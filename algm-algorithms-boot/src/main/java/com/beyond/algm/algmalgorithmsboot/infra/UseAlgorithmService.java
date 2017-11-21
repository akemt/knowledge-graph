package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgRUserModuleCallTransVo;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:算法情况
 * @date ：12:56 2017/10/12
 */
public interface UseAlgorithmService {
   List<AlgRUserModuleCallTransVo> algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo)throws AlgException;
   List<AlgRUserModuleCallTransVo> earnRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo)throws AlgException;
}
