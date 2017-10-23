package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.model.AlgModule;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 1:11
 */
public interface AlgCatRankService {
    List<AlgModule> listAlg(String catName, String usage);
}
