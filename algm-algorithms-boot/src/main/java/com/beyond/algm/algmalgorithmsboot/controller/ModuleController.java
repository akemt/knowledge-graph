package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.base.BaseController;
import com.beyond.algm.algmalgorithmsboot.infra.*;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigEntity;
import com.beyond.algm.common.AESUtil;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.Result;
import com.beyond.algm.common.ResultEnum;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgAlgoCategory;
import com.beyond.algm.model.AlgModule;
import com.beyond.algm.model.AlgProgramLang;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgFileReadWriteVo;
import com.beyond.algm.vo.AlgModuleEditVo;
import com.beyond.algm.vo.AlgModuleListVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author ：lindewei
 * @Description:目录tree操作
 * @date ：16:30 2017/10/12
 */
@RestController
@Slf4j
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private JGitService jGitService;
    @Autowired
    private AntApiService antApiService;
    @Autowired
    private ProjectConfigEntity projectConfigEntity;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReadFileService readFileService;
    @Autowired
    private WriteFileService writeFileService;
    @Autowired
    private PublishService publishService;


    //初始化、和返回上一级的目录
    @GetMapping(value = "/{usrCode}/{modId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result initTree(@PathVariable("usrCode") String usrCode, @PathVariable("modId") String modId, String path, String fileName) throws AlgException {
        log.info("get module file tree: usrCode{} and modId {} ", usrCode, modId);
        AlgUser algUser = getUserInfo();
        //权限验证
        authService.isModuleByUser(algUser.getUsrCode(), modId);

        if(Assert.isEmpty(path) && Assert.isEmpty(fileName)){
            path = "";
        }else if(Assert.isNotEmpty(path) && Assert.isEmpty(fileName)){
            path = path;
        }else if ("/".equals(path) && Assert.isNotEmpty(fileName)) {
            // path为"/" 并且 fileName不为空
            path = path + fileName;
        }else {
            // 1、path有目录时候，fileName不为空；2、或者path为"/"，fileName为空
            path = path + File.separator + fileName;
        }

        AlgUser paramsUser = userService.findByUsrCode(usrCode);
        AlgModuleEditVo algModuleEditVo = moduleService.algModule(paramsUser.getIsOrg(),algUser.getUsrCode() , paramsUser.getUsrCode(), paramsUser.getUsrSn(), modId, path);
        return Result.ok(algModuleEditVo);
    }

    /**
     * @param :gitUser
     * @return
     * @Description:删除本地文件同时同步服务器 author:zhangchuanzhi
     */
    @RequestMapping(value = "/{usrCode}/{modId}/del", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result commitAndPushDelAllFiles(@PathVariable("usrCode") String usrCode, @PathVariable("modId") String modId, GitUser gitUser) throws AlgException {
        AlgUser algUser = getUserInfo();
        authService.isModuleByUser(algUser.getUsrCode(), modId);
        gitUser.setModId(modId);
        gitUser.setUsrCode(algUser.getUsrCode());
        jGitService.commitAndPushDelAllFiles(gitUser);
        Result result = Result.successResponse();
        result.setMsg("删除"+ gitUser.getFileName() +"文件成功！");
        return result;

    }

    /**
     * @param :gitUser
     * @return
     * @Description:ant项目进行编译打包同时解压到指定目录并且代码上传git上 author:zhangchuanzhi
     */
    @RequestMapping(value = "/{usrCode}/{modId}/build", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result buildAndUpLoadProject(@PathVariable("usrCode") String usrCode,@PathVariable("modId") String modId) throws AlgException,Exception {
        GitUser gitUser=new GitUser();
        AlgUser algUser = getUserInfo();
        authService.isModuleByUser(algUser.getUsrCode(), modId);
        gitUser.setUsrSn(algUser.getUsrSn());
        gitUser.setModId(modId);
        //   gitUser.setUsrCode(algUser.getUsrCode());
        gitUser.setUsrCode(algUser.getUsrCode());
        gitUser.setPassword(AESUtil.decryptAES(algUser.getPasswd(), projectConfigEntity.getKeyAES()));
        log.debug("用户名字:{},用户密码:{},用户usrSn:{},用户modId:{} ", gitUser.getUsrCode(), gitUser.getPassword(), gitUser.getUsrSn(), gitUser.getModId());
        antApiService.moduleAntBuild(gitUser);
        Result result = Result.successResponse();
        return result;

    }

    /**
     * @author ：lindewei
     * @Description:算法新增
     * @param：User
     */
    @RequestMapping(value = "/module/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result AddAlgorithm(AlgModule algModule) throws AlgException {
        AlgUser algUser = getUserInfo();
        algModule.setUsrSn(algUser.getUsrSn());
        algModule.setCreateSn(algUser.getUsrSn());
        //先保存到数据库
        if(moduleService.isRepeat(algModule.getModId(),algUser.getUsrSn())){
            //无算法重名，可以插入。
            moduleService.addAlgModule(algModule, algUser);
            return Result.successResponse();
        }else {
            //有重名存在。
            String msg = "项目名已经存在，请重新输入！";
            return Result.failure(msg);
        }
    }

    /**
     * @author ：lindewei
     * @Description: 算法新增初始化
     */
    @RequestMapping(value = "/module/add", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result init() {
        try {
            Map map = moduleService.addInit();
            return Result.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：lindewei
     * @Description: 依赖文件读取
     */
    @RequestMapping(value = "/{usrCode}/{modId}/dependRead", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result dependRead(@PathVariable("usrCode") String usrCode, @PathVariable("modId") String modId) throws AlgException {
        log.info("get module file tree: usrCode{} and modId {} ", usrCode, modId);
        AlgUser algUser = getUserInfo();
        //权限验证
        authService.isModuleByUser(algUser.getUsrCode(), modId);
        //定义文件名和路径的变量
        String dependFile = null;
        //判断何种的对应的配置文件
        AlgProgramLang algProgramLang = moduleService.getLanguage(usrCode,modId);
        if(algProgramLang.getLanName().equals("Java")){
            dependFile = "ivy.xml";
        }
        //读取文件
        AlgFileReadWriteVo algFileReadWriteVo = readFileService.readFile(usrCode, modId, null, dependFile);
        return Result.ok(algFileReadWriteVo);
    }

    /**
     * @author ：lindewei
     * @Description: 依赖文件修改保存
     */
    @RequestMapping(value = "/{usrCode}/{modId}/dependWrite", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result dependWrite(@PathVariable("modId") String modId, @PathVariable("usrCode") String usrCode, String fileContent) throws AlgException {
        log.info("depend write module file tree: usrCode:{} and modId: {} and fileContent:{}", usrCode, modId,fileContent);
        AlgUser algUser = getUserInfo();
        //权限验证
        authService.isModuleByUser(algUser.getUsrCode(), modId);
        //定义文件名和路径的变量
        String dependFile = null;
        //判断何种的对应的配置文件
        AlgProgramLang algProgramLang = moduleService.getLanguage(usrCode,modId);
        if(algProgramLang.getLanName().equals("Java")){
            dependFile = "ivy.xml";
        }
        writeFileService.writeFile(algUser.getUsrCode(), modId, null, dependFile, fileContent);//写入文件中，并且保存到路径下。
        return Result.successResponse();
    }

    /**
     * @author ：lindewei
     * @Description: 发布接口
     */
    @RequestMapping(value = "/{usrCode}/{modId}/publish", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result publish(@PathVariable("modId") String modId, @PathVariable("usrCode") String usrCode,String verMark) throws AlgException {
        //权限验证
        AlgUser algUser = getUserInfo();
        authService.isModuleByUser(algUser.getUsrCode(), modId);
        publishService.publishModule(modId,usrCode,verMark);
        return Result.successResponse();
    }

    /**
     * @author ：lindewei
     * @Description: 分类接口
     */
    @RequestMapping(value = "/module/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result category() throws AlgException {
        List<AlgAlgoCategory> AlgAlgoCategory = moduleService.category();
        return Result.ok(AlgAlgoCategory);
    }


    /**
     * 发布接口-发布版本号、计算版权费用
     *
     * @param modId
     * @param usrCode
     * @return
     * @throws AlgException
     * @author xialf
     */
    @GetMapping(value = "/{usrCode}/{modId}/publish", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getAlgModuleVersion(@PathVariable("modId") String modId, @PathVariable("usrCode") String usrCode) throws AlgException {
        //权限验证
        AlgUser algUser = getUserInfo();
        authService.isModuleByUser(algUser.getUsrCode(), modId);
        Map<String, Object> algModuleVersionMap = publishService.getAlgModuleVersion(modId, usrCode);
        return Result.ok(algModuleVersionMap);
    }


    /**
     * 新增组织算法
     * @param algModule
     * @param orgUsrCode
     * @return
     * @throws AlgException
     * @author xialf
     */
    @RequestMapping(value = "/module/addOrgAlg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result AddOrgAlgorithm(AlgModule algModule,String orgUsrCode) throws AlgException {
        log.info("新增组织算法 -Controller(/module/addOrgAlg) : orgUsrCode:{}", orgUsrCode);
        //查询当前登录用户信息
        AlgUser curAlgUser = getUserInfo();
        //验证组所有者，还是普通用户
        //传入的组织orgUsrCode与当前登录用户相比较：如果返回true,是组织拥有者。如果返回false,则是普通用户
        AlgUser algUser = userService.isOwnerByUsrCode(orgUsrCode,curAlgUser.getUsrSn());
        if(Assert.isNULL(algUser)) {
            throw new AlgException("BEYOND.ALG.MODULE.ADD.0000022");
        }
        algModule.setUsrSn(algUser.getUsrSn());
        algModule.setCreateSn(curAlgUser.getUsrSn());
        //把组织编号，封装到AlgModule实体中
        algModule.setOrgUsrCode(orgUsrCode);
        //当前用户实体的isOrg把0-改成组织的IsOrg=1
        curAlgUser.setIsOrg(algUser.getIsOrg());
        //先保存到数据库
        if (moduleService.isRepeat(algModule.getModId(), algUser.getUsrSn())) {
            //algModule-算法实体信息，curAlgUser-当前登录用户实体信息。
            moduleService.addAlgModule(algModule, curAlgUser);
            return Result.successResponse();
        } else {
            throw new AlgException("BEYOND.ALG.MODULE.ADD.0000023");
        }
    }

    /**
     * 编辑算法-初始化组织算法左侧树形结构
     *
     * @param orgUsrCode
     * @param modId
     * @param path
     * @param fileName
     * @return
     * @throws AlgException
     * @author xialf
     */
    @GetMapping(value = "initOrgAlg/{orgUsrCode}/{modId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result initTreeOrgAlgorithm(@PathVariable("orgUsrCode") String orgUsrCode, @PathVariable("modId") String modId, String path, String fileName) throws AlgException {

        log.info("编辑算法-初始化组织算法左侧树形结构 -Controller(/orgUsrCode/addOrgAlg) : orgUsrCode:{} and modId : ", orgUsrCode,modId);
        //查询当前登录用户信息
        AlgUser curAlgUser = getUserInfo();
        //验证组所有者，还是普通用户
        //传入的组织orgUsrCode与当前登录用户相比较：如果返回true,是组织拥有者。如果返回false,则是普通用户
        AlgUser algUser = userService.isOwnerByUsrCode(orgUsrCode,curAlgUser.getUsrSn());
        if(Assert.isNULL(algUser)) {
            throw new AlgException("BEYOND.ALG.MODULE.ADD.0000022");
        }

        if(Assert.isEmpty(path) && Assert.isEmpty(fileName)){
            path = "";
        }else if(Assert.isNotEmpty(path) && Assert.isEmpty(fileName)){
            path = path;
        }else if ("/".equals(path) && Assert.isNotEmpty(fileName)) {
            // path为"/" 并且 fileName不为空
            path = path + fileName;
        }else {
            // 1、path有目录时候，fileName不为空；2、或者path为"/"，fileName为空
            path = path + File.separator + fileName;
        }
        AlgModuleEditVo algModuleEditVo = moduleService.algModule(algUser.getIsOrg(),curAlgUser.getUsrCode(), orgUsrCode, algUser.getUsrSn(), modId, path);
        return Result.ok(algModuleEditVo);
    }

}
