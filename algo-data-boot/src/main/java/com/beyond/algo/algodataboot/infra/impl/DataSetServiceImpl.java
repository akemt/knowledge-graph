package com.beyond.algo.algodataboot.infra.impl;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.algodataboot.infra.DataSetService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgDataMapper;
import com.beyond.algo.mapper.AlgDataSetMapper;
import com.beyond.algo.model.AlgData;
import com.beyond.algo.model.AlgDataSet;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DataSetServiceImpl implements DataSetService {

    @Autowired
    private AlgDataSetMapper algDataSetMapper;
    @Autowired
    private AlgDataMapper algDataMapper;

    //我的数据集tree
    @Override
    public List<AlgDataSet> getDataSet(String usrSn) throws AlgException{
        return algDataSetMapper.selectAll(usrSn);
    }

    //我的数据List
    @Override
    public List<AlgData> getData(String usrSn) throws AlgException{
        return algDataMapper.findDataList(usrSn);
    }

    //添加数据集
    @Override
    public Result addDataSet(AlgDataSet dataSet,String usrSn) throws AlgException {
        try {
            if (Assert.isEmpty(dataSet.getDataSetName())) {
                return Result.failure("数据集名称不能为空！");
            }
            if(algDataSetMapper.dataSetCount(usrSn,dataSet.getDataSetName())>0){
                return Result.failure(dataSet.getDataSetName() + "数据集名称已经存在！");
            }

            //取出当前用户最大排序值
            String dataOrderBy =algDataSetMapper.getMaxDataOrderBy(usrSn);

            //生成数据集随机串
            dataSet.setDataSetSn(UUID.randomUUID().toString().replace("-", ""));
            dataSet.setUsrSn(usrSn);
            dataSet.setDataOrderby(dataOrderBy);

            algDataSetMapper.insert(dataSet);
        } catch (Exception e) {
            log.error("添加数据集添加失败。数据集串号：{},用户串号：{},数据集名称：{},数据集排序：{}",dataSet.getDataSetSn(),dataSet.getUsrSn(),dataSet.getDataSetName(),dataSet.getDataOrderby(),e);
            throw new AlgException("BEYOND.ALG.MODULE.ADD.0000002",new String[]{});
        }
        return Result.successResponse();
    }

    //删除数据
    @Override
    public Result deleteData(String dataSn) throws AlgException {
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

    //删除当前数据集
    @Override
    public Result deleteDataSet(String dataSetSn) throws AlgException {
        try{
            if(Assert.isEmpty(dataSetSn))
            {
                return Result.failure("数据集串号为空");
            }
            if(algDataMapper.dataCount(dataSetSn) != 0){
                return Result.failure("数据集下存在数据，不允许删除！");
            }
            algDataSetMapper.deleteByPrimaryKey(dataSetSn);
        }catch(Exception e){
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
    }

    //点击数据集关联查询数据
    // @Override
    public Result queryAlgDatabySet(String dataSetSn) throws AlgException {
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





    //-----------------------------------------------------------old
    /**@Override
    public List<AlgDataSet> showDataSet(String usrSn) throws Exception {
        List<AlgDataSet> algDataSetsList = algDataSetMapper.selectAll(usrSn);
        return algDataSetsList;
    }*/

    /** @Override
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
    }*/


    /**@Override
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
    }*/

   // @Override
    /**public Result queryAlgDatabySet(String dataSetSn) throws Exception {
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
    }*/

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

    /**@Override
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
    }*/

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
