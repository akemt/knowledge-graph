package com.beyond.algm.vo;

import com.beyond.algm.model.AlgCashTrans;
import lombok.Data;

/**
 * @author ：zhangchuanzhi
 * @Description:充值记录
 * @date ：10:43 2017/10/12
 */
@Data
public class PayRecordVo extends AlgCashTrans {

    private int page;
    private int rows;
    // 积分
    private int integral;

    private int total;

    private String createDate;

    private String payDate;

}
