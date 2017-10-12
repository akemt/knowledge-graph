package com.beyond.algo.vo;

import com.beyond.algo.model.AlgRUserModuleCallTrans;

/**
 * @author ：zhangchuanzhi
 * @Description:用户调用查询
 * @date ：13:04 2017/10/12
 */
public class AlgRUserModuleCallTransVo extends AlgRUserModuleCallTrans {
    private int page;
    private int rows;
    private int total;
    private String modName;
    private String createTime;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
