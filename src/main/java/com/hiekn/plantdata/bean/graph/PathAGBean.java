package com.hiekn.plantdata.bean.graph;

import java.util.List;


public class PathAGBean {
    private Long start;
    private Long end;
    private List<String> edges;
    private List<Long> nodes;

    public Long getStart() {
        return this.start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return this.end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public List<String> getEdges() {
        return this.edges;
    }

    public void setEdges(List<String> edges) {
        this.edges = edges;
    }

    public List<Long> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<Long> nodes) {
        this.nodes = nodes;
    }
}