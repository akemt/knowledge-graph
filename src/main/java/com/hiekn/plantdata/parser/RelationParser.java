
package com.hiekn.plantdata.parser;

import com.hiekn.plantdata.bean.graph.RelationBean;
import com.hiekn.plantdata.bean.graph.RelationInfoBean;
import com.hiekn.plantdata.bean.item.RelationItem;
import com.hiekn.plantdata.bean.item.ServiceAttributeItem;
import com.hiekn.plantdata.bean.item.ServiceEntityItem;
import com.hiekn.plantdata.bean.item.SimpleEntityItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RelationParser {
    private static final String relation_bs_time_sse_name = "attr_time_from";


    public static List<RelationBean> RelationItemList2RelationBeanList(List<RelationItem> oldRelationList) {

        List relationList = new ArrayList();

        if ((oldRelationList != null) && (oldRelationList.size() > 0)) {
            for (int i = 0; i < oldRelationList.size(); ++i) {

                RelationBean newone = RelationItem2RelationBean((RelationItem) oldRelationList.get(i));

                relationList.add(newone);

            }

        }

        return relationList;

    }


    public static RelationBean RelationItem2RelationBean(RelationItem e) {
        RelationBean relationBean = new RelationBean();

        relationBean.setId(e.getId());
        relationBean.setFrom(Long.valueOf(e.getFromId()));
        relationBean.setTo(Long.valueOf(e.getToId()));
        relationBean.setAttId(e.getAttId());
        relationBean.setAttName(e.getAttName());

        List attExtObjInfoList = e.getAttExtObjInfoList();

        if ((attExtObjInfoList != null) && (attExtObjInfoList.size() > 0)) {
            RelationInfoBean objectRelationInfoBean = ServiceEntityItem2RelationInfo(attExtObjInfoList, e.getId());
            relationBean.addrObjectInfo(objectRelationInfoBean);
        }

        Map infoMap = e.getInfoMap();
        if ((infoMap != null) && (infoMap.size() > 0)) {
            RelationInfoBean numericalRelationInfoBean = new RelationInfoBean();

            numericalRelationInfoBean.setId(e.getId());

            for (Object k : infoMap.keySet()) {
                String v = (String) infoMap.get(k);

                if ("attr_time_from".equals(k)) {
                    relationBean.addBsTime(v);
                } else {
                    numericalRelationInfoBean.addKv(k, v);
                }

            }

            relationBean.addrNumInfo(numericalRelationInfoBean);
        }

        return relationBean;
    }


    public static RelationInfoBean ServiceEntityItem2RelationInfo(List<ServiceEntityItem> seiList, String relationId) {

        RelationInfoBean relationInfoBean = new RelationInfoBean();


        for (int j = 0; j < seiList.size(); ++j) {

            ServiceEntityItem e = (ServiceEntityItem) seiList.get(j);


            relationInfoBean.setId(relationId);


            if ((e.getPars() != null) && (e.getPars().size() > 0)) {

                String k = ((SimpleEntityItem) e.getPars().get(0)).getName();

                String v = e.getName();

                relationInfoBean.addKv(k, v);

            }


            List attList = e.getAtts();


            if ((attList == null) || (attList.size() <= 0))
                continue;

            for (int i = 0; i < attList.size(); ++i) {

                ServiceAttributeItem oldAtt = (ServiceAttributeItem) attList.get(i);

                String k = oldAtt.getName();

                switch (oldAtt.getType()) {

                    case 0:

                        String v = oldAtt.getDValue();

                        relationInfoBean.addKv(k, v);

                        break;

                    case 1:

                        String target = "";

                        List<SimpleEntityItem> sList = oldAtt.getOValues();

                        if (Objects.nonNull(sList)) {

                            for (SimpleEntityItem s : sList) {

                                target = target + s.getName() + " ";

                            }

                        }

                        relationInfoBean.addKv(k, target.trim());

                }


            }


        }


        return relationInfoBean;

    }


    public static RelationBean RelationBeanMerge(RelationBean savedOne, RelationBean one) {

        savedOne.getoRInfo().addAll(one.getoRInfo());

        savedOne.getnRInfo().addAll(one.getnRInfo());

        if (savedOne.getBsTime() != null) {

            savedOne.getBsTime().addAll(one.getBsTime());

        }

        return savedOne;

    }

}