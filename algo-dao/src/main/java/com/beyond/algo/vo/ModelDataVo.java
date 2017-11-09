package com.beyond.algo.vo;

import com.beyond.algo.model.AlgModel;
import lombok.Data;

/**
 * @author ：zhangchuanzhi
 * @Description:
 * @date ：9:28 2017/11/9
 */
@Data
public class ModelDataVo extends AlgModel{
    private String dataUrl;
    private int page;
    private int rows;
    private int total;
    private String usrSn;
    private String createDate;
}
