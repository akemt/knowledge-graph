package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgModuleVersion;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/24 8:50
 */
public interface DockerService {
    /**
     * 远程操作docker制作镜像
     * @param docTag 版本名称
     * @param targetPath publish 项目目录下的target
     * @return 镜像Id
     * @throws AlgException
     */
    String bulidDockerImage(String docTag,String targetPath) throws AlgException;

    /**
     *  制作Dokcerfile文件在target文件下
     * @param active
     * @param lanName
     * @param targetPath
     * @param jarFileName
     * @throws AlgException
     */
    void makeDockerfile(String active,String lanName,String targetPath,String jarFileName) throws AlgException;

    /**
     * 推送镜像到harbor上
     * @param docTag 版本名称
     * @throws AlgException
     */
    void pushDockerImageToHarbor(String docTag) throws AlgException;

    /**
     *  获取 docker image tag 如erniu3_testjava:0.0.3
     *
     * @param modId  算法英文名称
     * @param usrCode 用户英文名称或者组织英文名称
     * @param version  发布的版本号
     * @param curUsrCode 当前用户英文名称
     * @param isOrg  1-组织；0-用户
     * @return
     */
    String getDockerTag(String modId, String usrCode, String version, String curUsrCode,String isOrg);
}
