package com.beyond.algo.algodataboot.infra;

import com.beyond.algo.common.Result;
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
     * @author ：ZhangJiayue
     * @Description: 显示数据集
     * @param： String usrSn
     * @date ：2017-10-19 20:13:30
     */
    List<AlgDataSet> showDataSet(String usrSn) throws Exception;

    /**
     * @author ：ZhangJiayue
     * @Description: 添加数据集
     * @param： AlgDataSet
     * @date ：2017-10-19 10:30:57
     */
    Result addDataSet(AlgDataSet modelSet) throws Exception;

    /**
     * @author ：ZhangJiayue
     * @Description: 删除数据集
     * @param： String dataSetSn
     * @date ： 2017-10-19 10:31:13
     */
    Result deleteDataSet(String dataSetSn) throws Exception;

    /**
     * @author ：ZhangJiayue
     * @Description: 查看数据
     * @param： String dataSetSn
     * @date ： 2017-10-22 16:11:06
     */
    Result queryAlgDatabySet(String dataSetSn) throws Exception;
    /**
     * @author ：ZhangJiayue
     * @Description: 增加数据
     * @param： AlgData
     * @date ： 2017-10-22 19:08:32
     */
    Result addData(AlgData algData)throws Exception;
    /**
     * @author ：ZhangJiayue
     * @Description: 删除数据
     * @param： String dataSn
     * @date ： 2017-10-22 21:54:02
     */
    Result deleteData(String dataSn) throws Exception;
    /**
     * @author ：ZhangJiayue
     * @Description: 修改数据
     * @param： String dataSn
     * @date ： 2017-10-22 21:54:06
     */
    Result modifyData(AlgData algData) throws Exception;
}
