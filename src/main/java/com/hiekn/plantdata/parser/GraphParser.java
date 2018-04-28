package com.hiekn.plantdata.parser;

import com.hiekn.plantdata.bean.graph.GraphBean;
import com.hiekn.plantdata.bean.graph.RelationBean;
import com.hiekn.plantdata.bean.item.GraphItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphParser {
    public static GraphBean GraphItem2GraphBean(GraphItem graphItem, boolean relationMerge) {
        GraphBean graphBean = new GraphBean();

        List oldEntityList = graphItem.getEntityList();
        List oldRelationList = graphItem.getRelationList();

        //获取新的EntityList
        List entityList = EntityParser.EntityKGItemList2EntityBeanList(oldEntityList);
        graphBean.setEntityList(entityList);

        List relationList = RelationParser.RelationItemList2RelationBeanList(oldRelationList);
        graphBean.setRelationList(relationList);

        if (relationMerge) {
            graphBean = GraphBeanRelationMerge(graphBean);
        }

        return graphBean;
    }

    public static GraphBean GraphBeanRelationMerge(GraphBean graphBean) {
        List relationList = graphBean.getRelationList();

        if (relationList == null) {
            return graphBean;
        }

        Map index = new HashMap(relationList.size());

        for (int i = 0; i < relationList.size(); ++i) {
            RelationBean r = (RelationBean) relationList.get(i);
            String key = r.getAttId() + "_" + r.getFrom() + "_" + r.getTo();

            if (index.containsKey(key)) {
                int savedOnePos = ((Integer) index.get(key)).intValue();
                RelationBean savedOne = (RelationBean) relationList.get(savedOnePos);

                if ((savedOne.getoRInfo() != null) && (r.getoRInfo() != null)) {
                    savedOne = RelationParser.RelationBeanMerge(savedOne, r);
                    relationList.set(savedOnePos, savedOne);
                }

                relationList.remove(i);
                --i;
            } else {
                index.put(key, Integer.valueOf(i));
            }

        }

        return graphBean;
    }
}