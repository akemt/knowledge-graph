package com.beyond.algm.algmdataboot.controller;


import com.beyond.algm.algmdataboot.base.BaseController;
import com.beyond.algm.algmdataboot.infra.DataSetService;
import com.beyond.algm.common.Result;
import com.beyond.algm.common.ResultEnum;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgDataSet;
import com.beyond.algm.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangJiayue
 * @Description 数据管理数据模块controller
 * @date 2017-10-19 10:21:13
 *
 */
@RestController
@Slf4j
public class DataSetController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(DataSetController.class);

    @Autowired
    private DataSetService dataSetService;

    /**
     * @author ：Lindewei
     * @Description: 我的数据初始化
     */
    @RequestMapping(value = "/initdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result initData() throws AlgException {
        try {
            AlgUser algUser = getUserInfo();
            Map<String,List> dataMap=new HashMap<String,List>();
            //我的数据集
            List<AlgDataSet> dataSetList = dataSetService.getDataSet(algUser.getUsrSn());
            //List<AlgDataSet> dataSetList = dataSetService.getDataSet("37bf2269ee4845da8e86861bbde2438a");
            dataMap.put("algDataSet",dataSetList);
            //我的数据
            List<AlgData> algDataList = dataSetService.getData(algUser.getUsrSn());
            //List<AlgData> algDataList = dataSetService.getData("37bf2269ee4845da8e86861bbde2438a");
            dataMap.put("algData",algDataList);
            return Result.ok(dataMap);
        }catch (Exception e){
            logger.info("我的数据初始化失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：Lindewei
     * @Description: 添加数据集
     */
    @RequestMapping(value = "/adddataset", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result addDataSet(AlgDataSet dataSet) throws AlgException {
        AlgUser algUser = getUserInfo();
        logger.info("数据集名称:{},用户ID:{}", dataSet.getDataSetName(),algUser.getUsrSn());
        //logger.info("数据集名称:{},用户ID:{}", dataSet.getDataSetName(),null);
        try {
            dataSet.setUsrSn(algUser.getUsrSn());
            Result result = dataSetService.addDataSet(dataSet);
            return result;
        } catch (Exception e) {
            logger.info("添加数据集失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：Lindewei
     * @Description: 删除数据
     */
    @RequestMapping(value = "/deletedata", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result deleteData(String dataSn) throws AlgException {
        logger.info("数据集串号：{}",dataSn);
        try{
            Result result=dataSetService.deleteData(dataSn);
            return result;
        }catch(Exception e)
        {
            logger.info("删除数据集失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：Lindewei
     * @Description: 删除当前数据集
     */
    @RequestMapping(value = "/deletedataSet", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result deleteDataSet(String dataSetSn) throws AlgException {
        logger.info("数据集串号：{}",dataSetSn);
        try{
            Result result=dataSetService.deleteDataSet(dataSetSn);
            return result;
        }catch(Exception e)
        {
            logger.info("删除数据集失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：Lindewei
     * @Description: 点击数据集关联查询数据
     */
    @RequestMapping(value = "/showdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result showData(String dataSetSn) throws AlgException {
        logger.info("数据集串号：{}",dataSetSn);
        try{
            Result result=dataSetService.queryAlgDatabySet(dataSetSn);
            return result;
        }catch(Exception e)
        {
            logger.info("查看数据集失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：Lindewei
     * @Description: 新增数据
     */
    @RequestMapping(value = "/adddata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result addData(AlgData algData) throws AlgException {
        try {
            AlgUser algUser = getUserInfo();
            Result result = dataSetService.addData(algData,algUser);
            //Result result = dataSetService.addData(algData,null);
            return result;
        } catch (Exception e) {
            logger.info("新增数据失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：Lindewei
     * @Description: 数据商城
     */
    @RequestMapping(value = "/datamall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result dataMall(String dataContent) throws AlgException {
        logger.info("数据搜索名：{}",dataContent);
        try{
            Result result=dataSetService.algDataMall(dataContent);
            return result;
        }catch(Exception e)
        {
            logger.info("查看数据集失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @auther: ZhangJiayue
     * @Description: 修改数据
     * @param : AlgData
     * @date: 2017-10-22 19:24:26
     */
    /*@RequestMapping(value = "/modifyData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> modifyData(AlgData algData) {
        logger.info("修改数据信息");
        try {
            Result result = dataSetService.modifyData(algData);
            return result;
        } catch (Exception e) {
            logger.info("修改数据失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }*/
}
