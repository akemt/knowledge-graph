package com.hiekn.plantdata.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Massive on 2016/12/26.
 */
public class TreeBuilder {

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<TreeNode> bulid(List<TreeNode> treeNodes) {

        List<TreeNode> trees = new ArrayList<TreeNode>();

        for (TreeNode treeNode : treeNodes) {

            if ("0".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }

            for (TreeNode it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static List<TreeNode> buildByRecursive(List<TreeNode> treeNodes,long parentId) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodes) {
            if (parentId == treeNode.getParentId()) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static TreeNode findChildren(TreeNode treeNode, List<TreeNode> treeNodes) {
        for (TreeNode it : treeNodes) {
            if (treeNode.getId() == (it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }


    public static void main(String[] args) {

//        TreeNode treeNode1 = new TreeNode("1", "广州", "0",null);
//        TreeNode treeNode2 = new TreeNode("2", "深圳", "0",null);
//
//        TreeNode treeNode3 = new TreeNode("3", "天河区", treeNode1);
//        TreeNode treeNode4 = new TreeNode("4", "越秀区", treeNode1);
//        TreeNode treeNode5 = new TreeNode("5", "黄埔区", treeNode1);
//        TreeNode treeNode6 = new TreeNode("6", "石牌", treeNode3);
//        TreeNode treeNode7 = new TreeNode("7", "百脑汇", treeNode6);
//
//
//        TreeNode treeNode8 = new TreeNode("8", "南山区", treeNode2);
//        TreeNode treeNode9 = new TreeNode("9", "宝安区", treeNode2);
//        TreeNode treeNode10 = new TreeNode("10", "科技园", treeNode8);
//
//
//        List<TreeNode> list = new ArrayList<TreeNode>();
//
//        list.add(treeNode1);
//        list.add(treeNode2);
//        list.add(treeNode3);
//        list.add(treeNode4);
//        list.add(treeNode5);
//        list.add(treeNode6);
//        list.add(treeNode7);
//        list.add(treeNode8);
//        list.add(treeNode9);
//        list.add(treeNode10);
//
//        List<TreeNode> trees = TreeBuilder.bulid(list);
//
//        List<TreeNode> trees_ = TreeBuilder.buildByRecursive(list);

    }

}