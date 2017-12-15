package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/1 16:16
 */
public interface KubernetesService {
    /**
     * kubernetes 制作pod
     *
     * @param modId      算法英文名称
     * @param usrCode    用户英文名称或者组织英文名称
     * @param version    发布的版本号
     * @param curUsrCode 当前用户英文名称
     * @param isOrg      1-组织；0-用户
     * @throws AlgException
     */
    void makeK8sPod(String modId, String usrCode, String version, String curUsrCode, String isOrg) throws AlgException;

    /**
     * kubernetes 制作service
     *
     * @param modId      算法英文名称
     * @param usrCode    用户英文名称或者组织英文名称
     * @param version    发布的版本号
     * @param curUsrCode 当前用户英文名称
     * @param isOrg      1-组织；0-用户
     * @throws AlgException
     */
    void makeK8sService(String modId, String usrCode, String version, String curUsrCode,String isOrg) throws AlgException;

    /**
     * kubernetes 创建 命名空间
     *
     * @param usrCode
     * @throws AlgException
     */
    void makeK8sNamespace(String usrCode) throws AlgException;

    /**
     * kubernetes 创建 以用户code为命名空间 docker拉取镜像密钥
     *
     * @param usrCode
     * @throws AlgException
     */
    void makeK8sSecretForNamespace(String usrCode) throws AlgException;
}
