package com.beyond.algm.vo;

import com.beyond.algm.model.AlgModelSet;
import lombok.Data;

import java.util.List;

/**
 * @author ：
 * @Description:
 * @date ：13:52 2017/11/13
 */
@Data
public class AlgModelSetVo extends AlgModelSet{

    private List<ModelDataVo> algModelVolist;

}
