package com.beyond.algm.algmquartzboot.infra.impl;

import com.beyond.algm.algmquartzboot.infra.ScoreQuartzService;
import com.beyond.algm.mapper.AlgAccountMapper;
import com.beyond.algm.mapper.AlgDicMapper;
import com.beyond.algm.vo.AlgAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreQuartzServiceImpl implements ScoreQuartzService {

    @Autowired
    private AlgAccountMapper algAccountMapper;
    @Autowired
    private AlgDicMapper algDicMapper;

    @Override
    public void coreQuartzs()throws Exception{
        //查询平台收费单价
        String core = algDicMapper.selectDicValue("core","core_default");
        //设定赠送周期时间
        String cycle = algDicMapper.selectDicValue("cycle","cycle_default");

        AlgAccountVo algAccountVo = new AlgAccountVo();
        algAccountVo.setFreeBal(Float.parseFloat(core));
        algAccountVo.setCycle(cycle);

        //赠送的积分置换原值
        int a = algAccountMapper.updateByFreeBal(algAccountVo);
        if(a>0){
            System.out.println(a);
        }

    }
}
