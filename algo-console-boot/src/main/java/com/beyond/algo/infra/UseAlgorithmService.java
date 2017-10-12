package com.beyond.algo.infra;

import com.beyond.algo.vo.AlgRUserModuleCallTransVo;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:用户使用算法情况
 * @date ：12:56 2017/10/12
 */
public interface UseAlgorithmService {
   List<AlgRUserModuleCallTransVo> algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo);
}
