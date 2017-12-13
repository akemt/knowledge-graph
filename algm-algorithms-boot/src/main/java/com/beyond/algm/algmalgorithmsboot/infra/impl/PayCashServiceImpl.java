package com.beyond.algm.algmalgorithmsboot.infra.impl;

/**
 * @author ：zhangchuanzhi
 * @Description:用户登录注册server层
 * @date ：13:26 2017/9/25
 */

import com.beyond.algm.algmalgorithmsboot.infra.PayCashService;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgAccountMapper;
import com.beyond.algm.mapper.AlgCashTransMapper;

import com.beyond.algm.mapper.AlgDicMapper;
import com.beyond.algm.model.*;
import com.beyond.algm.vo.PayRecordVo;
import com.beyond.algm.vo.PayVo;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class PayCashServiceImpl implements PayCashService {
    private final static Logger logger = LoggerFactory.getLogger(PayCashServiceImpl.class);
    @Autowired
    private AlgCashTransMapper algCashTransMapper;
    @Autowired
    private AlgAccountMapper algAccountMapper;
    @Autowired
    private AlgDicMapper algDicMapper;

    /**
     * @author ：zhangchuanzhi
     * @Description:用户充值记录
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：15:09 2017/10/09
     */
    @Override
    public PageInfo<AlgCashTrans> payRecord(PayRecordVo payRecordVo, Pageable pageable)throws AlgException {
       //分页处理
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<AlgCashTrans> algCashTransList=algCashTransMapper.payRecord(payRecordVo.getUsrSn());
        return new PageInfo<>(algCashTransList);
    }

    /**
     * @author :lindw
     * @Description:用户注册时，赠送积分
     * @date ：11:32 2017/12/5
     */
    @Override
    public int presentCash(String usrSn, float freeBal)throws AlgException{
        //查询该用户是否在积分表存在。
        if(algAccountMapper.presentCash(usrSn) > 0){
            return 0;
        }
        //积分表对象赋值。
        AlgAccount algAccount = new AlgAccount();
        algAccount.setAccSn(UUIDUtil.createUUID());
        algAccount.setUsrSn(usrSn);
        algAccount.setFreeBal(freeBal);
        algAccount.setFreeSetTime(new Date());
        //往积分表插入该用户，并赠送积分。
        logger.info("注册用户时，积分赠送，积分串号setAccSn ：{}",algAccount.getAccSn());
        int result = algAccountMapper.insert(algAccount);
        return result;
    }

    /**
     * @author :lindw
     * @Description:积分充值
     * @date ：11:32 2017/12/5
     */
    @Override
    @Transactional(rollbackFor = AlgException.class)
    public void recharge(AlgCashTrans algCashTrans,AlgUser algUser)throws AlgException{
            //定义账户对象
            AlgAccount algAccount = new AlgAccount();
            //查询余额
            algAccount = algAccountMapper.selectAccount(algUser.getUsrSn());
            //查兑换率
            String rate = algDicMapper.selectDicValue("rate","rate_default");
            //设置账户钱：余额积分+充值积分
            Float total = algAccount.getCashBal() + algCashTrans.getCashPayAmount() * Float.parseFloat(rate);
            algAccount.setCashBal(total);
        try {
            //更新余额。
            algAccountMapper.updateByPrimaryKey(algAccount);
            //插入充值记录
            algCashTrans.setTraSn(UUIDUtil.createUUID());
            algCashTrans.setUsrSn(algUser.getUsrSn());
            algCashTrans.setOrderId(UUIDUtil.createUUID());//todo 客户订单号以后生成替换。
            algCashTrans.setPayTime(new Date());
            algCashTransMapper.insert(algCashTrans);
        } catch (NumberFormatException e) {
            log.error("充值失败。用户串号：{},账户总额：{},交易串号：{},充值现金额：{}",algUser.getUsrSn(),total,
                    algCashTrans.getTraSn(),algCashTrans.getCashPayAmount(),e);
            throw new AlgException("BEYOND.ALG.RECHARGE.INSERT.0000001",new String[]{});
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description: 购买积分
     * @param：PayVo
     * @Modify By :zhangchuanzhi
     * @date ：15:43 2017/10/16
     */
    @Override
     // 购买积分
    public void buyIntegral(PayVo payVo)throws AlgException {
      // 调用充值接口未完成


    }


}

