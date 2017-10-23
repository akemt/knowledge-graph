package com.beyond.algo.algodataboot.infra.impl;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.algodataboot.infra.DataSetService;
import com.beyond.algo.mapper.AlgDataMapper;
import com.beyond.algo.mapper.AlgDataSetMapper;
import com.beyond.algo.model.AlgData;
import com.beyond.algo.model.AlgDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author ZhangJiayue
 * @Description 数据管理数据模块service接口实现
 * @data 2017-10-19 10:43:02
 */

@Service
public class DataSetServiceImpl implements DataSetService {

    @Autowired
    private AlgDataSetMapper algDataSetMapper;
    @Autowired
    private AlgDataMapper algDataMapper;

    @Override
    public List<AlgDataSet> showDataSet(String usrSn) throws Exception {
        List<AlgDataSet> algDataSetsList = algDataSetMapper.selectAll(usrSn);
        return algDataSetsList;
    }

    @Override
    public Result addDataSet(AlgDataSet dataSet) throws Exception {
        try {
            if (Assert.isEmpty(dataSet.getDataSetName())) {
                return Result.failure("数据集名称为空");
            }
            //生成数据集随机串
            dataSet.setDataSetSn(UUID.randomUUID().toString().replace("-", ""));
            algDataSetMapper.insert(dataSet);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
    }


    @Override
    public Result deleteDataSet(String dataSetSn) throws Exception {
        try{
            if(Assert.isEmpty(dataSetSn))
            {
                return Result.failure("数据集串号为空");
            }
            if(algDataMapper.dataCount(dataSetSn) != 0){
                return Result.failure("数据集中还存在数据，无法删除");
            }
            algDataSetMapper.deleteByPrimaryKey(dataSetSn);
        }catch(Exception e){
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
    }

    @Override
    public Result queryAlgDatabySet(String dataSetSn) throws Exception {
        try {
            Result result = new Result();
            if(Assert.isEmpty(dataSetSn)) {
                result.setMsg("数据集合串号为空");
                return result;
            }
            List<AlgData> allAlgData = algDataMapper.queryAlgDatabySet(dataSetSn);
            result.setData(allAlgData);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result("获取所有模型集失败，模型集串号：" + dataSetSn);
        }
    }

    @Override
    public Result addData(AlgData algData) throws Exception {
        try {
            if (Assert.isEmpty(algData.getDataName())||Assert.isEmpty(algData.getDataEnName())) {
                return Result.failure("数据名称或英文名为空");
            }
            if (algDataMapper.checkDataEnName(algData) != 0 ){
                return Result.failure("数据英文名已存在");
            }
            //生成数据集随机串
            algData.setDataSn(UUID.randomUUID().toString().replace("-", ""));
            algDataMapper.insert(algData);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
    }

    @Override
    public Result deleteData(String dataSn) throws Exception {
        try{
            if(Assert.isEmpty(dataSn))
            {
                return Result.failure("数据串号为空");
            }
            algDataMapper.deleteByPrimaryKey(dataSn);
        }catch(Exception e){
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
    }

    @Override
    public Result modifyData(AlgData algData) throws Exception {
        try {
            if (Assert.isEmpty(algData.getDataSn())) {
                return Result.failure("数据串号为空！");
            }
            if (algDataMapper.checkDataEnName(algData) != 0 ){
                return Result.failure("数据英文名已存在");
            }
            algDataMapper.updateByPrimaryKey(algData);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("修改数据失败");
        }
        return Result.successResponse();
    }
}
