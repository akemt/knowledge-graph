package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.algoconsoleboot.infra.AuthCodeDomainService;
import com.beyond.algo.mapper.AlgAuthCodeDomainMapper;
import com.beyond.algo.model.AlgAuthCodeDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/9/29 0029 下午 4:54
 */
@Service
public class AuthCodeDomainServiceImpl implements AuthCodeDomainService {

    @Autowired
    private AlgAuthCodeDomainMapper algAuthCodeDomainMapper;


    @Override
    public List<AlgAuthCodeDomain> listAcdSnUrl(String acdSn) {
        List<AlgAuthCodeDomain> result = algAuthCodeDomainMapper.listAcdSnUrl(acdSn);
        return result;
    }
}
