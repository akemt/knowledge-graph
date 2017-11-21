package com.beyond.algm.algmalgorithmsboot.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="project")
@Data
public class ProjectConfigModel {
    private String packageName;
    private String cloneFiles;
    private String ftlFiles;
}
