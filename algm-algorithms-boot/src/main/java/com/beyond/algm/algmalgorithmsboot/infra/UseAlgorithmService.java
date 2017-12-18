package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgRUserModuleCallTransVo;
import com.github.pagehelper.PageInfo;

/**
 * @author ：zhangchuanzhi
 * @Description:算法情况
 * @date ：12:56 2017/10/12
 */
public interface UseAlgorithmService {
   /**
    *
    * @param algRUserModuleCallTransVo
    * @param pageInfo
    * @return
    * @throws AlgException
    */
   PageInfo<AlgRUserModuleCallTransVo> algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo,PageInfo pageInfo)throws AlgException;

   /**
    *
    * @param algRUserModuleCallTransVo
    * @param pageInfo
    * @return
    * @throws AlgException
    */
   PageInfo<AlgRUserModuleCallTransVo> earnRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo,PageInfo pageInfo)throws AlgException;
}
