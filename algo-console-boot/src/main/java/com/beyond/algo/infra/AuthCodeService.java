package com.beyond.algo.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgAuthCode;
import com.sun.org.apache.regexp.internal.RE;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/12 0012 下午 9:48
 */
public interface AuthCodeService {
    Result createAuthCode(AlgAuthCode algAuthCode);
    Result delectAuthCode(String acdSn_id);
    Result updateAuthCode(AlgAuthCode algAuthCode);
    Result selectAuthCode(String acdSn_id);
    Result selectAll();
}
