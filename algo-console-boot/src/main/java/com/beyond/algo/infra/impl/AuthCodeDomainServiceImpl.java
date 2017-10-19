package com.beyond.algo.infra.impl;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.infra.AuthCodeDomainService;
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
    public Result createAuthCodeDomain(AlgAuthCodeDomain algAuthCodeDomain) {
        String uuid = UUIDUtil.createUUID();
        algAuthCodeDomain.setAddSn(uuid);
        algAuthCodeDomainMapper.insert(algAuthCodeDomain);
        return Result.successResponse();
    }

    @Override
    public Result deleteAuthCodeDomain(String addSn_id) {

        algAuthCodeDomainMapper.deleteByPrimaryKey(addSn_id);
        return Result.successResponse();
    }

    @Override
    public Result updataAuthCodeDomain(AlgAuthCodeDomain algAuthCodeDomain) {

        algAuthCodeDomainMapper.updateByPrimaryKey(algAuthCodeDomain);
        return Result.successResponse();
    }

    @Override
    public Result selectAuthCodeDomain(String addSn_id) {
        AlgAuthCodeDomain authResult = algAuthCodeDomainMapper.selectByPrimaryKey(addSn_id);
        Result result=new Result();
        result.setData(authResult);
        return result;
    }

    @Override
    public List<AlgAuthCodeDomain> listAcdSnUrl(String acdSn) {
        List<AlgAuthCodeDomain> result = algAuthCodeDomainMapper.listAcdSnUrl(acdSn);
        return result;
    }

    @Override
    public Result selectAll(){
        Result result=new Result();
        List<AlgAuthCodeDomain> allUser = algAuthCodeDomainMapper.selectAll();
        result.setData(allUser);
        return result;
    }
}
