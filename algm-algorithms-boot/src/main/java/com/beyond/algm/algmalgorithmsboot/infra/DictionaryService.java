package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgDic;

import java.util.List;

public interface DictionaryService {
    List<AlgDic> getDictionarylist(String dicCode)throws AlgException;
}
