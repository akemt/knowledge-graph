package com.beyond.algm.algmalgorithmsboot.model;

import lombok.Data;

@Data
public class GitUser {
    /**
     * 当前登录用户Code
     */
    private String usrCode;
    /**
     * 当前登录用户密码
     */
    private String password;
    private String usrSn;
    // 模拟令牌
    private String privateToken;
    // 本地仓库路径
    private String path;
    // 用户zip解压目标路径
    private String descDir;
    // 用户全名
    private String fullName;
    // 用户邮箱
    private String email;
    // 项目名称
    private String projectName;
    // gitlab 路径地址
    private String projectRepoURI;
    // 文件路径
    private String filePath;
    // 组织下的项目编号-模块编号
    private String modId;
    /**
     * 当前登录用户下的组织编号
     */
    private String orgUsrCode;
    private String currentPath;
    private String fileName;
    /**
     * 是否为组织：1-是；默认0-不是
     */
    private String isOrg;

}
