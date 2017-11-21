package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.AuthCodeDomainService;
import com.beyond.algm.mapper.AlgAuthCodeDomainMapper;
import com.beyond.algm.model.AlgAuthCodeDomain;
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
