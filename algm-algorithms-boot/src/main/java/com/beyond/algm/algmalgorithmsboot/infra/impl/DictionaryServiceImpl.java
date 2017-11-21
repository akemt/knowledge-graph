package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.DictionaryService;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgDicMapper;
import com.beyond.algm.model.AlgDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private AlgDicMapper algDicMapper;
    @Override
    public List<AlgDic> getDictionarylist(String dicCode) throws AlgException {
        List<AlgDic> algDicList=algDicMapper.getDictionarylist(dicCode);
        return algDicList;
    }


}
