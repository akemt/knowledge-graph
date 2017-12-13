package com.beyond.algm.vo;

import com.beyond.algm.model.AlgData;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AlgDataDownLoadVo extends AlgData {
    private Integer downLoad;
}
