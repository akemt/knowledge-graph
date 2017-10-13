package com.beyond.algo.vo;

import com.beyond.algo.model.AlgRUserModuleCallTrans;
import lombok.Data;

/**
 * @author ：zhangchuanzhi
 * @Description:用户调用查询
 * @date ：13:04 2017/10/12
 */
@Data
public class AlgRUserModuleCallTransVo extends AlgRUserModuleCallTrans {
    private int page;
    private int rows;
    private int total;
    private String modName;
    private String createTime;

}
