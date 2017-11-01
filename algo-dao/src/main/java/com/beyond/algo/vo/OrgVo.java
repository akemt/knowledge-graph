package com.beyond.algo.vo;

import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgUser;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 组织详情
 */
@Data
public class OrgVo extends AlgUser {

    // API调用次数
    private Long modCallCnt;
    // 积分
    private BigDecimal cashBal;
    // 受益
    private BigDecimal earnBal;
    // 算法集合
    private List<AlgModule> moduleList;
    // 组织下用户集合
    private List<AlgUser> memberList;
}
