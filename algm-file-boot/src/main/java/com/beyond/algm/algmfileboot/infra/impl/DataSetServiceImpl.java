package com.beyond.algm.algmfileboot.infra.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.beyond.algm.algmfileboot.infra.DataSetService;
import com.beyond.algm.algmfileboot.util.FileDownLoad;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgDataMapper;
import com.beyond.algm.mapper.AlgDataSetMapper;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgData;
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
 * @author ZhangJiayue
 * @Description 数据管理数据模块service接口实现
 * @data 2017-10-19 10:43:02
 */

@Service
@Slf4j
public class DataSetServiceImpl implements DataSetService {
    @Value("${ceph.host}")
    private  String host;
    @Value("${ceph.accessKey}")
    private   String accessKey;
    @Value("${ceph.secretKey}")
    private  String secretKey;
    @Value("${ceph.path}")
    private  String path;
    @Autowired
    private AlgDataSetMapper algDataSetMapper;
    @Autowired
    private AlgDataMapper algDataMapper;
    @Autowired
    private AlgUserMapper algUserMapper;
    @Autowired
    AmazonS3 conn;

    /**
     * @author ：zhangchuanzhi
     * @Description: 上传个人数据文件
     * @param：
     * @date ： 2017-10-22 21:54:06
     */
    @Override
    @Transactional(rollbackFor = AlgException.class)
    public AlgData uploadDateSet(MultipartFile file, String usrCode,String dataSetName,String dataSetUuid,String usrSn) throws AlgException{
        Float count=algUserMapper.selectCountSpace(usrCode);
        Float fileSize= FileUtil.bytes2kb(file.getSize());
          // 上传文件大于用户所剩存储空间
         if(fileSize>count){
             String[] checkMessage = {" 空间不足",""};
             throw new AlgException("BEYOND.ALG.DATA.FILE.SPACE.0000007",checkMessage);

         }
        log.info("文件名:{},用户code:{},accessKey:{},secretKey:{},path:{},数据集合名称:{},数据集id:{},用户ID",file.getOriginalFilename(),usrCode,accessKey,secretKey,path,dataSetName,dataSetUuid,usrSn);
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
        String key=dataSetName+"/"+file.getOriginalFilename();
        String bucketName=usrCode;
        Bucket bucket=null;
        if(!conn.doesBucketExistV2(bucketName)){
            bucket=conn.createBucket(bucketName);
        }
        conn.putObject(bucketName,key,targetFile);
        conn.setObjectAcl(bucketName,key, CannedAccessControlList.PublicRead);
        String pathUrl=  conn.getUrl(bucketName,key).toString();
        pathUrl= pathUrl.replace("http","data");
        // 替换
        log.info("保存路径：{}",pathUrl);
        AlgUser user=new AlgUser();
         // 增加占用空间
        user.setUsrCode(usrCode);
        user.setUsrUsedSpace(fileSize);
        algUserMapper.updateSpace(user);
        AlgData algData=new AlgData();
        String uuid= UUIDUtil.createUUID();
        log.info("生成数据主键:{}",uuid);
        algData.setDataSn(uuid);
        algData.setCreatTime(new Date());
        algData.setDataName(file.getOriginalFilename());
        algData.setUsrSn(usrSn);
        algData.setDataSetSn(dataSetUuid);
        algData.setDataAddr(pathUrl);
        algData.setDataSize(fileSize.toString());
        algDataMapper.insert(algData);
        targetFile.delete();
        return algData;
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:检查文件上传名字
     * @param： String dataSn
     * @date ： 2017-12-06 21:54:06
     */
    @Override
    public int checkFileName(AlgData algData) throws AlgException{
      int count= algDataMapper.checkFileName(algData);
      if(count>0){
          String[] checkMessage = {" 文件名字重复",""};
          throw new AlgException("BEYOND.ALG.DATA.FILE.NAME.0000006",checkMessage);
      }
      return  count;
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:获取数据url
     * @param： String algData
     * @date ： 2017-12-06 21:54:06
     */
    @Override
    public String  dataUrl(AlgData algData)throws AlgException{
        String url= algDataMapper.dataUrl(algData);
        return url;
    }
    /**
     * @author ：zhangchuanzhii
     * @Description:数据文件下载
     * @param：
     * @date ： 2017-12-06 21:54:06
     */

    @Override
    public  void  downDataUrl(String  usrSn,String dataSet,String fileName,String usrCode,HttpServletResponse response)throws AlgException{
        String url=host+"/"+usrCode+"/"+dataSet+"/"+fileName;
        log.info("生成url:{}",url);
        if(Assert.isNotEmpty(url)){
            // 数据集+文件名
            String key=dataSet+"/"+fileName;
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
}
