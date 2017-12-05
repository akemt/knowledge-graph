package com.beyond.algm.algmalgorithmsboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/10/27 11:54
 */
@Configuration
@EnableWebMvc
@Slf4j
public class MvcWebConfig extends WebMvcConfigurerAdapter {

    @Value("spring.profiles.active")
    private String active;
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:error_message_en");
        // 如果是dev环境，10秒刷新一次提示配置文件
        if("dev".equals(active)){
            messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        }
        return messageSource;
    }

}
