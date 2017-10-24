package com.beyond.algo.vo;

import com.beyond.algo.common.FileDir;
import com.beyond.algo.common.FileNodes;
import lombok.Data;

import java.util.List;

@Data
public class AlgModuleEditVo {
    private String modId;
    private String modName;
    private String latestCommit;
    private String latestVersion;
    private FileNodes fileNodes;
}
