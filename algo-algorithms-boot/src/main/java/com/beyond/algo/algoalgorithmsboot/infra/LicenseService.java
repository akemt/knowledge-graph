package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgLicense;
import java.util.List;

/**
 * lindewei
 * 协议获取接口
 */
public interface LicenseService {
    List<AlgLicense> selectAlgLicense(String licSn)throws AlgException;
}
