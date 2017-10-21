package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgAuthCode;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/12 0012 下午 9:48
 */
public interface AuthCodeService {
    Result createAuthCode(AlgAuthCode algAuthCode,String[] addUrl);
    Result deleteAuthCode(String acdSn);
    Result updateAuthCode(AlgAuthCode algAuthCode);
    Result selectAuthCode(String acdSn);
    List<AlgAuthCode> listUserAuthCode(String usrSn);
    Result selectAll();
}
