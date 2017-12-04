package com.beyond.algm.algmalgorithmsboot.adapter.infra;

import com.beyond.algm.algmalgorithmsboot.model.PublishConfigModel;
import com.beyond.algm.exception.AlgException;

/**
 * @Author: gaohaijun
 * @Description:
 * @Date: 2017/11/23
 */
public interface PublishAdapter {

    /**
     *  发布适配器
     * @param userCode 用户id
     * @param modId 算法id
     * @param modDescription 算法描述
     * @param version 版本号
     * @param publishConfigModel 发布配置项
     * @param active 是否调试
     * @param modPath 源码 服务器路径
     * @param publishPath 发布项目 服务器路径
     * @throws AlgException
     */
    void initBootProject(String userCode, String modId, String modDescription, String version, PublishConfigModel publishConfigModel, String active,String modPath,String publishPath) throws AlgException;
}
