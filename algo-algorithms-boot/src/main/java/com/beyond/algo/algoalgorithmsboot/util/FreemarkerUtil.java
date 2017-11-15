package com.beyond.algo.algoalgorithmsboot.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.Map;

public class FreemarkerUtil {

    /**
     * 根据Freemarker模板生成文件
     *
     * @param templatePath     模板目录
     * @param templateFileName 模板文件名
     * @param destPath         生成文件所在目录
     * @param destFileName     生成文件名
     * @param paramMap         参数集合
     */
    public static void createFile(String templatePath, String templateFileName, String destPath, String destFileName, Map<String, Object> paramMap) throws Exception {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("utf-8");
        URL url = new URL(templatePath + templateFileName);
        InputStream ins = url.openStream();
        String content = IOUtils.toString(ins, "UTF-8");
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(templateFileName,content);
        configuration.setTemplateLoader(stringTemplateLoader);
        Template template = configuration.getTemplate(templateFileName);

        String outfile = destPath + File.separator + destFileName;
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile), "UTF-8"));

        try {
            File dir = new File(destPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            template.process(paramMap, out);
        } catch (Exception e) {
            throw e;
        } finally {
            out.close();
        }
    }
}
