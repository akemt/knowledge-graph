package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.ReadFileService;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.vo.AlgFileReadWriteVo;
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

    @Override
    public AlgFileReadWriteVo readFile(String usrCode, String modId, String path, String fileName) throws Exception{
        AlgFileReadWriteVo algFileReadWriteVo = new AlgFileReadWriteVo();
        String readPath = showProjectFileService.getSplitPath(usrCode,modId) + "/"+path +"/"+ fileName;//正式
        //String readPath = showProjectFileService.getSplitPath(usrCode,modId) + "//"+"src//beyondalgo//TestJava" +"//"+ "TestJava.java";//测试
        File file = new File(readPath);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        algFileReadWriteVo.setReadFile(result.toString());
        return algFileReadWriteVo;
    }
}
