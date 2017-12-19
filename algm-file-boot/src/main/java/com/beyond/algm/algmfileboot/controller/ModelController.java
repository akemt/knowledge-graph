package com.beyond.algm.algmfileboot.controller;

import com.beyond.algm.algmfileboot.base.BaseController;
import com.beyond.algm.algmfileboot.infra.AuthService;
import com.beyond.algm.algmfileboot.infra.ModelSetService;
import com.beyond.algm.algmfileboot.infra.UserService;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgAuthCode;
import com.beyond.algm.model.AlgModel;
import com.beyond.algm.model.AlgUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import com.beyond.algm.common.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/11 16:12
 */
@RestController
@Slf4j
public class ModelController extends BaseController {

    @Autowired
    private AuthService authService;
    @Autowired
    private ModelSetService modelSetService;
    @Autowired
    private UserService  userService;
    /**
     * @author ：zhangchuanzhi
     * @Description: 数据文件增加
     * @param：modelName模型名称，modelUuid模型主键
     */
    @RequestMapping(value = "/modelUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result dataFileUpload(MultipartFile file, String modelName , String modelUuid) throws AlgException {
        AlgUser algUser = getUserInfo();
        AlgModel algModel =new AlgModel();
        algModel.setModelName(file.getOriginalFilename());
        algModel.setUsrSn(algUser.getUsrSn());
        algModel.setModelSetSn(modelUuid);
        // 留存权限接口
        int count =modelSetService.checkFileName(algModel);
        AlgModel algModelValue= modelSetService.uploadModelSet(file,algUser.getUsrCode(),modelName,modelUuid,algUser.getUsrSn());
        return Result.ok(algModelValue);
    }

    /**
     * @author ：zhangchuanzhi
     * @Description: 模型下载
     */
    @RequestMapping(value = "/model/{usrCode}/{modelSet}/{fileName:.+}", method = RequestMethod.GET)
    public Result modelDownFile(@PathVariable("usrCode") String usrCode, @PathVariable("modelSet") String modelSet, @PathVariable("fileName") String fileName, HttpServletResponse response) throws AlgException {
        AlgUser algUser = getUserInfo();
        // 权限控制
        authService.isModelByUser( usrCode, algUser.getUsrCode(), algUser.getUsrSn(), modelSet, fileName);
        modelSetService.downModelUrl(algUser.getUsrSn(), modelSet, fileName,usrCode,response);
        return Result.successResponse();
    }


    @RequestMapping(value = "/model/{usrCode}/{modelSet}/{fileName:.+}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result modelDownFileHttpClient(@PathVariable("usrCode") String usrCode, @PathVariable("modelSet") String modelSet,
                                          @PathVariable("fileName") String fileName,
                                          HttpServletRequest request, HttpServletResponse response) throws AlgException {
        String keyValue = request.getHeader("KeyValue");
        if (Assert.isNotEmpty(keyValue)) {
            AlgAuthCode algAuthCode= modelSetService.selectUsr(keyValue);
            AlgUser algUser= userService.findByUsrCode(algAuthCode.getUsrSn());
            authService.isModelByUser( usrCode, algUser.getUsrCode(), algUser.getUsrSn(), modelSet, fileName);
            modelSetService.downModelUrl(algUser.getUsrSn(), modelSet, fileName,usrCode,response);
        }else{
            // 到时候优化
            return Result.failureResponse();
        }

        return Result.successResponse();
    }
}
