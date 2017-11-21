package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.*;
import com.beyond.algm.vo.PayRecordVo;
import com.beyond.algm.vo.PayVo;

import java.util.List;


/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface PayCashService {
      List<AlgCashTrans> payRecord(PayRecordVo payRecordVo)throws AlgException;
      void buyIntegral(PayVo payVo)throws AlgException;
}
