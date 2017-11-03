package com.beyond.algo.algoalgorithmsboot.infra.impl;

import com.beyond.algo.algoalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algo.algoalgorithmsboot.infra.*;
import com.beyond.algo.algoalgorithmsboot.model.GitConfigModel;
import com.beyond.algo.algoalgorithmsboot.model.GitUser;
import com.beyond.algo.algoalgorithmsboot.model.ProjectConfigEntity;
import com.beyond.algo.algoalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algo.common.*;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgModuleVersionMapper;
import com.beyond.algo.mapper.AlgProgramLangMapper;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgModuleVersion;
import com.beyond.algo.model.AlgProgramLang;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgModuleEditVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

import static com.beyond.algo.common.StringConstant.src;

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
    private JGitService jGitService;;

    @Autowired
    private ProjectConfigEntity projectConfigEntity;

    @Autowired
    private GitLibService gitLibService;

    @Override
    public void initProject(AlgUser algUser, String projectName) throws Exception {
        AlgModule algModule = findByUsrSnAndModId(algUser.getUsrSn(), projectName);
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(algModule.getLanSn());
        //适配器模式 调用创建算法项目适配器
        // ModuleAdapter createModuleAdapter = (ModuleAdapter)Class.forName("com.beyond.algo.algoconsoleboot.adapter."+ algProgramLang.getLanName() +"ModuleAdapter").newInstance();
        ModuleAdapter createModuleAdapter = (ModuleAdapter) AdapterUtil.moduleAdapter(algProgramLang.getLanName());
        createModuleAdapter.createModule(algUser.getUsrCode(), projectName, gitConfigModel, projectConfigModel);

    }

    @Autowired
    private ShowProjectFileService showProjectFileService;

    //返回文件的后缀名
    @Override
    public String getModuleMainFilePath(String usrCode, String modId, String lanSn) throws AlgException {
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(lanSn);
        //项目名称初始化Tree
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gitConfigModel.getLocalBasePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(usrCode);
        stringBuilder.append(File.separator);
        stringBuilder.append(modId);
        stringBuilder.append(File.separator);
        stringBuilder.append(src);
        stringBuilder.append(File.separator);
        stringBuilder.append(projectConfigModel.getPackageName());
        stringBuilder.append(File.separator);
        stringBuilder.append(modId);
        stringBuilder.append(File.separator);

        return stringBuilder.toString();
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
                path = this.getModuleMainFilePath(usrCode, modId, algModule.getLanSn());
            } else if (path.equals("/")) {
                path = showProjectFileService.getModuleBasePath(usrCode, modId);
            } else {
                path = showProjectFileService.getModuleBasePath(usrCode, modId) + File.separator + path;
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
            throw new AlgException("fileNodes取得失败，路径path：" + path + "，用户usrCode：" + usrCode + "，模块ID：" + modId + "。", e);
        }
        return algModuleEditVo;
    }

    //获取最后的版本
    @Override
    public AlgModuleVersion getLastVersion(String mod_sn) throws AlgException {
        return algModuleVersionMapper.selectLatestAll(mod_sn);
    }

    /**
     * @author ：lindw
     * @Description:算法新增
     */
    @Override
    @Transactional
    public Boolean addAlgModule(AlgModule algModule,AlgUser algUser) throws AlgException {
        //模块串号
        algModule.setModSn(UUIDUtil.createUUID());
        // 新增算法
        try {
            algModuleMapper.insert(algModule);
            AlgModuleVersion algModuleVersion = new AlgModuleVersion();
            algModuleVersion.setVerSn(UUIDUtil.createUUID());
            algModuleVersion.setCreateDate(new Date());
            algModuleVersion.setVerCodeL1(0);
            algModuleVersion.setVerCodeL2(0);
            algModuleVersion.setVerCodeL3(1);
            //TODO 未实现版本 等其他信息
            algModuleVersion.setLatestCommit("1234567890123456789");
            algModuleVersion.setModSn(algModule.getModSn());
            algModuleVersionMapper.insert(algModuleVersion);

            GitUser gitUser = new GitUser();
            gitUser.setModId(algModule.getModId());
            gitUser.setUsrCode(algUser.getUsrCode());
            gitUser.setPassword(AESUtil.decryptAES(algUser.getPasswd(),projectConfigEntity.getKeyAES()));
            //在git上创建项目
            gitLibService.createGitLibProject(gitUser);
            //在服务器本地创建项目
            initProject(algUser,algModule.getModId());
            //commit and push 代码
            jGitService.commitAndPushAllFiles(gitUser);
        } catch (Exception e) {
            throw new AlgException("新增算法插入失败，用户串号：" + algModule.getUsrSn() + "，语言串号：" + algModule.getLanSn()
                    + "，分类串号：" + algModule.getCatSn() + "，协议串号：" + algModule.getLicSn() + "。", e);
        }
        return false;
    }
}
