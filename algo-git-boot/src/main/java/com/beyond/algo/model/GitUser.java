package com.beyond.algo.model;

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
}
