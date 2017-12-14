package com.beyond.algm.algmdataboot.controller;


import com.beyond.algm.algmdataboot.base.BaseController;
import com.beyond.algm.algmdataboot.infra.AuthService;
import com.beyond.algm.algmdataboot.infra.ModelSetService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.Result;
import com.beyond.algm.common.ResultEnum;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgModel;
import com.beyond.algm.model.AlgModelSet;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgModelSetVo;
import com.beyond.algm.vo.ModelDataVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author ：huangjinqing
 * @Description:数据管理模型模块controller
 * @date ：19:55 2017/10/17
 */
@RestController
@Slf4j
public class ModelSetController  extends BaseController {


    private final static Logger logger = LoggerFactory.getLogger(ModelSetController.class);

    @Autowired
    private ModelSetService modelSetService;

    @Autowired
    private AuthService authService;

    @Value("${model.upload-path}")
    private String modelUploadPath;
    /**
     * @author ：huangjinqing
     * @Description:添加模型集
     * @param：
     * @date ：19:56 2017/10/17
     */
    @RequestMapping(value = "/addModelSet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result addModelSet(String  modelSetName) throws AlgException{
            AlgUser algUser = getUserInfo();
            AlgModelSet modelSet=new AlgModelSet();
            modelSet.setModelSetName(modelSetName);
            modelSet.setUsrSn(algUser.getUsrSn());
          //  modelSet.setUsrSn("3");
            logger.info("模型集名称:{},用户ID:{}", modelSet.getModelSetName(),modelSet.getUsrSn());
            modelSetService.addModelSet(modelSet);
            return Result.successResponse();

    }
    /**
     * @author ：huangjinqing
     * @Description: 删除模型集
     * @param： String modelSetSn
     * @date ：19:56 2017/10/18
     */
    @RequestMapping(value = "/deleteModelSet", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object>  deleteModelSet(String modelSetSn) throws AlgException{
        AlgUser algUser = getUserInfo();
        AlgModel algModel=new AlgModel();
        // 预留方法判断是否是本人
        logger.info("模型集串号：{},用户串号", modelSetSn,algUser.getUsrSn());
        algModel.setModelSetSn(modelSetSn);
        algModel.setUsrSn(algUser.getUsrSn());
       // algModel.setUsrSn("3");
        int count= modelSetService.deleteModelSet(algModel);
        return Result.ok(count);
    }

    /**
     * @author :huangjinqing
     * @Description: 添加模型
     * @param: AlgModel
     * @date: 19:09 2017/10/18
     */
    @RequestMapping(value = "/addModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> addAlgModel(AlgModel algModel) {
        logger.info("模型串号：{}");
        try {
            AlgUser algUser = getUserInfo();
            Result result = modelSetService.addAlgModel(algModel);
            return result;
        } catch (Exception e) {
            logger.info("添加模型失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author :huangjinqing
     * @Description: 删除模型
     * @param: String modelSn
     * @date: 19:09 2017/10/18
     */
    @RequestMapping(value = "/deleteModel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result deleteModel(String modelSn)throws AlgException {
        AlgUser algUser = getUserInfo();
        // 预留方法判断是否是本人
        logger.info("模型串号：{}", modelSn);
        AlgModel algModel =new AlgModel();
        algModel.setUsrSn(algUser.getUsrSn());
        algModel.setModelSn(modelSn);
        int count= modelSetService.deleteModel(algModel);
        return  Result.ok(count);

    }

    /**
     * @param : AlgModel
     * @auther: huangjinqing
     * @Description: 修改模型
     * @date: 10:21 2017/10/19
     */
    @RequestMapping(value = "/modifyModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> modifyModel(AlgModel algModel) {
        logger.info("修改模型信息");
        try {
            Result result = modelSetService.modifyAlgModel(algModel);
            return result;
        } catch (Exception e) {
            logger.info("修改模型失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
    /**
     * @auther: huangjinqing
     * @Description: 查询用户的模型集
     * @date : 14:15 2017/10/21
     */
    @RequestMapping(value = "/queryModelSet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<AlgModelSetVo>> queryModelSet(PageInfo pageInfo) throws AlgException{
        pageInfo.setPageNum(pageInfo.getPageNum()==0?1 : pageInfo.getPageNum());
        pageInfo.setPageSize(pageInfo.getPageSize()==0?10 : pageInfo.getPageSize());
        logger.info("查询用户的模型集");
        AlgUser algUser = getUserInfo();
        PageInfo<AlgModelSetVo> AlgModelSetVoList=modelSetService.queryAlgModelSet(algUser.getUsrSn(), pageInfo);
        return Result.ok(AlgModelSetVoList);
    }

    /**
     * @auther: huangjinqing
     * @Description: 查询用户的模型集,当点击模型集时候
     * @date : 14:18 2017/10/21
     */
    @RequestMapping(value = "/queryModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<ModelDataVo>> queryModel(String modelSetSn,PageInfo pageInfo)throws AlgException{
        pageInfo.setPageNum(pageInfo.getPageNum()==0?1 : pageInfo.getPageNum());
        pageInfo.setPageSize(pageInfo.getPageSize()==0?10 : pageInfo.getPageSize());
        AlgUser algUser = getUserInfo();
        // 预留方法判断是否是本人
        logger.info("模型集串号：{},用户ID:{}",modelSetSn,algUser.getUsrSn());
       AlgModel algModelSet=new AlgModel();
       algModelSet.setUsrSn(algUser.getUsrSn());
       //  algModelSet.setUsrSn("8ec99d9819744a8aa0db947a6be6db4c");
        algModelSet.setModelSetSn(modelSetSn);
        PageInfo<ModelDataVo> mdelDataVoList=modelSetService.queryAlgModel(algModelSet, pageInfo);
        return Result.ok(mdelDataVoList);
    }

    /**
     * @auther: huangjinqing
     * @Description: 上传模型文件
     * @date : 10:40 2017/10/19
     */
    @PostMapping(value = "/modelFileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> fileUpload(@RequestParam("file") MultipartFile file, AlgModel algModel, AlgModelSet modelSet) {
        Result resultVo = new Result();
        if (!file.isEmpty()) {
            if (file.getContentType().contains("jpg")) {
                try {
                    // String modelSetName=modelSetService.
                    // 保存目录为 model/UsrSn/modelSetSn/modelEnName
                    String temp = "model" + File.separator + algModel.getUsrSn() + File.separator + modelSet.getModelSetSn() + File.separator;
                    // 获取文件名
                    String fileName = file.getOriginalFilename();
                    // 获取的扩展名
                    String extensionName = StringUtils.substringAfter(fileName, ".");
                    // 新的文件名 = 模型英文名 +"."文件扩展名
                    String newFileName = algModel.getModelEnName() + "." + extensionName;
                    // 文件路径
                    String filePath = modelUploadPath.concat(temp);
                    //创建新文件
                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 上传到指定目录
                    file.transferTo(dest);
                    resultVo.setCode(ResultEnum.SUCCESS.code);
                    resultVo.setMsg("上传成功！");
                } catch (IOException e) {
                    resultVo.setCode(ResultEnum.UNKNOWN_ERROR.code);
                    resultVo.setMsg("上传失败！");
                }
            } else {
                resultVo.setCode(ResultEnum.PARAM_ERROR.code);
                resultVo.setMsg("上传的文件不是jpg，请重新上传!");
            }
            return resultVo;
        } else {
            resultVo.setCode(ResultEnum.SUCCESS.code);
            resultVo.setMsg("文件为空，上传失败！");
            return resultVo;
        }
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:算法详情的数据模型
     * @param：accSn
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/11/09
     */
    @RequestMapping(value="/{usrCode}/{modId}/modeldata", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result modeldata(@PathVariable("usrCode") String usrCode, @PathVariable("modId") String modId,ModelDataVo modelDataVo ) throws AlgException {
        log.info("查看算法用户:{},算法模块项目名称id:{}",usrCode,modId);
        AlgUser algUser = modelSetService.findByUsrCode(usrCode);
        if(Assert.isNULL(algUser)){
            String[] checkMessage = {"查询结果为空",""};
            throw new AlgException("BEYOND.ALG.MODEL.COMMON.VALID.0000003",checkMessage);
        }
        modelDataVo.setUsrSn(algUser.getUsrSn());
        List<ModelDataVo> mdelDataVoList=modelSetService.queryModelDataSet(modelDataVo);
        return  Result.ok(mdelDataVoList);
    }

}
