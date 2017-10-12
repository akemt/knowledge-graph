package com.beyond.algo.vo;

import com.beyond.algo.model.AlgCashTrans;

/**
 * @author ：zhangchuanzhi
 * @Description:充值记录
 * @date ：10:43 2017/10/12
 */
public class PayRecordVo extends AlgCashTrans {

    private int page;
    private int rows;
    // 积分
    private int integral;

    private int total;
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
