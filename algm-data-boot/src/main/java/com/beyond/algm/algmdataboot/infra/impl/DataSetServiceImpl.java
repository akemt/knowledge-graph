package com.beyond.algm.algmdataboot.infra.impl;


import com.beyond.algm.algmdataboot.infra.DataSetService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgDataMapper;
import com.beyond.algm.mapper.AlgDataSetMapper;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgDataSet;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgDataDownLoadVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private AlgUserMapper algUserMapper;

    //我的数据集tree
    @Override
    public PageInfo<AlgDataSet> getDataSet(String usrSn, PageInfo pageInfo) throws AlgException{
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        Page<AlgDataSet> algDataSets = algDataSetMapper.selectAll(usrSn);
        return new PageInfo<>(algDataSets);
    }

    //我的数据List
    @Override
    public PageInfo<AlgData> getData(String usrSn, PageInfo pageInfo) throws AlgException{
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        Page<AlgData> algData = algDataMapper.findDataList(usrSn);
        return new PageInfo<>(algData);
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
    public int deleteData(AlgData algData) throws AlgException {
        int delData = algDataMapper.deleteData(algData);
        return delData;
    }

    //删除当前数据集
    @Override
    public int deleteDataSet(AlgDataSet algDataSet) throws AlgException {
        if(Assert.isEmpty(algDataSet.getDataSetSn())){
            throw new AlgException("BEYOND.ALG.DATA.PAY.STATUS.0000013");
        }
        if(algDataMapper.dataCount(algDataSet.getDataSetSn()) != 0){
            throw new AlgException("BEYOND.ALG.DATA.PAY.STATUS.0000014");
        }
       int delDataSet = algDataSetMapper.deleteDataSet(algDataSet);
       return  delDataSet;
    }

    //点击数据集关联查询数据
    @Override
    public PageInfo<AlgData> queryAlgDatabySet(String dataSetSn, PageInfo pageInfo) throws AlgException {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        Page<AlgData> algData = algDataMapper.queryAlgDatabySet(dataSetSn);
        return new PageInfo<>(algData);
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
  /*          //生成数据集随机串
            algData.setDataSn(UUID.randomUUID().toString().replace("-", ""));
            //用户串号
            algData.setUsrSn(algUser.getUsrSn());*/
            //上传时间
            algData.setCreatTime(new Date());
            //数据地址
        /*    String dataPath = null;
            if(Assert.isEmpty(algData.getDataEnName())){
                dataPath = "data://" + algUser.getUsrCode() + "/" + algData.getDataSn();
            }else {
                dataPath = "data://" + algUser.getUsrCode() + "/" + algData.getDataEnName();
            }
            algData.setDataAddr(dataPath);*/

            algDataMapper.updateData(algData);
        } catch (Exception e) {
            log.error("新增数据失败。数据串号：{},数据集串号：{},用户串号：{},数据名称：{},数据英文名称：{}",algData.getDataSn(),algData.getDataSetSn(),algData.getUsrSn(),
                    algData.getDataName(),algData.getDataEnName(),e);
            throw new AlgException("BEYOND.ALG.DATA.COMMON.ADD.0000005",new String[]{});
        }
        return Result.successResponse();
    }

    //数据商城
    @Override
    public Page<AlgDataDownLoadVo> algDataMall(String dataContent, Integer pageNum, Integer pageSize) throws AlgException {
        //初步设定用数据库进行排序查询
        PageHelper.startPage(pageNum,pageSize);
        Page<AlgDataDownLoadVo> allAlgData = algDataMapper.findAlgDataMall(dataContent);
        return allAlgData;
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:获取数据集主键
     * @param： String dataSn
     * @date ： 2017-12-06 21:54:06
     */
    @Override
    public List<AlgDataSet>  dataSetId(String usrSn,String dataSetName ) throws AlgException{
        List<AlgDataSet> algDataSetList=algDataSetMapper.dataSetId(usrSn,dataSetName);
        if(Assert.isEmpty(algDataSetList)){
            String[] checkMessage = {" 查询结果为空",""};
            throw new AlgException("BEYOND.ALG.MODEL.COMMON.VALID.0000003",checkMessage);
        }
        return algDataSetList;
    }

}
