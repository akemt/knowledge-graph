package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;

import java.util.Map;

public interface PublishService {

    /**
     * 初始化 项目代码
     * @param lanSn 语言串号
     * @param curUsrCode 当前登录用户编号
     * @param isOrg  1-组织；0-用户
     * @param userCode 用户code
     * @param modId 算法模块Id
     * @param modDesc 项目描述
     * @param version
     * @throws Exception
     */
    void initBootProject(String lanSn,String curUsrCode,String isOrg, String userCode, String modId, String modDesc, String version) throws AlgException;


    /**
     *  算法发布统一入口
     * @param algUser 当前登录用户信息
     * @param modId 算法英文名称
     * @param usrCode 用户英文名称
     * @param verMark H,L,M 对应高、中、低
     * @throws AlgException
     */
    void publishModule(AlgUser algUser,String modId, String usrCode, String verMark) throws AlgException;

    /**
     * 通过参数获取版本号和版权费用
     *
     * @param modId
     * @param usrCode
     * @return
     * @throws AlgException
     * @author xialf
     */
    Map<String, Object> getAlgModuleVersion(String modId, String usrCode) throws AlgException;

}
