package com.beyond.algo.algodataboot.controller;

import base.BaseController;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.algodataboot.infra.DataSetService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgData;
import com.beyond.algo.model.AlgDataSet;
import com.beyond.algo.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
//public class DataSetController extends BaseController {
public class DataSetController {

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
            //todo
            //AlgUser algUser = getUserInfo();
            Map<String,List> dataMap=new HashMap<String,List>();
            //我的数据集
            //List<AlgDataSet> dataSetList = dataSetService.getDataSet(algUser.getUsrSn());
            List<AlgDataSet> dataSetList = dataSetService.getDataSet("37bf2269ee4845da8e86861bbde2438a");
            dataMap.put("algDataSet",dataSetList);
            //我的数据
            //List<AlgData> algDataList = dataSetService.getData(algUser.getUsrSn());
            List<AlgData> algDataList = dataSetService.getData("37bf2269ee4845da8e86861bbde2438a");
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
        //todo
        //AlgUser algUser = getUserInfo();
        //logger.info("数据集名称:{},用户ID:{}", dataSet.getDataSetName(),algUser.getUsrSn());
        logger.info("数据集名称:{},用户ID:{}", dataSet.getDataSetName(),null);
        try {
            //Result result = dataSetService.addDataSet(dataSet,algUser.getUsrSn());
            Result result = dataSetService.addDataSet(dataSet,"37bf2269ee4845da8e86861bbde2438a");
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
    @RequestMapping(value = "/deleteData", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "/deleteDataSet", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "/showData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "/addData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result addData(AlgData algData) throws AlgException {
        try {
            //AlgUser algUser = getUserInfo();
            //Result result = dataSetService.addData(algData,algUser.getUsrSn());
            Result result = dataSetService.addData(algData,"37bf2269ee4845da8e86861bbde2438a");
            return result;
        } catch (Exception e) {
            logger.info("新增数据失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }




    //-----------------------------------------------------------------------old

    /**
     * @author ：ZhangJiayue
     * @Description: 显示数据集
     * @param： String usrSn
     * @date ：2017-10-19 20:03:20
     */
    /**@RequestMapping(value = "/showDataSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> showDataSet(String usrSn){
        logger.info("用户ID:{}",usrSn );
        List<AlgDataSet> dataSetList = new ArrayList<AlgDataSet>();
        try {
            dataSetList = dataSetService.showDataSet(usrSn);
            if(Assert.isNotEmpty(dataSetList)){
                return Result.ok(dataSetList);
            }else{
                return Result.failure(dataSetList);
            }

        }catch (Exception e){
            logger.info("查询数据集失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }*/

    /**
     * @author ：ZhangJiayue
     * @Description: 添加数据集
     * @param： AlgDataSet
     * @date ：2017-10-19 16:10:12
     */
    /** @RequestMapping(value = "/addDataSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> addDataSet(AlgDataSet dataSet){
        logger.info("数据集名称:{},用户ID:{}", dataSet.getDataSetName(),dataSet.getUsrSn());
        try {
            Result result = dataSetService.addDataSet(dataSet);
            return result;
        } catch (Exception e) {
            logger.info("添加数据集失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }*/

    /**
     * @author ：ZhangJiayue
     * @Description: 删除数据集
     * @param： String dataSetSn
     * @date ：2017-10-19 16:24:17
     */
    //@RequestMapping(value = "/deleteDataSet",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    /**public @ResponseBody
    Result<Object> deleteDataSet(String dataSetSn){
        logger.info("数据集串号：{}",dataSetSn);
        try{
            Result result=dataSetService.deleteDataSet(dataSetSn);
            return result;
        }catch(Exception e)
        {
            logger.info("删除数据集失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }*/

    /**
     * @author ：ZhangJiayue
     * @Description: 查看数据
     * @param： String dataSetSn
     * @date ：2017-10-22 16:08:05
     */
   /** @RequestMapping(value = "/showData",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> showData(String dataSetSn){
        logger.info("数据集串号：{}",dataSetSn);
        try{
            Result result=dataSetService.queryAlgDatabySet(dataSetSn);
            return result;
        }catch(Exception e)
        {
            logger.info("查看数据集失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }*/

    /**
     * @author :ZhangJiayue
     * @Description: 添加数据
     * @param: AlgData
     * @date: 2017-10-22 19:07:10
     */
    /**@RequestMapping(value = "/addData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> addAlgData(AlgData algData) {
        logger.info("数据串号：{}");
        try {
            Result result = dataSetService.addData(algData);
            return result;
        } catch (Exception e) {
            logger.info("添加数据失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }*/
    /**
     * @author ：ZhangJiayue
     * @Description: 删除数据
     * @param： String dataSn
     * @date ：2017-10-22 19:15:45
     */
    /**@RequestMapping(value = "/deleteData",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> deleteData(String dataSn){
        logger.info("数据集串号：{}",dataSn);
        try{
            Result result=dataSetService.deleteData(dataSn);
            return result;
        }catch(Exception e)
        {
            logger.info("删除数据集失败",e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }*/
    /**
     * @auther: ZhangJiayue
     * @Description: 修改数据
     * @param : AlgData
     * @date: 2017-10-22 19:24:26
     */
    @RequestMapping(value = "/modifyData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    }
}
