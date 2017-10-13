package com.beyond.algo.vo;

import com.beyond.algo.model.AlgUser;
import lombok.Data;

/**
 * @author ：zhangchuanzhi
 * @Description:确认密码
 * @date ：14:58 2017/10/9
 */
@Data
public class UserVo extends AlgUser {
    String confirmPassword;
    String newPassword;
}