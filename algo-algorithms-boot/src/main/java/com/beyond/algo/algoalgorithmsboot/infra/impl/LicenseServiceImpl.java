package com.beyond.algo.algoalgorithmsboot.infra.impl;

import com.beyond.algo.algoalgorithmsboot.infra.LicenseService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgLicenseMapper;
import com.beyond.algo.model.AlgLicense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LicenseServiceImpl implements LicenseService {

    @Autowired
    private AlgLicenseMapper algLicenseMapper;
    @Override
    public List<AlgLicense> selectAlgLicense(String licSn)throws AlgException{
        List<AlgLicense> license=new ArrayList<AlgLicense>();
        if(licSn=="1"){
            license = algLicenseMapper.selectLicName(licSn);
        }else {
            license = algLicenseMapper.selectAll();
        }
        return license;
    }
}
