package com.beyond.algm.vo;

import com.beyond.algm.model.AlgAccount;
import lombok.Data;

/**
 * @author ：lindw
 * @Description:每周赠送积分
 * @date ：14:58 2017/12/6
 */
@Data
public class AlgAccountVo extends AlgAccount {
    private String cycle;
}
