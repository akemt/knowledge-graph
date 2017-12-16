package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/1 16:16
 */
public interface KubernetesService {
    /**
     *  kubernetes 制作pod
     * @param modId
     * @param usrCode
     * @param version
     * @throws AlgException
     */
    void makeK8sPod(String modId, String usrCode, String version) throws AlgException;

    /**
     * kubernetes 制作service
     * @param modId
     * @param usrCode
     * @param version
     * @throws AlgException
     */
    void makeK8sService(String modId, String usrCode, String version) throws AlgException;

    /**
     * kubernetes 创建 命名空间
     * @param usrCode
     * @throws AlgException
     */
    void makeK8sNamespace(String usrCode) throws AlgException;

    /**
     * kubernetes 创建 以用户code为命名空间 docker拉取镜像密钥
     * @param usrCode
     * @throws AlgException
     */
    void makeK8sSecretForNamespace(String usrCode) throws AlgException;
}
