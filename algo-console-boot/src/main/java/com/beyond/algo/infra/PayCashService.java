package com.beyond.algo.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgCashTrans;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.model.UserAccountVo;
import com.beyond.algo.model.UserVo;

import java.util.List;


/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface PayCashService {
      List<AlgCashTrans> payRecord(String usrSn);
}
