package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.DictionaryService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgDicMapper;
import com.beyond.algo.model.AlgDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private AlgDicMapper algDicMapper;
    @Override
    public List<AlgDic> getDictionarylist(String code) throws AlgException {
        List<AlgDic> algDicList=algDicMapper.getDictionarylist(code);
        return algDicList;
    }


}
