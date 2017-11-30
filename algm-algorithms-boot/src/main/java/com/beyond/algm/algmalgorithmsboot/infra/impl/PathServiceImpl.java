package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.PathService;
import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.PublishConfigModel;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.beyond.algm.common.StringConstant.src;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/30 14:28
 */
@Slf4j
@Service
public class PathServiceImpl implements PathService {

    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;
    @Autowired
    private PublishConfigModel publishConfigModel;

    @Override
    public String getModuleBasePath(String usrCode, String modId) throws AlgException {
        //项目名称初始化Tree
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = initBaseFolder(stringBuilder, gitConfigModel.getLocalBasePath(), usrCode, modId);
        return stringBuilder.toString();
    }

    @Override
    public String getModuleMainFilePath(String usrCode, String modId, String lanSn) throws AlgException {
        //项目名称初始化Tree
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = initBaseFolder(stringBuilder, gitConfigModel.getLocalBasePath(), usrCode, modId);
        stringBuilder.append(File.separator);
        stringBuilder.append(src);
        stringBuilder.append(File.separator);
        stringBuilder.append(projectConfigModel.getPackageName());
        stringBuilder.append(File.separator);
        stringBuilder.append(modId);
        stringBuilder.append(File.separator);

        return stringBuilder.toString();
    }

    @Override
    public String getPublishPath(String usrCode, String modId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = initBaseFolder(stringBuilder, publishConfigModel.getLocalBasePath(), usrCode, modId);
        return stringBuilder.toString();
    }

    /**
     * 初始化项目基础目录 例：E:\repo\erniu4\TestJavaK1
     *
     * @param stringBuilder
     * @param basePath
     * @param usrCode
     * @param modId
     * @return
     */
    private StringBuilder initBaseFolder(StringBuilder stringBuilder, String basePath, String usrCode, String modId) {
        stringBuilder.append(basePath);
        stringBuilder.append(File.separator);
        stringBuilder.append(usrCode);
        stringBuilder.append(File.separator);
        stringBuilder.append(modId);
        return stringBuilder;
    }

}
