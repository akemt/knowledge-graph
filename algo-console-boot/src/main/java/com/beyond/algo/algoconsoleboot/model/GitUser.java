package com.beyond.algo.algoconsoleboot.model;

import lombok.Data;

@Data
public class GitUser {
    // 本地仓库路径
    private String  path;
    // 用户名字
    private String username;
    // 用户密码
    private String password;
    // 用户zip解压目标路径
    private String descDir;
    // 用户全名
    private String fullName;
    // 用户邮箱
    private String email;
    // 项目名称
    private String projectName;
    // gitlib 路径地址
    private String projectRepoURI;
    // 文件路径
    private String filePath;

    private String  modId;
    private String  currentPath;
    private String  fileName;
    private String  usrCode;

}
