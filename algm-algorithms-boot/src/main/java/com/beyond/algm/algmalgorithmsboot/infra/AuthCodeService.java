package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.model.AlgAuthCode;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/12 0012 下午 9:48
 */
public interface AuthCodeService {
    void generateKey(AlgAuthCode algAuthCode,String[] addUrl);
    void deleteAuthCode(String acdSn);
    void updateAuthCode(AlgAuthCode algAuthCode,String[] addUrl);
    List<AlgAuthCode> listUserAuthCode(String usrSn);
}
