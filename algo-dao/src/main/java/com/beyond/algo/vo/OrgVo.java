package com.beyond.algo.vo;

import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgUser;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 组织详情
 */
@Data
public class OrgVo extends AlgUser {

    // 总调用次数
    private Long modCallCnt;
    // 积分
    private BigDecimal cashBal;
    // 收益
    private BigDecimal earnBal;
    // 创建人
    private AlgUser owner;
    // 算法集合
    private List<AlgModuleListVo> moduleList;
    // 组织下用户集合
    private List<AlgUser> memberList;
}
