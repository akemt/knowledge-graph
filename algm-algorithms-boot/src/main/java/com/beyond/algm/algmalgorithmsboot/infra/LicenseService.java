package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgLicense;
import java.util.List;

/**
 * lindewei
 * 协议获取接口
 */
public interface LicenseService {
    List<AlgLicense> selectAlgLicense(String licSn)throws AlgException;
}
