package com.beyond.algo.vo;

import com.beyond.algo.model.AlgModel;
import com.beyond.algo.model.AlgModelSet;
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
