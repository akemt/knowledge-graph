package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgDic;

import java.util.List;

public interface DictionaryService {
    List<AlgDic> getDictionarylist(String dicCode)throws AlgException;
}
