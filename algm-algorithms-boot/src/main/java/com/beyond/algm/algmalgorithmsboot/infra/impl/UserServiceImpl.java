package com.beyond.algm.algmalgorithmsboot.infra.impl;

/**
 * @author ：zhangchuanzhi
 * @Description:用户登录注册server层
 * @date ：13:26 2017/9/25
 */

import com.beyond.algm.algmalgorithmsboot.infra.GitLabService;
import com.beyond.algm.algmalgorithmsboot.infra.KubernetesService;
import com.beyond.algm.algmalgorithmsboot.infra.UserService;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigEntity;
import com.beyond.algm.common.AESUtil;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.NumCheckUtil;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgAccountMapper;
import com.beyond.algm.mapper.AlgCashHisMapper;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgAccount;
import com.beyond.algm.model.AlgCashHis;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.UserAccountVo;
import com.beyond.algm.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.gitlab.api.models.GitlabUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private AlgUserMapper algUserMapper;

    @Autowired
    private ProjectConfigEntity projectConfigEntity;

    @Autowired
    private AlgAccountMapper algAccountMapper;

    @Autowired
    private AlgCashHisMapper algCashHisMapper;

    @Autowired
    private GitLabService gitLabService;

    @Autowired
    private KubernetesService kubernetesService;

    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户注册功能
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：13:16 2017/9/25
     */
    @Override
    public void createUser(AlgUser user) throws AlgException, Exception {
        if (Assert.isEmpty(user.getPasswd()) || user.getPasswd().length() < 8) {
            String[] checkMessage = {"密码", ""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000007", checkMessage);
        }
        AlgUser algUser = findByUsrCode(user.getUsrCode());
        if (Assert.isNotNULL(algUser)) {
            String[] checkMessage = {"已注册", ""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000002", checkMessage);
        } else {
            String uuid = UUIDUtil.createUUID();
            user.setUsrSn(uuid);
            user.setUpdateDate(new Date());
            user.setCreateDate(new Date());
            logger.info("密码：" + projectConfigEntity.getKeyAES());
            GitUser gitUser = new GitUser();
            gitUser.setPassword(user.getPasswd());
            gitUser.setFullName(user.getUsrCode());
            gitUser.setUsrCode(user.getUsrCode());
            gitUser.setEmail(user.getEmail());
            String passWord = AESUtil.encryptAES(user.getPasswd(), projectConfigEntity.getKeyAES());
            user.setPasswd(passWord);
            //xialf 20171205 update
            GitlabUser gitlabUser = null;
            try {
                gitlabUser = gitLabService.addGitLabUser(gitUser);
                user.setPrivateToken(gitlabUser.getPrivateToken());
                algUserMapper.insert(user);
            } catch (Exception e) {
                //插入数据失败，则删除gitlib中的用户
                if (Assert.isNotNULL(gitlabUser)) {
                    gitLabService.deleteUserByGitUserId(gitlabUser.getId());
                }
                log.error("algUser insert false.", e.getMessage());
                throw new AlgException("BEYOND.ALG.PLATFORM.ADDUSR.0000001", new String[]{"注册用户", user.getUsrCode(), user.getUsrCode()}, e);
            }

            //为用户创建k8s命名空间
            kubernetesService.makeK8sNamespace(user.getUsrCode());
            //为用户创建k8s命名空间下的harbor镜像拉取密钥
            kubernetesService.makeK8sSecretForNamespace(user.getUsrCode());

        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户登录
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：15:26 2017/9/28
     */
    @Override
    public void userLogin(AlgUser user) throws AlgException, Exception {
        String password = user.getPasswd();
        user = algUserMapper.selectUsrname(user.getUsrName());
        // 如果没有这个用户
        if (Assert.isNULL(user)) {
            String[] checkMessage = {"不存在", ""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000002", checkMessage);
        }
        // 对比密码
        String passwordEncryp = AESUtil.decryptAES(user.getPasswd(), projectConfigEntity.getKeyAES());
        if (!passwordEncryp.equals(password)) {
            String[] checkMessage = {"密码", ""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000003", checkMessage);
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户修改密码
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：15:09 2017/10/09
     */
    @Override
    public void changePassword(UserVo user) throws AlgException, Exception {
        // 判断两次输入密码是否一致
        if (!user.getConfirmPassword().equals(user.getNewPassword())) {
            String[] checkMessage = {"两次输入新密码不一致", ""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000004", checkMessage);
        } else {
            // 当两次输入密码一致时候判断输入新密码和原始密码是否一致
            AlgUser algUser = findByUsrCode(user.getUsrCode());
            String passwordEncryp = AESUtil.decryptAES(algUser.getPasswd(), projectConfigEntity.getKeyAES());
            // 判断输入原始密码是否是数据库密码
            if (user.getPasswd().equals(passwordEncryp)) {
                if (user.getNewPassword().equals(passwordEncryp)) {
                    String[] checkMessage = {"原始密码和新密码一致", ""};
                    throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000005", checkMessage);
                }
            } else {
                String[] checkMessage = {"原始密码", ""};
                throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000003", checkMessage);
            }
        }
        String newPassWord = AESUtil.encryptAES(user.getNewPassword(), projectConfigEntity.getKeyAES());
        user.setPasswd(newPassWord);
        user.setUpdateDate(new Date());
        algUserMapper.update(user);
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户信息更改
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：9:15 2017/10/10
     */
    @Override
    public void updateUserInformation(AlgUser user) throws AlgException {
        // 判断邮箱是否合法
        boolean emailFlag = NumCheckUtil.checkEmail(user.getEmail());
        if (!emailFlag) {
            String[] checkMessage = {"邮箱", ""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001", checkMessage);
        }
        // 判断电话
        boolean telFlag = NumCheckUtil.checkTel(user.getTelephone());
        if (!telFlag) {
            String[] checkMessage = {"电话", ""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001", checkMessage);
        }
        // 判断个人主页
        boolean webFlag = NumCheckUtil.checkWebsite(user.getUsrUrl());
        if (!webFlag) {
            String[] checkMessage = {"个人主页", ""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001", checkMessage);
        }
        // 判断名字是否全是中文
        boolean nameFlag = NumCheckUtil.isChineseStr(user.getUsrName());
        if (!nameFlag) {
            String[] checkMessage = {"输入名字", "必须全是中文"};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001", checkMessage);
        }
        // 1：代码邮件 0：短信
        if ("1".equals(user.getNeedNotify())) {
            //多线程发送邮件
        } else {
            // 多线程发送短信
        }
        user.setUpdateDate(new Date());
        algUserMapper.update(user);

    }


    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户修改密码
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：15:09 2017/10/09
     */
    @Override
    public UserAccountVo accountInformation(String usrSn) throws AlgException {
        AlgAccount algAccount = algAccountMapper.selectAccount(usrSn);
        logger.info("用户ID:{}", algAccount.getUsrSn());
        UserAccountVo userAccountVo = new UserAccountVo();
        if (Assert.isNotNULL(algAccount)) {
            AlgCashHis algCashHis = new AlgCashHis();
            algCashHis.setUsrSn(algAccount.getUsrSn());
            // 1代表已经体现
            algCashHis.setStatus("1");
            String cash = algCashHisMapper.selectTotalCash(algCashHis);
            userAccountVo.setCashBal(algAccount.getCashBal());
            userAccountVo.setEarnBal(algAccount.getEarnBal());
            userAccountVo.setFreeBal(algAccount.getFreeBal());
            userAccountVo.setUsrSn(algAccount.getUsrSn());
            userAccountVo.setAccSn(algAccount.getAccSn());
            userAccountVo.setCash(cash);
        }
        return userAccountVo;
    }

    @Override
    public AlgUser findByUsrCode(String usrCode) {

        return algUserMapper.selectUsrCode(usrCode);
    }

    /**
     * 查询一个用户都有哪些组织
     *
     * @param usrCode
     * @return
     */
    public List<AlgUser> ownOrganize(String usrCode) throws AlgException {
        return algUserMapper.selectOwnOrganize(usrCode);
    }

    public AlgUser isOwnerByUsrCode(String orgUsrCode, String curUsrSn) throws AlgException {

        //根据OrgCode，查询该用户信息
        AlgUser algUser = this.findByUsrCode(orgUsrCode);
        //验证是组所有者，还是普通用户
        //传入的组织orgUsrCode与当前登录用户相比较：如果返回true,是组织拥有者 。如果返回false,则是普通用户
        if (algUser.getOwnerId().equals(curUsrSn)) {
            return algUser;
        } else {
            return null;
        }
    }

}

