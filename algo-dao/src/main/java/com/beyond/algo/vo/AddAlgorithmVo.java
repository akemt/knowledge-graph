package com.beyond.algo.vo;

import com.beyond.algo.model.AlgModule;
import lombok.Data;

/**
 * @author ：lindw
 * @Description: 算法新增参数
 */
@Data
public class AddAlgorithmVo extends AlgModule {
    String lanName;
    String catName;
    String licName;
}
