package com.hiekn.plantdata.infra.impl;

import com.hiekn.plantdata.bean.graph.*;
import com.hiekn.plantdata.infra.IGraphComputeService;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Table;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GraphComputeServiceImpl implements IGraphComputeService {


    public GraphBean computeStats(GraphBean graphBean, List<GraphStatBean> statConfig) {
        if ((statConfig == null) || (statConfig.size() == 0)) {
            return graphBean;
        }

        for (int i = 0; i < statConfig.size(); ++i) {
            HashMultiset countSet = HashMultiset.create();
            GraphStatBean gst = (GraphStatBean) statConfig.get(i);

            Set entity2Compute = new HashSet();
            for (EntityBean e : graphBean.getEntityList()) {
                if (gst.getType().equals(e.getClassId())) {
                    entity2Compute.add(e.getId());
                }

            }

            for (RelationBean r : graphBean.getRelationList()) {
                if (gst.getAtts().contains(r.getAttId())) {
                    if (entity2Compute.contains(r.getFrom())) {
                        countSet.add(r.getFrom());
                    }
                    if (entity2Compute.contains(r.getTo())) {
                        countSet.add(r.getTo());
                    }
                }

            }

            for (Object entityId : countSet.elementSet()) {
                gst.addRs((Long) entityId, Integer.valueOf(countSet.count(entityId)));
            }

        }

        graphBean.setStats(statConfig);

        return graphBean;
    }


    public GraphBean computeConnects(GraphBean graphBean, List<Long> nodes, Integer distance) {
        if ((graphBean.getEntityList() == null) || (graphBean.getEntityList().size() == 0)) {
            return graphBean;
        }
        if ((graphBean.getRelationList() == null) || (graphBean.getRelationList().size() == 0)) {
            return graphBean;
        }

        if (6 < distance.intValue()) {
            return graphBean;
        }

        if (distance.intValue() % 2 != 0) {
            distance = Integer.valueOf(distance.intValue() + 1);
        }
        distance = Integer.valueOf(distance.intValue() / 2);

        Map relation_1_setp_index = new HashMap();
        List<RelationBean> relationList = graphBean.getRelationList();
        Long from;
        for (RelationBean r : relationList) {
            from = r.getFrom();
            Long to = r.getTo();

            if (relation_1_setp_index.get(from) == null) {
                relation_1_setp_index.put(from, new HashSet());
            }
            ((Set) relation_1_setp_index.get(from)).add(to);

            if (relation_1_setp_index.get(to) == null) {
                relation_1_setp_index.put(to, new HashSet());
            }
            ((Set) relation_1_setp_index.get(to)).add(from);
        }

        Map<PathIndexKey, List<Long>> discrete_path_map = new HashMap<PathIndexKey, List<Long>>();

        for (Long startNode : nodes) {
            for (int depth = 1; depth <= distance.intValue(); ++depth) {
                constructDiscretePaths(startNode, discrete_path_map, relation_1_setp_index, depth);
            }
            System.out.println("-------------------------------------------------------");

            for (PathIndexKey pik : discrete_path_map.keySet()) {
                List mid = (List) discrete_path_map.get(pik);
                System.out.println("depth:" + pik.getDepth() + " path: " + pik.getFrom() + mid.toString() + pik.getTo());
            }
        }

        System.out.println("*******************************************************************");

        Object connectsList = new ArrayList();
        Table node_edge_index = HashBasedTable.create();
        for (RelationBean r : graphBean.getRelationList()) {
            node_edge_index.put(r.getFrom(), r.getTo(), r.getId());
            node_edge_index.put(r.getTo(), r.getFrom(), r.getId());
        }

        for (int pos = 0; pos < nodes.size()-1; pos++) {
            Long pole_node = (Long) nodes.get(pos);

            for (PathIndexKey pik_i : discrete_path_map.keySet()) {
                if (!(pik_i.getFrom().equals(pole_node)))
                    continue;
                for (PathIndexKey pik_j : discrete_path_map.keySet()) {
                    List pik_i_mid = (List) discrete_path_map.get(pik_i);
                    List pik_j_mid = (List) discrete_path_map.get(pik_j);

                    if ((pik_i.getFrom().equals(pik_j.getFrom())) || (!(pik_i.getTo().equals(pik_j.getTo())))) {
                        continue;
                    }
                    System.out.println("Path:" + pik_i.getFrom() + " " + pik_i_mid.toString() + " " + pik_i.getTo() + " " + pik_j_mid.toString() + " " + pik_j.getFrom());

                    Long start = pik_i.getFrom();
                    Long end = pik_j.getFrom();
                    Long mid = pik_i.getTo();

                    PathAGBean pag = new PathAGBean();
                    pag.setStart(start);
                    pag.setEnd(end);

                    List pagNodes = new ArrayList();
                    pag.setNodes(pagNodes);

                    pagNodes.add(start);
                    pagNodes.addAll(pik_i_mid);
                    pagNodes.add(mid);
                    pagNodes.addAll(pik_j_mid);
                    pagNodes.add(end);

                    List pagEdges = new ArrayList();
                    pag.setEdges(pagEdges);

                    for (int npos = 0; npos < pagNodes.size() - 1; ++npos) {
                        Long from1 = (Long) pagNodes.get(npos);
                        Long to = (Long) pagNodes.get(npos + 1);
                        pagEdges.add((String) node_edge_index.get(from1, to));
                    }

                    ((List) connectsList).add(pag);
                }

            }

        }

        graphBean.setConnects((List) connectsList);
        return ((GraphBean) graphBean);
    }

    private void constructDiscretePaths(Long startNode, Map<PathIndexKey, List<Long>> discrete_path_map, Map<Long, Set<Long>> relation_1_setp_index, int depth) {
        Iterator localIterator1 = null;
        if (depth == 1) {
            Set endNodes = (Set) relation_1_setp_index.get(startNode);
            if (Objects.nonNull(endNodes))
                localIterator1 = endNodes.iterator();
            while (true) {
                Long endNode = (Long) localIterator1.next();

                PathIndexKey p = new PathIndexKey(startNode, endNode, Integer.valueOf(1));
                discrete_path_map.put(p, new ArrayList(0));

                if (!(localIterator1.hasNext())) {
                    return;
                }
            }
        }
        PathIndexKey pik;
        PathIndexKey new_pik;
        Map newMap = new HashMap();
        if (depth == 2) {
            newMap = new HashMap();

            for (localIterator1 = discrete_path_map.keySet().iterator(); localIterator1.hasNext(); ) {
                pik = (PathIndexKey) localIterator1.next();

                if (!(pik.getFrom().equals(startNode))) {
                    continue;
                }

                if (pik.getDepth().intValue() != 1) {
                    continue;
                }

                Set endNodes = (Set) relation_1_setp_index.get(pik.getTo());
                if (endNodes == null) {
                    continue;
                }

                for (Object endNode : endNodes) {
                    if (startNode.equals(endNode)) {
                        continue;
                    }

                    new_pik = new PathIndexKey(pik.getFrom(), (Long) endNode, Integer.valueOf(2));
                    newMap.put(new_pik, Arrays.asList(new Long[]{pik.getTo()}));
                }
            }

            discrete_path_map.putAll(newMap);
            return;
        }

        if (depth != 3) {
            return;
        }

        for (Iterator localIterator2 = discrete_path_map.keySet().iterator(); localIterator2.hasNext(); ) {
            pik = (PathIndexKey) localIterator2.next();

            if (pik.getDepth().intValue() != 2) {
                continue;
            }

            if (!(pik.getFrom().equals(startNode))) {
                continue;
            }

            List midPath = (List) discrete_path_map.get(pik);
            Set endNodes = (Set) relation_1_setp_index.get(pik.getTo());

            if (endNodes == null) {
                continue;
            }

            for (Object endNode : endNodes) {
                if (startNode.equals(endNode)) {
                    continue;
                }

                PathIndexKey new_pik1 = new PathIndexKey(pik.getFrom(), (Long) endNode, Integer.valueOf(3));

                List newPath = new ArrayList(midPath);
                newPath.add(pik.getTo());
                newMap.put(new_pik1, newPath);
            }
        }

        discrete_path_map.putAll(newMap);

        return;
    }


    protected class PathIndexKey {
        private Long from;
        private Long to;
        private Integer depth;

        public PathIndexKey(Long paramLong1, Long paramLong2, Integer paramInteger) {
            this.from = paramLong1;
            this.to = paramLong2;
            this.depth = paramInteger;
        }

        public Long getFrom() {
            return this.from;
        }

        public void setFrom(Long from) {
            this.from = from;
        }

        public Long getTo() {
            return this.to;
        }

        public void setTo(Long to) {
            this.to = to;
        }

        public Integer getDepth() {
            return this.depth;
        }

        public void setDepth(Integer depth) {
            this.depth = depth;
        }
    }
}
