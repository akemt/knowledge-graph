package com.beyond.algo.vo;

import com.beyond.algo.model.AlgModule;
import lombok.Data;

/**
 * @author ：zhangchuanzhi
 * @Description:算法详情
 * @date ：13:22 2017/10/24
 */
@Data
public class AlgModuleVo extends AlgModule {
    private String licName;
    private String lanName;
    private String verCode;
}