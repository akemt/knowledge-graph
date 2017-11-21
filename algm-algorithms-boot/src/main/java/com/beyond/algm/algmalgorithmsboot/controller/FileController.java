package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.base.BaseController;
import com.beyond.algm.algmalgorithmsboot.infra.*;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgFileReadWriteVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：lindewei
 * @Description:文件读写操作
 * @date ：16:30 2017/10/12
 */
@RestController
@EnableAutoConfiguration
@Slf4j
public class FileController extends BaseController {
    @Autowired
    private ReadFileService readFileService;
    @Autowired
    private WriteFileService writeFileService;
    @Autowired
    private ShowProjectFileService showProjectFileService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private AuthService authService;

    // 读取文本
    @RequestMapping(value = "/{usrCode}/{modId}/read", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result read(@PathVariable("modId") String modId, @PathVariable("usrCode") String usrCode, String currentPath, String fileName) throws AlgException {
        AlgUser algUser = getUserInfo();
        authService.isModuleByUser(algUser.getUsrCode(), modId);
        AlgFileReadWriteVo algFileReadWriteVo = readFileService.readFile(algUser.getUsrCode(), modId, currentPath, fileName);
        return Result.ok(algFileReadWriteVo);
    }

    // 写入保存（包括新建）
    @RequestMapping(value = "/{usrCode}/{modId}/write", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result write(@PathVariable("modId") String modId, @PathVariable("usrCode") String usrCode, String currentPath, String fileName, String fileContent) throws AlgException {
        log.info("在编辑页面，新建文件或者修改文件进行保存:{} ", "modId:" + modId, "----currentPath:" + currentPath, "----fileName:" + fileName, "----fileContent:" + fileContent);
        AlgUser algUser = getUserInfo();
        authService.isModuleByUser(algUser.getUsrCode(), modId);
        writeFileService.writeFile(algUser.getUsrCode(), modId, currentPath, fileName, fileContent);//写入文件中，并且保存到路径下。
        return Result.successResponse();
    }
}
