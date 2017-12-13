package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgRUserModuleCallTransVo;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:算法情况
 * @date ：12:56 2017/10/12
 */
public interface UseAlgorithmService {
   PageInfo<AlgRUserModuleCallTransVo> algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo, Pageable pageable)throws AlgException;
   PageInfo<AlgRUserModuleCallTransVo> earnRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo, Pageable pageable)throws AlgException;
}
