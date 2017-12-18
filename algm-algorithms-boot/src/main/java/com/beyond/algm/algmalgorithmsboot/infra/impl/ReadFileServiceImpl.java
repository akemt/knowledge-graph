package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.PathService;
import com.beyond.algm.algmalgorithmsboot.infra.ReadFileService;
import com.beyond.algm.algmalgorithmsboot.infra.ShowProjectFileService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgFileReadWriteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
@Service
public class ReadFileServiceImpl implements ReadFileService {
    @Autowired
    private ShowProjectFileService showProjectFileService;
    @Autowired
    private PathService pathService;

    @Override
    public AlgFileReadWriteVo readFile(String modPath, String path, String fileName) throws AlgException {
        AlgFileReadWriteVo algFileReadWriteVo = new AlgFileReadWriteVo();
        String readPath = null;
        try {
            if(Assert.isEmpty(path)){
                readPath = modPath + File.separator + fileName;
            }else {
                readPath = modPath + File.separator +path + File.separator + fileName;
            }
        } catch (Exception e) {
            throw new AlgException("BEYOND.ALG.MODULE.READ.0000008",new String[]{},e);
        }
        File file = new File(readPath);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(s+System.lineSeparator());
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        algFileReadWriteVo.setReadFile(result.toString());
        return algFileReadWriteVo;
    }
}
