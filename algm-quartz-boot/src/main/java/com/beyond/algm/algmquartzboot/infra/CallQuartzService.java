package com.beyond.algm.algmquartzboot.infra;

import com.beyond.algm.exception.AlgException;

public interface CallQuartzService {
    /**
     * 批量插入用户调用记录表
     *
     * @param
     * @return
     */
    void callQuartzs()throws AlgException;
}
