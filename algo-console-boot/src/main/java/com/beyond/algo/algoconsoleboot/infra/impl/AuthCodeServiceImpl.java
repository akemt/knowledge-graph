package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.algoconsoleboot.infra.AuthCodeService;
import com.beyond.algo.mapper.AlgAuthCodeDomainMapper;
import com.beyond.algo.mapper.AlgAuthCodeMapper;
import com.beyond.algo.model.AlgAuthCode;
import com.beyond.algo.model.AlgAuthCodeDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/12 0012 下午 9:57
 */
@Service
public class AuthCodeServiceImpl implements AuthCodeService {

    @Autowired
    private AlgAuthCodeMapper algAuthCodeMapper;
    @Autowired
    private AlgAuthCodeDomainMapper algAuthCodeDomainMapper;

    @Override
    @Transactional
    public Result createAuthCode(AlgAuthCode algAuthCode,String[] addUrl) {
        //主键插入
        String acdSn = UUIDUtil.createUUID();
        algAuthCode.setAcdSn(acdSn);
        //授权码生成，后期看看用别的方法生成串号
        String acdId = "Beyond" + UUIDUtil.getRandomString(15) + "1";
        algAuthCode.setAcdId(acdId);
        //将对象AuthCode插入数据库
        algAuthCodeMapper.insert(algAuthCode);

        //插入默认的Url “algo://*”
        AlgAuthCodeDomain algAuthCodeDomainDefault  = new AlgAuthCodeDomain();
        String addSnDefault = UUIDUtil.createUUID();
        algAuthCodeDomainDefault.setAddSn(addSnDefault);
        algAuthCodeDomainDefault.setAcdSn(algAuthCode.getAcdSn());
        algAuthCodeDomainDefault.setAddUrl("algo://*");
        //Result resultDomainDefault = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomainDefault);
        algAuthCodeDomainMapper.insert(algAuthCodeDomainDefault);

        //插入全部允许调用算法Url的路径
        if(addUrl != null){
            for (String anAddUrl : addUrl) {
                AlgAuthCodeDomain algAuthCodeDomain = new AlgAuthCodeDomain();
                String addSn = UUIDUtil.createUUID();
                algAuthCodeDomain.setAddSn(addSn);
                algAuthCodeDomain.setAcdSn(algAuthCode.getAcdSn());
                algAuthCodeDomain.setAddUrl(anAddUrl);
                //Result resultDomain = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomain);
                algAuthCodeDomainMapper.insert(algAuthCodeDomain);
            }
        }

        return Result.successResponse();
    }

    @Override
    public Result deleteAuthCode(String acdSn) {
        algAuthCodeMapper.deleteByPrimaryKey(acdSn);
        return Result.successResponse();
    }

    @Override
    public Result updateAuthCode(AlgAuthCode algAuthCode) {
        algAuthCodeMapper.updateByPrimaryKey(algAuthCode);
        return Result.successResponse();
    }

    @Override
    public Result selectAuthCode(String acdSn) {
        AlgAuthCode algAuthCode = algAuthCodeMapper.selectByPrimaryKey(acdSn);
        Result result = new Result();
        result.setData(algAuthCode);
        return result;
    }

    @Override
    public List<AlgAuthCode> listUserAuthCode(String usrSn) {
        List<AlgAuthCode> userAllAuthCode = algAuthCodeMapper.selectByUsrSnKey(usrSn);
        return userAllAuthCode;
    }

    @Override
    public Result selectAll() {
        List<AlgAuthCode> allAlgAuth = algAuthCodeMapper.selectAll();
        Result result = new Result();
        result.setData(allAlgAuth);
        return result;
    }
}
