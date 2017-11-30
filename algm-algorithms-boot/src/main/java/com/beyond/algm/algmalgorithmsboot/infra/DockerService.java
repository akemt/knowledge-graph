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
     * @param modId 算法英文名称
     * @param usrCode 用户英文名称
     * @param algModuleVersion 主要用版本号
     * @throws AlgException
     */
    void makeDockerImage(String modId, String usrCode, AlgModuleVersion algModuleVersion) throws AlgException;
}
