package com.beyond.algm.algmdataboot.infra.impl;

import com.beyond.algm.common.Assert;
import com.beyond.algm.common.Result;
import com.beyond.algm.algmdataboot.infra.DataSetService;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgDataMapper;
import com.beyond.algm.mapper.AlgDataSetMapper;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgDataSet;
import com.beyond.algm.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Result addDataSet(AlgDataSet dataSet) throws AlgException {
        try {
            if (Assert.isEmpty(dataSet.getDataSetName())) {
                return Result.failure("数据集名称不能为空！");
            }
            if(algDataSetMapper.dataSetCount(dataSet.getUsrSn(),dataSet.getDataSetName())>0){
                return Result.failure(dataSet.getDataSetName() + "数据集名称已经存在！");
            }

            //取出当前用户最大排序值
            String dataOrderBy =algDataSetMapper.getMaxDataOrderBy(dataSet.getUsrSn());
            int max = 1;
            if(Assert.isNotEmpty(dataOrderBy)){
                max = Integer.parseInt(algDataSetMapper.getMaxDataOrderBy(dataSet.getUsrSn())) + 1;
            }
            dataOrderBy = Integer.toString(max);

            //生成数据集随机串
            dataSet.setDataSetSn(UUID.randomUUID().toString().replace("-", ""));
            dataSet.setDataOrderby(dataOrderBy);

            algDataSetMapper.insert(dataSet);
        } catch (Exception e) {
            log.error("添加数据集添加失败。数据集串号：{},用户串号：{},数据集名称：{},数据集排序：{}",dataSet.getDataSetSn(),dataSet.getUsrSn(),dataSet.getDataSetName(),dataSet.getDataOrderby(),e);
            throw new AlgException("BEYOND.ALG.DATASET.COMMON.ADD.0000001",new String[]{});
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
            log.error("删除数据失败。数据串号：{}",dataSn,e);
            throw new AlgException("BEYOND.ALG.DATA.COMMON.DEL.0000002",new String[]{});
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
            log.error("删除数据失败。数据集串号：{}",dataSetSn,e);
            throw new AlgException("BEYOND.ALG.DATASET.COMMON.DEL.0000003",new String[]{});
        }
        return Result.successResponse();
    }

    //点击数据集关联查询数据
    @Override
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
            log.error("获取所有数据集失败，模型集串号：{}",dataSetSn,e);
            throw new AlgException("BEYOND.ALG.DATA.COMMON.FIND.0000004",new String[]{});
        }
    }

    //新增数据
    @Override
    public Result addData(AlgData algData,AlgUser algUser) throws AlgException {
        try {
            if (Assert.isEmpty(algData.getDataName())) {
                return Result.failure("数据名称不能为空！");
            }
            if (Assert.isNotEmpty(algData.getDataEnName()) && algDataMapper.checkDataEnName(algUser.getUsrSn(),algData.getDataEnName()) != 0 ){
                return Result.failure("数据英文名已存在！");
            }
            //生成数据集随机串
            algData.setDataSn(UUID.randomUUID().toString().replace("-", ""));
            //用户串号
            algData.setUsrSn(algUser.getUsrSn());
            //上传时间
            algData.setCreatTime(new Date());
            //数据地址
            String dataPath = null;
            if(Assert.isEmpty(algData.getDataEnName())){
                dataPath = "data://" + algUser.getUsrCode() + "/" + algData.getDataSn();
            }else {
                dataPath = "data://" + algUser.getUsrCode() + "/" + algData.getDataEnName();
            }
            algData.setDataAddr(dataPath);

            algDataMapper.insert(algData);
        } catch (Exception e) {
            log.error("新增数据失败。数据串号：{},数据集串号：{},用户串号：{},数据名称：{},数据英文名称：{}",algData.getDataSn(),algData.getDataSetSn(),algData.getUsrSn(),
                    algData.getDataName(),algData.getDataEnName(),e);
            throw new AlgException("BEYOND.ALG.DATA.COMMON.ADD.0000005",new String[]{});
        }
        return Result.successResponse();
    }

    //数据商城
    @Override
    public Result algDataMall(String dataContent) throws AlgException {
        try {
            Result result = new Result();
            List<AlgData> allAlgData = algDataMapper.findAlgDataMall(dataContent);
            result.setData(allAlgData);
            return result;
        } catch (Exception e) {
            log.error("获取所有数据集失败，数据名称：{}",dataContent,e);
            throw new AlgException("BEYOND.ALG.DATA.COMMON.FIND.0000004",new String[]{});
        }
    }

    /*@Override
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
    }*/
}