package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algm.algmalgorithmsboot.infra.*;
import com.beyond.algm.algmalgorithmsboot.model.*;
import com.beyond.algm.common.*;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.*;
import com.beyond.algm.model.*;
import com.beyond.algm.vo.AlgModuleEditVo;
import com.beyond.algm.vo.AlgModuleVo;
import com.beyond.algm.vo.AlgorithmDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;
    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Autowired
    private AlgModuleVersionMapper algModuleVersionMapper;
    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private JGitService jGitService;
    @Autowired
    private ProjectConfigEntity projectConfigEntity;
    @Autowired
    private GitLabService gitLabService;
    @Autowired
    private AlgDicMapper algDicMapper;
    @Autowired
    private AlgLicenseMapper algLicenseMapper;
    @Autowired
    private AlgUserMapper algUserMapper;
    @Autowired
    private ShowProjectFileService showProjectFileService;
    @Autowired
    private AlgAlgoCategoryMapper algAlgoCategoryMapper;
    @Autowired
    private PathService pathService;

    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void initProject(String strPath, String username,String projectName,String lanSn) throws Exception {
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(lanSn);
        //适配器模式 调用创建算法项目适配器
        ModuleAdapter createModuleAdapter = (ModuleAdapter) AdapterUtil.moduleAdapter(algProgramLang.getLanName());
        createModuleAdapter.createModule(strPath, username,projectName, gitConfigModel, projectConfigModel,active);

    }
//    public void initProject(AlgUser algUser, String projectName,String lanSn) throws Exception {
//        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(lanSn);
//        //适配器模式 调用创建算法项目适配器
//        ModuleAdapter createModuleAdapter = (ModuleAdapter) AdapterUtil.moduleAdapter(algProgramLang.getLanName());
//        createModuleAdapter.createModule(algUser.getUsrCode(), projectName, gitConfigModel, projectConfigModel,active);
//
//    }

    public AlgModule findByUsrSnAndModId(String usrSn, String modId) throws AlgException {
        return algModuleMapper.selectByUsrSnAndModId(usrSn, modId);
    }

    @Override
    public AlgModuleEditVo initModuleTree(AlgUser modUser, String usrCode,String modId, String path,String fileName) throws AlgException {
        AlgModuleEditVo algModuleEditVo = new AlgModuleEditVo();

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

        log.info("current user:{} ", usrCode);
        AlgModule algModule = this.findByUsrSnAndModId(modUser.getUsrSn(), modId);
        log.info("current project id:{} ,name :{} ", algModule.getModId(), algModule.getModName());
        AlgModuleVersion algModuleVersion = getLastVersion(algModule.getModSn());
        log.info("current modSn:{} ", algModule.getModSn());
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(algModule.getLanSn());
        try {
            FileNodes fileNodes = null;
            //项目名称初始化Tree
            // path 为空的情况是，是项目主文件路径

            String basePath = pathService.getModuleBasePath(modUser.getUsrCode(), modId,usrCode,modUser.getIsOrg());
            if (Assert.isEmpty(path)) {
                path = pathService.getModuleMainFilePath(basePath, modId, algModule.getLanSn());
            } else if (path.equals("/")) {
                path = basePath;
            } else {
                path = basePath + File.separator + path;
            }
            fileNodes = showProjectFileService.ShowProjectFile(path, usrCode, modId,modUser.getUsrCode(),modUser.getIsOrg());

            //返回同级目录所有文件和文件夹.
            log.info("current fileNodes {} ", fileNodes.toString());
            algModuleEditVo.setModId(algModule.getModId());
            algModuleEditVo.setModName(algModule.getModName());
            algModuleEditVo.setLatestCommit(algModuleVersion.getLatestCommit());
            algModuleEditVo.setProgramLang(algProgramLang.getLanName());
            algModuleEditVo.setLatestVersion(algModuleVersion.getVerCodeL1() + "." + algModuleVersion.getVerCodeL2() + "." + algModuleVersion.getVerCodeL3());
            algModuleEditVo.setFileNodes(fileNodes);
        } catch (Exception e) {
            log.error("目录树文件读取失败：{},{},{}",path, usrCode, modId,e);
            throw new AlgException("BEYOND.ALG.MODULE.TREE.0000001",new String[]{});
        }
        return algModuleEditVo;
    }

    //获取最后的版本
    @Override
    public AlgModuleVersion getLastVersion(String mod_sn) throws AlgException {
        return algModuleVersionMapper.queryLatestVersion(mod_sn);
    }

    /**
     * @author ：lindw
     * @Description:算法新增
     */
    @Override
    @Transactional(rollbackFor = AlgException.class)
    public Boolean addAlgModule(AlgModuleVo algModule, AlgUser algUser) throws AlgException {

        // 新增算法
        try {
            //模块串号
            algModule.setModSn(UUIDUtil.createUUID());
            algModule.setCreateSn(algUser.getUsrSn());
            //是否要存取网络
            String needWeb = Assert.isEmpty(algModule.getNeedWeb())?"0":algModule.getNeedWeb();
            algModule.setNeedWeb(needWeb);
            //是否要调用其他算法
            String needCallOther = Assert.isEmpty(algModule.getNeedCallOtherName())?"0":algModule.getNeedCallOtherName();
            algModule.setNeedCallOtherName(needCallOther);

            log.info("新增算法，向模块表插入成功，项目串号:{}",algModule.getModSn());
            GitUser gitUser = new GitUser();
            gitUser.setModId(algModule.getModId());
            gitUser.setUsrCode(algUser.getUsrCode());
            gitUser.setPrivateToken(algUser.getPrivateToken());
            gitUser.setPassword(AESUtil.decryptAES(algUser.getPasswd(),projectConfigEntity.getKeyAES()));
            String strUserName = "";


            if(Assert.isNotEmpty(algModule.getUsrSn())){//组所有者-下面的组织创建项目
                AlgUser modUser = algUserMapper.selectByPrimaryKey(algModule.getUsrSn());
                //校验算法是否有重复
                isRepeat(algModule.getUsrSn(),algModule.getModId());
                algModule.setUsrSn(algModule.getUsrSn());

                gitUser.setIsOrg("1");
                //组织编号
                gitUser.setOrgUsrCode(modUser.getUsrCode());

                //在git上组织创建项目
                log.info("开始在git上组织创建项目，git串号:{}",gitUser.getUsrSn());
                gitLabService.createGitLabGroupProject(gitUser);
                log.info("结束在git上组织创建项目，git串号:{}",gitUser.getUsrSn());
                strUserName = modUser.getUsrCode();

            }else{ //当前用户-下创建项目
                isRepeat(algUser.getUsrSn(),algModule.getModId());
                algModule.setUsrSn(algUser.getUsrSn());
                gitUser.setIsOrg("0");
                //在git上创建项目
                log.info("开始在git上创建项目，git串号:{}",gitUser.getUsrSn());
                gitLabService.createGitLabProject(gitUser);
                log.info("结束在git上创建项目，git串号:{}",gitUser.getUsrSn());
                strUserName = algUser.getUsrCode();
            }
            //在服务器本地创建项目 update xialf 20171213
            initProject(gitUser.getFilePath(),strUserName,algModule.getModId(),algModule.getLanSn());
            //commit and push 代码
            String version = jGitService.commitAndPushAllFiles(gitUser);
            log.info("commit and push 代码成功，git串号:{}",gitUser.getUsrSn());

            algModuleMapper.insert(algModule);
            AlgModuleVersion algModuleVersion = new AlgModuleVersion();
            algModuleVersion.setVerSn(UUIDUtil.createUUID());
            algModuleVersion.setCreateDate(new Date());
            algModuleVersion.setVerCodeL1(0);
            algModuleVersion.setVerCodeL2(0);
            algModuleVersion.setVerCodeL3(0);
            algModuleVersion.setLatestCommit(version);
            algModuleVersion.setModSn(algModule.getModSn());
            algModuleVersionMapper.insert(algModuleVersion);
            log.info("增加版本信息成功，版本串号:{}",algModuleVersion.getVerSn());

        } catch (Exception e) {
            log.error("算法新增增加失败。用户串号：{},语言串号：{},分类串号：{},协议串号：{}",algModule.getUsrSn(),algModule.getLanSn(),algModule.getCatSn(),algModule.getLicSn(),e);
            throw new AlgException("BEYOND.ALG.MODULE.ADD.0000002",new String[]{});
        }
        return false;
    }

    /**
     * lindewei
     * 新增算法初始化
     */
    @Override
    public Map addInit() throws AlgException {
        try {
            //初始化
            Map<String,List> map=new HashMap<String,List>();
            //编程语言
            List<AlgProgramLang> algProgramLang =algProgramLangMapper.selectAll();
            map.put("algProgramLang",algProgramLang);
            //协议
            List<AlgLicense> license = algLicenseMapper.selectAll();
            map.put("license",license);
            //网络接入
            String needWeb="need_web";
            List<AlgDic> AccessandCallNw =algDicMapper.getDictionarylist(needWeb);
            map.put("needWeb",AccessandCallNw);
            //调用第三方算法
            String needCallOther="need_call_other";
            List<AlgDic> AccessandCallNco =algDicMapper.getDictionarylist(needCallOther);
            map.put("needCallOther",AccessandCallNco);
            String runEnv="run_env";
            List<AlgDic> RunandEnv =algDicMapper.getDictionarylist(runEnv);
            map.put("runEnv",RunandEnv);
            String iscolony="is_colony";
            List<AlgDic> SingandClu =algDicMapper.getDictionarylist(iscolony);
            map.put("iscolony",SingandClu);
            String standenv="stand_env";
            List<AlgDic> Single =algDicMapper.getDictionarylist(standenv);
            map.put("standenv",Single);
            String gpuenv="gpu_env";
            List<AlgDic> Cluster =algDicMapper.getDictionarylist(gpuenv);
            map.put("gpuenv",Cluster);
            return map;
        } catch (Exception e) {
            throw new AlgException("BEYOND.ALG.MODULE.INIT.0000007",new String[]{});
        }
    }

    /**
     * lindewei
     * 依赖功能：查找语言
     */
    @Override
    public AlgProgramLang getLanguage(String usrCode,String modId) throws AlgException{
        AlgUser algUser = algUserMapper.selectUsrCode(usrCode);
        AlgModule algModule = algModuleMapper.selectByUsrSnAndModId(algUser.getUsrSn(),modId);
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(algModule.getLanSn());
        return algProgramLang;
    }

    /**
     * lindewei
     * 版本接口
     */
    public AlgModuleVersion addVersion(String usrCode,String modId,String verMark)throws AlgException{
        AlgUser algUser = algUserMapper.selectUsrCode(usrCode);
        //给AlgorithmDetailVo赋值
        AlgorithmDetailVo algorithmDetailVo = new AlgorithmDetailVo();
        algorithmDetailVo.setUsrSn(algUser.getUsrSn());
        algorithmDetailVo.setModId(modId);
        //获取最新的版本
        AlgModule algModule = algModuleMapper.selectByUsrSnAndModId(algUser.getUsrSn(),modId);
        AlgModuleVersion algModuleVersion = algModuleVersionMapper.queryLatestVersion(algModule.getModSn());
        log.info("获取最新的版本:current verSn: {} ", algModuleVersion.getVerSn());
        //插入新的版本号
        if("H".equals(verMark)){
            algModuleVersion.setVerCodeL1(algModuleVersion.getVerCodeL1()+1);
            algModuleVersion.setVerCodeL2(0);
            algModuleVersion.setVerCodeL3(0);
        }else if("M".equals(verMark)){
            algModuleVersion.setVerCodeL2(algModuleVersion.getVerCodeL2()+1);
            algModuleVersion.setVerCodeL3(0);
        }else{
            algModuleVersion.setVerCodeL3(algModuleVersion.getVerCodeL3()+1);
        }
        algModuleVersion.setVerSn(UUIDUtil.createUUID());
        algModuleVersion.setCreateDate(new Date());
        try{
            algModuleVersionMapper.insert(algModuleVersion);
        } catch (Exception e){
            throw new AlgException("BEYOND.ALG.MODULE.PUBLISH.0000010",new String[]{});
        }
        return algModuleVersion;
    }

    /**
     * lindewei
     * 分类接口
     */
    public List<AlgAlgoCategory> category()throws AlgException{
        return algAlgoCategoryMapper.selectAll();
    }

    /**
     * @author ：lindewei
     * @Description: 校验算法是否有重复
     */
    public void isRepeat(String usrSn,String modId) throws AlgException{
        //项目modId大写转换小写。
        String strModId = modId.toLowerCase();
        //校验
        int count = algModuleMapper.selectIsRepeat(usrSn,strModId);
        if(count>0){
            throw new AlgException("BEYOND.ALG.MODULE.ADD.00000011");
        }

    }
}
