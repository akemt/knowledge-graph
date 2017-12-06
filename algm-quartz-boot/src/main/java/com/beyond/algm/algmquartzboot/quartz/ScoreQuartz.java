package com.beyond.algm.algmquartzboot.quartz;

import com.beyond.algm.algmquartzboot.infra.ScoreQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class ScoreQuartz {
    @Autowired
    private ScoreQuartzService scoreQuartzService;

    //每1秒执行一次
    @Scheduled(cron = "*/1 * * * * * ")
    public void reportCurrentByCron(){
        try {
            scoreQuartzService.coreQuartzs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
