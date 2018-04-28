package com.hiekn.plantdata.common;

import java.util.List;

/**
 * Created by Massive on 2016/12/26.
 */
public class TreeNode {

    private long id;

    private long parentId;

    private String name;

    private List<String> value;

    private List<TreeNode> children;

    public TreeNode(long id, String name, long parentId,List<String> value) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.value = value;
    }

    public TreeNode(long id, String name, TreeNode parent,List<String> value) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
        this.value = value;
    }


    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", children=" + children +
                '}';
    }

}