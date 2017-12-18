package com.beyond.algm.vo;

import com.beyond.algm.model.AlgModel;
import lombok.Data;

/**
 * @author ：zhangchuanzhi
 * @Description:
 * @date ：9:28 2017/11/9
 */
@Data
public class ModelDataVo extends AlgModel{
    private String dataUrl;
    private String usrSn;
    private String createDate;
    private String dataName;
}
