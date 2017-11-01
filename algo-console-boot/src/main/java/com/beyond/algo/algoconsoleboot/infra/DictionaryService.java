package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgCashTrans;
import com.beyond.algo.model.AlgDic;
import com.beyond.algo.vo.PayRecordVo;

import java.util.List;

public interface DictionaryService {
    List<AlgDic> getDictionarylist(String dicCode)throws AlgException;
}
