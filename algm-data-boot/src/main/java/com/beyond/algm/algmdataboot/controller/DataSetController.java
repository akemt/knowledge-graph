package com.beyond.algm.algmdataboot.controller;


import com.beyond.algm.algmdataboot.base.BaseController;
import com.beyond.algm.algmdataboot.infra.AuthService;
import com.beyond.algm.algmdataboot.infra.DataSetService;
import com.beyond.algm.common.Result;
import com.beyond.algm.common.ResultEnum;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgDataSet;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgDataDownLoadVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    @Autowired
    private AuthService authService;
    /**
     * @author ：Lindewei
     * @Description: 我的数据初始化
     */
    @RequestMapping(value = "/initdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result initData(PageInfo pageInfo) throws AlgException {
        try {
            pageInfo.setPageNum(pageInfo.getPageNum()==0?1 : pageInfo.getPageNum());
            pageInfo.setPageSize(pageInfo.getPageSize()==0?10 : pageInfo.getPageSize());
            AlgUser algUser = getUserInfo();
            Map<String,PageInfo> dataMap=new HashMap<String,PageInfo>();
            //我的数据集
            PageInfo<AlgDataSet> dataSetList = dataSetService.getDataSet(algUser.getUsrSn(), pageInfo);
            dataMap.put("algDataSet",dataSetList);
            //我的数据
            PageInfo<AlgData> algDataList = dataSetService.getData(algUser.getUsrSn(), pageInfo);
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
        AlgUser algUser = getUserInfo();
        AlgData algData = new AlgData();
        algData.setUsrSn(algUser.getUsrSn());
        algData.setDataSn(dataSn);
        int count =dataSetService.deleteData(algData);
        return  Result.ok(count);
    }

    /**
     * @author ：Lindewei
     * @Description: 删除当前数据集
     */
    @RequestMapping(value = "/deletedataSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result deleteDataSet(String dataSetSn) throws AlgException {
        logger.info("数据集串号：{}",dataSetSn);
        AlgUser algUser = getUserInfo();
        AlgDataSet algDataSet = new AlgDataSet();
        algDataSet.setDataSetSn(dataSetSn);
        algDataSet.setUsrSn(algUser.getUsrSn());
        int count=dataSetService.deleteDataSet(algDataSet);
        return  Result.ok(count);
    }

    /**
     * @author ：Lindewei
     * @Description: 点击数据集关联查询数据
     */
    @RequestMapping(value = "/showdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result showData(String dataSetSn) throws AlgException {
        logger.info("数据集串号：{}",dataSetSn);
        try{
            AlgUser algUser = getUserInfo();
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
    public Result dataMall(String dataContent,Integer pageNum,Integer pageSize) throws AlgException {
        logger.info("数据搜索名：{}",dataContent);
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        Page<AlgDataDownLoadVo> page = dataSetService.algDataMall(dataContent,pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(page);
        return Result.ok(pageInfo);
    }

}
