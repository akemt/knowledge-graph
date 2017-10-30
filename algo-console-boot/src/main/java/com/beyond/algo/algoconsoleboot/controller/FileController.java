package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.base.BaseController;
import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import com.beyond.algo.algoconsoleboot.infra.ReadFileService;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.algoconsoleboot.infra.WriteFileService;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgFileReadWriteVo;
import com.beyond.algo.vo.AlgModuleEditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author ：lindewei
 * @Description:文件读写操作
 * @date ：16:30 2017/10/12
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/file")
public class FileController extends BaseController {
    @Autowired
    private ReadFileService readFileService;
    @Autowired
    private WriteFileService writeFileService;
    @Autowired
    private ShowProjectFileService showProjectFileService;
    @Autowired
    private ModuleService moduleService;

    // 读取文本
    @RequestMapping(value = "{modId}/read", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result read(@PathVariable("modId") String modId, String currentPath,String fileName) {
        try {
            AlgUser algUser = getUserInfo();
            AlgFileReadWriteVo algFileReadWriteVo = readFileService.readFile(algUser.getUsrCode(), modId, currentPath, fileName);
            return Result.ok(algFileReadWriteVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    // 写入保存（包括新建）
    @RequestMapping(value="{modId}/write", method= RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result write(@PathVariable("modId") String modId, String currentPath,String fileName,String fileContent) {
        AlgFileReadWriteVo algFileReadWriteVo = new AlgFileReadWriteVo();
        try {
            AlgUser algUser = getUserInfo();
            writeFileService.writeFile(algUser.getUsrCode(), modId, currentPath, fileName,fileContent);;//写入文件中，并且保存到路径下。
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.successResponse();
    }
}
