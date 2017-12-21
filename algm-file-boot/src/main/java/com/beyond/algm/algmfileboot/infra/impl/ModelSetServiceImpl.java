package com.beyond.algm.algmfileboot.infra.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.beyond.algm.algmfileboot.infra.ModelSetService;
import com.beyond.algm.algmfileboot.util.FileDownLoad;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgAuthCodeMapper;
import com.beyond.algm.mapper.AlgModelMapper;
import com.beyond.algm.mapper.AlgModelSetMapper;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgAuthCode;
import com.beyond.algm.model.AlgModel;

import com.beyond.algm.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

/**
 * @author huangjinqing
 * @version Created in：20:22 2017/10/17
 * @Description:数据管理模型模块service接口实现
 */

@Service
@Slf4j
public class ModelSetServiceImpl implements ModelSetService {
    @Value("${ceph.host}")
    private  String host;
    @Value("${ceph.accessKey}")
    private   String accessKey;
    @Value("${ceph.secretKey}")
    private  String secretKey;
    @Value("${ceph.path}")
    private  String path;
    @Autowired
    private AlgModelSetMapper algModelSetMapper;

    @Autowired
    private AlgModelMapper algModelMapper;

    @Autowired
    private AlgUserMapper algUserMapper;
    @Autowired
    AmazonS3 conn;
    @Autowired
    private AlgAuthCodeMapper algAuthCodeMapper;
    /**
     * @author ：zhangchuanzhi
     * @Description:检查文件上传名字
     * @param： String dataSn
     * @date ： 2017-12-06 21:54:06
     */
    @Override
    public int checkFileName(AlgModel algModel) throws AlgException{
        int count= algModelMapper.checkFileName(algModel);
        if(count>0){
            String[] checkMessage = {" 文件名字",""};
            throw new AlgException("BEYOND.ALG.DATA.FILE.NAME.0000006",checkMessage);
        }
        return count;
    }

    /**
     * @author ：zhangchuanzhi
     * @Description: 上传个人数据文件
     * @param：
     * @date ： 2017-10-22 21:54:06
     */
    @Override
    @Transactional(rollbackFor = AlgException.class)
    public AlgModel uploadModelSet(MultipartFile file, String usrCode, String modelName, String modelUuid,String usrSn) throws AlgException{
        Float count=algUserMapper.selectCountSpace(usrCode);
        Float fileSize= FileUtil.bytes2kb(file.getSize());
        // 上传文件大于用户所剩存储空间
        if(fileSize>count){
            String[] checkMessage = {" 空间不足",""};
            throw new AlgException("BEYOND.ALG.DATA.FILE.SPACE.0000007",checkMessage);

        }
        log.info("文件名:{},用户code:{},accessKey:{},secretKey:{},path:{},模型集合名称:{},模型id:{},用户ID:{}",file.getOriginalFilename(),usrCode,accessKey,secretKey,path,modelName,modelUuid,usrSn);
        File targetFile = new File(path+file.getOriginalFilename());
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            // 文件转存到硬盘
            file.transferTo(targetFile);
        } catch (Exception e) {
            log.info("文件转存错误",e);
        }
        String key=modelName+"/"+file.getOriginalFilename();
        String bucketName=usrCode;
        Bucket bucket=null;
        if(!conn.doesBucketExistV2(bucketName)){
            bucket=conn.createBucket(bucketName);
        }
         // 此步骤没有考虑
        conn.putObject(bucketName,key,targetFile);
        conn.setObjectAcl(bucketName,key, CannedAccessControlList.PublicRead);
        String pathUrl=  conn.getUrl(bucketName,key).toString();
        String pathHost="http://"+host;
        pathUrl= pathUrl.replace(pathHost,"model:/");
        log.info("保存路径：{}",pathUrl);
        AlgUser user=new AlgUser();
        user.setUsrCode(usrCode);
        user.setUsrUsedSpace(fileSize);
        algUserMapper.updateSpace(user);
        AlgModel algModel=new AlgModel();
        String uuid= UUIDUtil.createUUID();
        log.info("生成数据主键:{}",uuid);
        targetFile.delete();
        algModel.setModelSn(uuid);
        algModel.setUsrSn(usrSn);
        algModel.setModelSetSn(modelUuid);
        algModel.setModelName(file.getOriginalFilename());
        algModel.setCreateTime(new Date());
        algModel.setModelAddress(pathUrl);
        algModel.setModelSize(file.getSize()+"");
        algModel.setIsOpenSrc("0");
        algModelMapper.insert(algModel);
        return algModel;
    }
    /**
     * @author ：zhangchuanzhi
     * @Description: 数据模型下载
     * @param：
     * @date ： 2017-10-22 21:54:06
     */
    @Override
    public  void  downModelUrl(String  usrSn,String modelSet,String fileName,String usrCode,HttpServletResponse response)throws AlgException{
        String url=host+"/"+usrCode+"/"+modelSet+"/"+fileName;
        log.info("生成url:{}",url);
        if(Assert.isNotEmpty(url)){
            // 数据集+文件名
            String key=modelSet+"/"+fileName;
            File downloadFile=new File(path+fileName);
            conn.getObject(
                    new GetObjectRequest(usrCode, key),
                    downloadFile
            );
            FileDownLoad.fileDownLoad(response,downloadFile,fileName);
        }else{
            String[] checkMessage = {" 查询结果为空",""};
            throw new AlgException("BEYOND.ALG.MODEL.COMMON.VALID.0000003",checkMessage);
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description: 返回用户
     * @param：
     * @date ： 2017-10-22 21:54:06
     */
    @Override
    public AlgAuthCode selectUsr(String acdId)throws AlgException{
        AlgAuthCode algAuthCode = algAuthCodeMapper.selectUsr(acdId);
        return algAuthCode;
    }
}
