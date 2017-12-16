package com.beyond.algm.vo;

import com.beyond.algm.model.AlgModule;
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
    private String url;
    private String dataUrl;
    private String usrCode;
    private long starCnt;
    private long followCnt;
    private long callCnt;
    private String sourceCodeUrl;
    private String callAlgorithmUrl;
    private String isCollection;


}
