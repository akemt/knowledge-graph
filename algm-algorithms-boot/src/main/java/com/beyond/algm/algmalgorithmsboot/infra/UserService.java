package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.UserAccountVo;
import com.beyond.algm.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface UserService {
    /**
     *
     * @param user
     * @throws AlgException
     * @throws Exception
     */
    void createUser(AlgUser user) throws AlgException,Exception;

    /**
     *
     * @param user
     * @throws AlgException
     * @throws Exception
     */
    void userLogin(AlgUser user) throws AlgException,Exception;

    /**
     *
     * @param userVo
     * @throws AlgException
     * @throws Exception
     */
    void changePassword(UserVo userVo) throws AlgException,Exception;

    /**
     *
     * @param user
     * @throws AlgException
     */
    void updateUserInformation(AlgUser user) throws AlgException;

    /**
     *
     * @param accSn
     * @return
     * @throws AlgException
     */
    UserAccountVo accountInformation(String accSn)throws AlgException;

    /**
     * 根据usr_code查找用户
     * @param usrCode
     * @return
     */
    AlgUser findByUsrCode(String usrCode) throws AlgException;

    /**
     * 查询一个用户都有哪些组织
     * @param usrCode
     * @return
     */
    List<Map<String,Object>> ownOrganize(String usrCode) throws AlgException;
    /**
     * 验证是组所有者，还是普通用户
     *
     * @param orgUsrCode
     * @param curUsrSn
     * @return
     * @throws AlgException
     * @author xialf
     */
    AlgUser isOwnerByUsrCode(String orgUsrCode, String curUsrSn) throws AlgException;

}
