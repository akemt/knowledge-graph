package com.beyond.algo.algoconsoleboot.infra;


import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgAuthCodeDomain;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @Description:接口定义
 * @version Created in：2017/9/28 0028 上午 10:59
 */
public interface AuthCodeDomainService {
    Result createAuthCodeDomain(AlgAuthCodeDomain algAuthCodeDomain);
    Result deleteAuthCodeDomain(String addSn_id);
    Result deleteByAcdSn(String acdSn);
    Result updataAuthCodeDomain(AlgAuthCodeDomain algAuthCodeDomain);
    Result selectAuthCodeDomain(String addSn_id);
    List<AlgAuthCodeDomain> listAcdSnUrl(String acdSn);
    Result selectAll();
}
