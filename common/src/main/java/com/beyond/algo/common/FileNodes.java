package com.beyond.algo.common;

import java.util.List;

public class FileNodes {
    private String currentPath;
    private String patentPath;
    private List<FileNode> nodeList;

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    public List<FileNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<FileNode> nodeList) {
        this.nodeList = nodeList;
    }

    public String getPatentPath() {
        return patentPath;
    }

    public void setPatentPath(String patentPath) {
        this.patentPath = patentPath;
    }
}
