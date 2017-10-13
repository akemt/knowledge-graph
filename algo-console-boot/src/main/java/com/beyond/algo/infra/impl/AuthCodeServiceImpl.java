package com.beyond.algo.infra.impl;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.infra.AuthCodeService;
import com.beyond.algo.mapper.AlgAuthCodeMapper;
import com.beyond.algo.model.AlgAuthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/12 0012 下午 9:57
 */
@Service
public class AuthCodeServiceImpl implements AuthCodeService {

    @Autowired
    private AlgAuthCodeMapper algAuthCodeMapper;

    @Override
    public Result createAuthCode(AlgAuthCode algAuthCode) {
        String uuid = UUIDUtil.creatUUID();
        algAuthCode.setAcdId(uuid);
        algAuthCodeMapper.insert(algAuthCode);
        return Result.successResponse();
    }

    @Override
    public Result deleteAuthCode(String acdSn_id) {
        algAuthCodeMapper.deleteByPrimaryKey(acdSn_id);
        return Result.successResponse();
    }

    @Override
    public Result updateAuthCode(AlgAuthCode algAuthCode) {
        algAuthCodeMapper.updateByPrimaryKey(algAuthCode);
        return Result.successResponse();
    }

    @Override
    public Result selectAuthCode(String acdSn_id) {
        algAuthCodeMapper.selectByPrimaryKey(acdSn_id);
        return Result.successResponse();
    }

    @Override
    public Result selectAll() {
        algAuthCodeMapper.selectAll();
        return null;
    }
}
