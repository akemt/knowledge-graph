package com.beyond.algo.algodataboot.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgData;
import com.beyond.algo.model.AlgDataSet;

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
    Result addDataSet(AlgDataSet dataSet,String usrSn) throws AlgException;

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
    Result addData(AlgData algData,String usrSn)throws AlgException;





    //---------------------------------------------------old
    /**
     * @author ：ZhangJiayue
     * @Description: 显示数据集
     * @param： String usrSn
     * @date ：2017-10-19 20:13:30
     */
    //List<AlgDataSet> showDataSet(String usrSn) throws Exception;

    /**
     * @author ：ZhangJiayue
     * @Description: 添加数据集
     * @param： AlgDataSet
     * @date ：2017-10-19 10:30:57
     */
    //Result addDataSet(AlgDataSet modelSet) throws Exception;

    /**
     * @author ：ZhangJiayue
     * @Description: 删除数据集
     * @param： String dataSetSn
     * @date ： 2017-10-19 10:31:13
     */
    //Result deleteDataSet(String dataSetSn) throws Exception;

    /**
     * @author ：ZhangJiayue
     * @Description: 查看数据
     * @param： String dataSetSn
     * @date ： 2017-10-22 16:11:06
     */
    //Result queryAlgDatabySet(String dataSetSn) throws Exception;
    /**
     * @author ：ZhangJiayue
     * @Description: 增加数据
     * @param： AlgData
     * @date ： 2017-10-22 19:08:32
     */
    //Result addData(AlgData algData)throws Exception;
    /**
     * @author ：ZhangJiayue
     * @Description: 删除数据
     * @param： String dataSn
     * @date ： 2017-10-22 21:54:02
     */
    //Result deleteData(String dataSn) throws Exception;
    /**
     * @author ：ZhangJiayue
     * @Description: 修改数据
     * @param： String dataSn
     * @date ： 2017-10-22 21:54:06
     */
    Result modifyData(AlgData algData) throws Exception;
}
