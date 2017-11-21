package com.beyond.algm.vo;

import com.beyond.algm.common.FileNodes;
import lombok.Data;

@Data
public class AlgModuleEditVo {
    private String modId;
    private String modName;
    private String latestCommit;
    private String latestVersion;
    private FileNodes fileNodes;
    private String programLang;
}
