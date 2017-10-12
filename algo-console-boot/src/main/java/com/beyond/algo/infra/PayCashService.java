package com.beyond.algo.infra;

import com.beyond.algo.model.*;
import com.beyond.algo.vo.PayRecordVo;

import java.util.List;


/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface PayCashService {
      List<AlgCashTrans> payRecord(PayRecordVo payRecordVo);
}
