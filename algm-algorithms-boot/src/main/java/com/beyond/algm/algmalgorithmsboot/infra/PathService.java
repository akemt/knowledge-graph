package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/30 14:27
 */
public interface PathService {

    /**
     * 获取当前用户项目为modId的服务器项目根路径
     * @param usrCode ;当
     * @param modId
     * @return
     * @throws Exception
     * @deprecated   当为组织时,则usrCode-\组织编号(名称) \项目名称(编号);modId-用户编号！
     *                当为组拥有者时,则usrCode-用户编号;modId-项目名称(编号) ！
     * @author xialf
     */
    String getModuleBasePath(String usrCode, String modId) throws AlgException ;

    /**
     * 返回项目主文件目录 例：E:\repo\erniu4\TestJavaK1\src\algmarket\TestJavaK1
     * @param usrCode
     * @param modId
     * @param lanSn
     * @return
     * @throws AlgException
     */
    String getModuleMainFilePath(String usrCode, String modId, String lanSn) throws AlgException;

    /**
     * 返回Spring Boot发布项目的用户级别目录 例：E:\alg-publish\gaohaijun
     * @param usrCode
     * @param modId
     * @return
     */
    String getPublishPath(String usrCode, String modId);

}
