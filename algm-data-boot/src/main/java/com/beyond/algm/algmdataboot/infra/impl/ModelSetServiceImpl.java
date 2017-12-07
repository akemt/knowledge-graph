package com.beyond.algm.algmdataboot.infra.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.beyond.algm.algmdataboot.infra.ModelSetService;
import com.beyond.algm.algmdataboot.util.CephUtil;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgModelSetVo;
import com.beyond.algm.vo.ModelDataVo;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.beyond.algm.common.Result;
import com.beyond.algm.model.AlgModelSet;
import com.beyond.algm.model.AlgModel;
import com.beyond.algm.mapper.AlgModelSetMapper;
import com.beyond.algm.mapper.AlgModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void addModelSet(AlgModelSet modelSet) throws AlgException {
            //生成模型集随机串
            if (modelSet.getModelSetName().isEmpty()) {
                throw new AlgException("BEYOND.ALG.MODEL.COMMON.VALID.0000001");
            }
            int count= algModelSetMapper.checkData(modelSet);
            if(count>0){
                String[] checkMessage = {"模型集",""};
                throw new AlgException("BEYOND.ALG.MODEL.COMMON.VALID.0000002",checkMessage);
            }
            String maxOrderby =algModelSetMapper.checkMaxOrderby(modelSet.getUsrSn());
            if(Assert.isNotEmpty(maxOrderby)){
                maxOrderby=String.valueOf(Integer.valueOf(maxOrderby)+1);
            }else{
                maxOrderby="1";
            }
            modelSet.setModelOrderby(maxOrderby);
            modelSet.setModelSetSn(UUIDUtil.createUUID());
            algModelSetMapper.insert(modelSet);
    }

    @Override
    public int deleteModelSet(AlgModel algModel) throws AlgException {
            int count= algModelMapper.checkData(algModel);
            if(count==0) {
                algModelSetMapper.deleteByPrimaryKey(algModel.getModelSetSn());
                return  1;
            }
            return 0;
    }

    @Override
    public Result addAlgModel(AlgModel algModel) throws Exception {
        try {
            //生成模型随机串号
            algModel.setModelSn(UUID.randomUUID().toString().replace("-", ""));
            // new Date()为获取当前系统时间
            algModel.setCreateTime(new Date());
            algModelMapper.insert(algModel);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("添加模型失败");
        }

        return Result.successResponse();
    }

    @Override
    public int deleteModel(String modelSn) throws AlgException {
           int count= algModelMapper.deleteByPrimaryKey(modelSn);
           return count;
    }

    @Override
    public Result deleteModelByModelSetSn(String modelSetSn) throws Exception {
        try {
            if (modelSetSn.isEmpty()) {
                return Result.failure("模型串号为空");
            }
            algModelMapper.deleteByModelSetSn(modelSetSn);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
    }

    @Override
    public Result modifyAlgModel(AlgModel algModel) throws Exception {
        try {
            if (algModel.getModelSn().isEmpty()) {
                return Result.failure("模型串号为空！");
            }
            algModelMapper.updateByPrimaryKey(algModel);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("修改模型失败");
        }
        return Result.successResponse();
    }

    @Override
    public  List<AlgModelSetVo> queryAlgModelSet(String usrSn) throws AlgException {
            List<AlgModelSetVo> algModelSetVoList = algModelSetMapper.queryModelSet(usrSn);
            if(Assert.isNotEmpty(algModelSetVoList)){
                String modelSetSn= algModelSetVoList.get(0).getModelSetSn();
                AlgModel algModel=new AlgModel();
                algModel.setUsrSn(usrSn);
                algModel.setModelSetSn(modelSetSn);
                List<ModelDataVo> algModelList=algModelMapper.queryModel(algModel);
                algModelSetVoList.get(0).setAlgModelVolist(algModelList);
                return algModelSetVoList;
            }
            return null;
    }

    @Override
    public List<ModelDataVo> queryAlgModel( AlgModel algModel) throws AlgException {
            List<ModelDataVo> allAlgModel = algModelMapper.queryModel(algModel);
            return allAlgModel;
    }
    @Override
    public AlgUser findByUsrCode(String usrCode){

        return algUserMapper.selectUsrCode(usrCode);
    }
    @Override
    public List<ModelDataVo> queryModelDataSet(ModelDataVo modelDataVo)throws AlgException{
        //分页处理
        PageHelper.startPage(modelDataVo.getPage(), modelDataVo.getRows());
        List<ModelDataVo> modelDataVoList = algModelMapper.queryModelDataSet(modelDataVo);
        return modelDataVoList;
    }
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
            String[] checkMessage = {" 文件名字重复",""};
            throw new AlgException("BEYOND.ALG.DATA.FILE.NAME.0000006",checkMessage);
        }
        return  count;
    }

    /**
     * @author ：zhangchuanzhi
     * @Description: 上传个人数据文件
     * @param：
     * @date ： 2017-10-22 21:54:06
     */
    @Override
    @Transactional(rollbackFor = AlgException.class)
    public void uploadModelSet(MultipartFile file, String usrCode, String modelName, String dataUuid) throws AlgException{
        Float count=algUserMapper.selectCountSpace(usrCode);
        Float fileSize= FileUtil.bytes2kb(file.getSize());
        // 上传文件大于用户所剩存储空间
        if(fileSize>count){
            String[] checkMessage = {" 空间不足",""};
            throw new AlgException("BEYOND.ALG.DATA.FILE.SPACE.0000007",checkMessage);

        }
        log.info("文件名:{},用户code:{},accessKey:{},secretKey:{},path:{},数据集合名称",file.getOriginalFilename(),usrCode,accessKey,secretKey,path,modelName);
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
        AmazonS3 conn= CephUtil.connectCeph(accessKey,secretKey,host);
        Bucket bucket=null;
        if(!conn.doesBucketExistV2(bucketName)){
            bucket=conn.createBucket(bucketName);
        }
        conn.putObject(bucketName,key,targetFile);
        conn.setObjectAcl(bucketName,key, CannedAccessControlList.PublicRead);
        String pathUrl=  conn.getUrl(bucketName,key).toString();
        log.info("保存路径：{}",pathUrl);
        AlgUser user=new AlgUser();
        user.setUsrCode(usrCode);
        user.setUsrUsedSpace(fileSize);
        algUserMapper.updateSpace(user);
        AlgModel algModel=new AlgModel();
        algModel.setModelSn(dataUuid);
        algModel.setModelAddress(pathUrl);
        algModel.setModelSize(count.toString());
        algModelMapper.update(algModel);
        targetFile.delete();
    }

}
