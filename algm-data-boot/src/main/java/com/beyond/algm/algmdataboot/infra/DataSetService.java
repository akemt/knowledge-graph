package com.beyond.algm.algmdataboot.infra;

import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgDataSet;
import com.beyond.algm.model.AlgUser;

import java.util.List;

/**
 * @author ZhangJiayue
 * @Description 数据管理数据模块service接口声明
 * @data 2017-10-19 10:29:44
 */
public interface DataSetService {
    /**
     * @author ：lindewei
     * @Description: 我的数据集tree
     */
    List<AlgDataSet> getDataSet(String usrSn) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 我的数据List
     */
    List<AlgData> getData(String usrSn) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 添加数据集
     */
    Result addDataSet(AlgDataSet dataSet) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 删除数据
     */
    Result deleteData(String dataSn) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 删除当前数据集
     */
    Result deleteDataSet(String dataSetSn) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 点击数据集关联查询数据
     */
    Result queryAlgDatabySet(String dataSetSn) throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 新增数据
     */
    Result addData(AlgData algData,AlgUser algUser)throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 数据商城
     */
    Result algDataMall(String dataContent,Integer numPage,Integer numRows) throws AlgException;

    /**
     * @author ：ZhangJiayue
     * @Description: 修改数据
     * @param： String dataSn
     * @date ： 2017-10-22 21:54:06
     */
    //Result modifyData(AlgData algData) throws Exception;
}
