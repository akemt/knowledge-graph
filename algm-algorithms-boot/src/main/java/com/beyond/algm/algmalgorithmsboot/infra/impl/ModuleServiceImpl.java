package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algm.algmalgorithmsboot.infra.*;
import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigEntity;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algm.common.*;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.*;
import com.beyond.algm.model.*;
import com.beyond.algm.vo.AlgModuleEditVo;
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

import static com.beyond.algm.common.StringConstant.src;

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
    private GitLibService gitLibService;
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
    public void initProject(AlgUser algUser, String projectName,String lanSn) throws Exception {
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(lanSn);
        //适配器模式 调用创建算法项目适配器
        ModuleAdapter createModuleAdapter = (ModuleAdapter) AdapterUtil.moduleAdapter(algProgramLang.getLanName());
        createModuleAdapter.createModule(algUser.getUsrCode(), projectName, gitConfigModel, projectConfigModel,active);

    }

    public AlgModule findByUsrSnAndModId(String usrSn, String modId) throws AlgException {
        return algModuleMapper.selectByUsrSnAndModId(usrSn, modId);
    }

    @Override
    public AlgModuleEditVo algModule(String usrCode, String usrSn, String modId, String path) throws AlgException {
        AlgModuleEditVo algModuleEditVo = new AlgModuleEditVo();

        log.info("current user:{} ", usrCode);
        AlgModule algModule = this.findByUsrSnAndModId(usrSn, modId);
        log.info("current project id:{} ,name :{} ", algModule.getModId(), algModule.getModName());
        AlgModuleVersion algModuleVersion = getLastVersion(algModule.getModSn());
        log.info("current modSn:{} ", algModule.getModSn());
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(algModule.getLanSn());
        try {
            //项目名称初始化Tree
            // path 为空的情况是，是项目主文件路径
            if (Assert.isEmpty(path)) {
                path = pathService.getModuleMainFilePath(usrCode, modId, algModule.getLanSn());
            } else if (path.equals("/")) {
                path = pathService.getModuleBasePath(usrCode, modId);
            } else {
                path = pathService.getModuleBasePath(usrCode, modId) + File.separator + path;
            }
            //返回同级目录所有文件和文件夹.
            FileNodes fileNodes = showProjectFileService.ShowProjectFile(path, usrCode, modId);
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
    public Boolean addAlgModule(AlgModule algModule,AlgUser algUser) throws AlgException {
        //模块串号
        algModule.setModSn(UUIDUtil.createUUID());
        // 新增算法
        try {
            algModuleMapper.insert(algModule);

            GitUser gitUser = new GitUser();
            gitUser.setModId(algModule.getModId());
            gitUser.setUsrCode(algUser.getUsrCode());
            gitUser.setPassword(AESUtil.decryptAES(algUser.getPasswd(),projectConfigEntity.getKeyAES()));
            //在git上创建项目
            gitLibService.createGitLibProject(gitUser);
            //在服务器本地创建项目
            initProject(algUser,algModule.getModId(),algModule.getLanSn());
            //commit and push 代码
            String version = jGitService.commitAndPushAllFiles(gitUser);

            AlgModuleVersion algModuleVersion = new AlgModuleVersion();
            algModuleVersion.setVerSn(UUIDUtil.createUUID());
            algModuleVersion.setCreateDate(new Date());
            algModuleVersion.setVerCodeL1(0);
            algModuleVersion.setVerCodeL2(0);
            algModuleVersion.setVerCodeL3(0);
            //TODO 未实现版本 等其他信息
            algModuleVersion.setLatestCommit(version);
            algModuleVersion.setModSn(algModule.getModSn());
            algModuleVersionMapper.insert(algModuleVersion);

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
            //集群
            String moduleAccessMode="module_access_mode";
            List<AlgDic> AccessandCall =algDicMapper.getDictionarylist(moduleAccessMode);
            map.put("moduleAccessMode",AccessandCall);
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
        AlgModule algModule = algModuleMapper.selectByUsrSnAndModId(usrCode,modId);
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
    public Boolean isRepeat(String modId,String usrSn) throws AlgException{
        //项目modId大写转换小写。
        String strModId = modId.toLowerCase();
        //校验
        AlgModule algModule = algModuleMapper.selectIsRepeat(strModId,usrSn);
        if(Assert.isEmpty(algModule)){
            //有重名存在
            return false;
        }else {
            //无重名存在
            return true;
        }
    }
}
