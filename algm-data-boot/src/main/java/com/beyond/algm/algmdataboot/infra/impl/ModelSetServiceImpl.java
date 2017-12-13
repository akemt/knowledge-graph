package com.beyond.algm.algmdataboot.infra.impl;


import com.beyond.algm.algmdataboot.infra.ModelSetService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgModelSetVo;
import com.beyond.algm.vo.ModelDataVo;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyond.algm.common.Result;
import com.beyond.algm.model.AlgModelSet;
import com.beyond.algm.model.AlgModel;
import com.beyond.algm.mapper.AlgModelSetMapper;
import com.beyond.algm.mapper.AlgModelMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
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
                algModelSetMapper.deleteByPrimaryKey(algModel);
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
    public int deleteModel( AlgModel algModel ) throws AlgException {
           int count= algModelMapper.deleteModel(algModel);
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
    public PageInfo<ModelDataVo> queryAlgModel( AlgModel algModel, Pageable pageable) throws AlgException {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<ModelDataVo> allAlgModel = algModelMapper.queryModelPage(algModel);
        return new PageInfo<>(allAlgModel);
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
}
