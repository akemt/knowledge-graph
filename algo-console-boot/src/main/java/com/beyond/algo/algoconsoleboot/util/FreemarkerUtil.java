package com.beyond.algo.algoconsoleboot.util;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
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

        TemplateLoader templateLoader = new FileTemplateLoader(new File(templatePath));
        configuration.setTemplateLoader(templateLoader);
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
