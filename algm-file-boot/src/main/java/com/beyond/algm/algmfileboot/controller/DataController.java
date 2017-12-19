package com.beyond.algm.algmfileboot.controller;

import com.beyond.algm.algmfileboot.base.BaseController;
import com.beyond.algm.algmfileboot.infra.AuthService;
import com.beyond.algm.algmfileboot.infra.DataSetService;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgData;
import com.beyond.algm.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/11 16:12
 */
@RestController
@Slf4j
public class DataController  extends BaseController {
    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private AuthService authService;
    /**
     * @author ：zhangchuanzhi
     * @Description: 数据文件增加
     * @param ：数据集名称：dataSetName，
     */
    @RequestMapping(value = "/dataModuleUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result dataFileUpload(MultipartFile file, String dataSetName , String dataSetUuid) throws AlgException {
        AlgUser algUser = getUserInfo();
        AlgData algData =new AlgData();
        algData.setDataName(file.getOriginalFilename());
        algData.setUsrSn(algUser.getUsrSn());
        algData.setDataSetSn(dataSetUuid);
        // 留存权限接口
        int count =dataSetService.checkFileName(algData);
        AlgData algDataValue= dataSetService.uploadDateSet(file,algUser.getUsrCode(),dataSetName,dataSetUuid,algUser.getUsrSn());
        return Result.ok(algDataValue);
    }


    /**
     * @author ：zhangchuanzhi
     * @Description: 数据下载
     */
    @RequestMapping(value = "/data/{usrCode}/{dataSet}/{fileName}", method = RequestMethod.GET)
    public Result dataDownFile(@PathVariable("usrCode") String usrCode, @PathVariable("dataSet") String dataSet, @PathVariable("fileName") String fileName, HttpServletResponse response) throws AlgException {
        AlgUser algUser = getUserInfo();
        // 权限控制预留接口
        authService.isDataByUser(usrCode,algUser.getUsrCode(),algUser.getUsrSn(),dataSet,fileName);
        dataSetService.downDataUrl(algUser.getUsrSn(), dataSet, fileName,usrCode,response);
        return Result.successResponse();
    }
}
