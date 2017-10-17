package com.beyond.algo.infra.impl;

/**
 * @author ：zhangchuanzhi
 * @Description:用户算法使用情况
 * @date ：13:26 2017/10/12
 */




import com.beyond.algo.infra.UseAlgorithmService;
import com.beyond.algo.mapper.AlgRUserModuleCallTransMapper;
import com.beyond.algo.vo.AlgRUserModuleCallTransVo;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UseAlgorithmServiceImpl implements UseAlgorithmService {
    private final static Logger logger = LoggerFactory.getLogger(UseAlgorithmServiceImpl.class);
    @Autowired
    private AlgRUserModuleCallTransMapper algRUserModuleCallTransMapper;
    /**
     * @author ：zhangchuanzhi
     * @Description:用户使用算法情况
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：13:16 2017/10/12
     */
    @Override
    public List<AlgRUserModuleCallTransVo> algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo){
        //分页处理
        PageHelper.startPage(algRUserModuleCallTransVo.getPage(), algRUserModuleCallTransVo.getRows());
        List<AlgRUserModuleCallTransVo> lgRUserModuleCallTransList=algRUserModuleCallTransMapper.selectAlgorithmRecord(algRUserModuleCallTransVo);

        return lgRUserModuleCallTransList;
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户收益情况
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：17:34 2017/10/12
     */
   @Override
    public List<AlgRUserModuleCallTransVo> earnRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo){
        //分页处理
        PageHelper.startPage(algRUserModuleCallTransVo.getPage(), algRUserModuleCallTransVo.getRows());
        List<AlgRUserModuleCallTransVo> lgRUserModuleCallTransList=algRUserModuleCallTransMapper.selectAlgorithmRecord(algRUserModuleCallTransVo);
        return lgRUserModuleCallTransList;
    }
}

