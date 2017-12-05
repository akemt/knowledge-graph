package com.beyond.algm.vo;

import com.beyond.algm.model.AlgUser;
import lombok.Data;

import java.math.BigDecimal;
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
    // 创建人用户编码
    private String ownerUsrCode;
    // 创建人中文名称
    private String ownerUsrName;
    // 算法集合
    private List<AlgModuleListVo> moduleList;
    // 组织下用户集合
    private List<AlgUser> memberList;
    // 详情页url

}
