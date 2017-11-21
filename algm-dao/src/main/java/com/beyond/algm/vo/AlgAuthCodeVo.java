package com.beyond.algm.vo;

import com.beyond.algm.model.AlgModelSet;
import lombok.Data;


/**
 * @author ：
 * @Description:
 * @date ：13:52 2017/11/13
 */
@Data
public class AlgAuthCodeVo extends AlgModelSet {

    private String acdSn;

    private String usrSn;

    private String acdName;

    private String acdId;

    private String callFromClient;

    private String callFromBrowser;

    private String restrictUrl;

    private String dataUseType;

    private String createDate;

}
