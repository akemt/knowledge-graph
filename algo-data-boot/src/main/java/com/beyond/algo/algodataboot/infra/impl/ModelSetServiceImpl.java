package com.beyond.algo.algodataboot.infra.impl;

import com.beyond.algo.algodataboot.infra.ModelSetService;
import com.beyond.algo.common.Assert;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgUserMapper;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgModelSetVo;
import com.beyond.algo.vo.ModelDataVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgModelSet;
import com.beyond.algo.model.AlgModel;
import com.beyond.algo.mapper.AlgModelSetMapper;
import com.beyond.algo.mapper.AlgModelMapper;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author huangjinqing
 * @version Created in：20:22 2017/10/17
 * @Description:数据管理模型模块service接口实现
 */

@Service
public class ModelSetServiceImpl implements ModelSetService {

    @Autowired
    private AlgModelSetMapper algModelSetMapper;

    @Autowired
    private AlgModelMapper algModelMapper;

    @Autowired
    private AlgUserMapper algUserMapper;

    @Override
    public Result addModelSet(AlgModelSet modelSet) throws Exception {
        try {
            //生成模型集随机串
            if (modelSet.getModelSetName().isEmpty()) {
                return Result.failure("模型集名称为空");
            }
            modelSet.setModelSetSn(UUID.randomUUID().toString().replace("-", ""));
            algModelSetMapper.insert(modelSet);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
    }

    @Override
    public Result deleteModelSet(String modelSetSn) throws Exception {
        try {
            if (modelSetSn.isEmpty()) {
                return Result.failure("模型集串号为空");
            }
            this.deleteModelByModelSetSn(modelSetSn);
            algModelSetMapper.deleteByPrimaryKey(modelSetSn);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
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
    public Result deleteModel(String modelSn) throws Exception {
        try {
            if (modelSn.isEmpty()) {
                return Result.failure("模型串号为空");
            }
            algModelMapper.deleteByPrimaryKey(modelSn);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
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
    public Result queryAlgModel(String modelSetSn) throws Exception {
        try {
            Result result = new Result();
            if(modelSetSn.isEmpty()) {
               result.setMsg("模型集合串号为空");
               return result;
            }
            List<AlgModel> allAlgModel = algModelMapper.selectAll(modelSetSn);
            result.setData(allAlgModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result("获取所有模型集失败，模型集串号：" + modelSetSn);
        }

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
