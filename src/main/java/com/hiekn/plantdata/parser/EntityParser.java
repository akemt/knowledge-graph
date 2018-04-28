/*     */
package com.hiekn.plantdata.parser;
/*     */

import com.hiekn.plantdata.Entity.Enterprise;
import com.hiekn.plantdata.bean.EntityProfileBean;
import com.hiekn.plantdata.bean.graph.EntityBean;
import com.hiekn.plantdata.bean.item.ServiceAttributeItem;
import com.hiekn.plantdata.bean.item.ServiceEntityItem;
import com.hiekn.plantdata.bean.item.SimpleEntityItem;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*     */
/*     */ public class EntityParser
/*     */ {
    /*     */
    public static List<EntityBean> EntityKGItemList2EntityBeanList(List<Enterprise> oldEntityList)
/*     */ {
/*  20 */
        List entityList = new ArrayList();
/*  21 */
        if ((oldEntityList != null) && (oldEntityList.size() > 0)) {
/*  22 */
            for (int i = 0; i < oldEntityList.size(); ++i) {
/*  23 */
                EntityBean newone = EntityKGItem2EntityBean((Enterprise) oldEntityList.get(i));
/*  24 */
                entityList.add(newone);
/*     */
            }
/*     */
        }
/*  27 */
        return entityList;
/*     */
    }

    /*     */
/*     */
    public static EntityBean EntityKGItem2EntityBean(Enterprise e)
/*     */ {
/*  33 */
        EntityBean entityBean = new EntityBean();
/*  34 */
        entityBean.setId(Long.valueOf(e.getEn_id()));
/*  35 */
        entityBean.setName(e.getName());
/*  36 */
        entityBean.setClassId(e.getClassId());
/*  37 */
        entityBean.setImg(e.getImg());
/*  38 */
        return entityBean;
/*     */
    }

    /*     */
/*     */
    public static EntityBean SimpleItemEntity2EntityBean(SimpleEntityItem e) {
/*  42 */
        EntityBean entityBean = new EntityBean();
/*  43 */
        entityBean.setId(Long.valueOf(e.getId()));
/*  44 */
        entityBean.setName(e.getName());
/*  45 */
        entityBean.setMeaningTag(e.getMeaningTag());
/*     */ 
/*  47 */
        return entityBean;
/*     */
    }

    /*     */
/*     */
    public static EntityProfileBean ServiceEntityItem2EntityProfileBean(ServiceEntityItem e, List<Integer> allowTypes)
/*     */ {
/*  53 */
        EntityProfileBean rs = new EntityProfileBean();
/*     */ 
/*  56 */
        Long id = Long.valueOf(e.getId());
/*  57 */
        String name = e.getName();
/*  58 */
        Integer kgType = Integer.valueOf(e.getType());
/*  59 */
        String meaningTag = e.getMeaningTag();
/*  60 */
        String img = e.getImageUrl();
/*  61 */
        EntityBean self = new EntityBean();
/*  62 */
        rs.setSelf(self);
/*     */ 
/*  64 */
        self.setId(id);
/*  65 */
        self.setName(name);
/*  66 */
        self.setKgType(kgType);
/*  67 */
        self.setMeaningTag(meaningTag);
/*  68 */
        self.setImg(img);
/*  69 */
//        self.addExtra("简介", e.getAbs());
///*     */
///*  71 */
//        String syns = StringUtils.collectionToDelimitedString(e.getSyns(), ",");
///*  72 */
//        self.addExtra("同义词", syns);
/*     */
        ServiceAttributeItem sai;
/*     */
        String k;
/*     */
        List oValues;
/*     */
        Iterator localIterator;
/*     */
        SimpleEntityItem oValue;
/*  75 */
        for (int i = 0; i < e.getAtts().size(); ++i)
/*     */ {
/*  77 */
            sai = (ServiceAttributeItem) e.getAtts().get(i);
/*     */ 
/*  80 */
            if (sai.getType() == 0) {
/*  81 */
                k = sai.getName();
/*  82 */
                String v = sai.getDValue() + " " + sai.getDataUnit();
/*  83 */
                self.addExtra(k, v);
/*     */
            }
/*     */
            else {
/*  86 */
                k = sai.getName();
/*  87 */
                oValues = new ArrayList(sai.getOValues().size());
/*  88 */
                for (localIterator = sai.getOValues().iterator(); localIterator.hasNext(); ) {
                    oValue = (SimpleEntityItem) localIterator.next();
/*  89 */
                    oValues.add(SimpleItemEntity2EntityBean(oValue));
/*     */
                }
/*  91 */
                rs.addAtts(k, oValues);
/*     */
            }
/*     */ 
/*     */
        }
/*     */ 
/*  96 */
        for (int i = 0; i < e.getReAtts().size(); ++i)
/*     */ {
/*  98 */
            sai = (ServiceAttributeItem) e.getReAtts().get(i);
/*     */ 
/* 100 */
            k = sai.getName();
/* 101 */
            oValues = new ArrayList(sai.getOValues().size());
/* 102 */
            for (localIterator = sai.getOValues().iterator(); localIterator.hasNext(); ) {
                oValue = (SimpleEntityItem) localIterator.next();
/* 103 */
                oValues.add(SimpleItemEntity2EntityBean(oValue));
/*     */
            }
/* 105 */
            rs.addAtts(k, oValues);
/*     */
        }
/*     */
        SimpleEntityItem sei;
/* 109 */
        Iterator m;
        if ((e.getPars() != null) && (e.getPars().size() > 0)) {
/* 110 */
            List pars = new ArrayList(e.getPars().size());
/* 111 */
            rs.setPars(pars);
/* 112 */
            for (m = e.getPars().iterator(); m.hasNext(); ) {
                sei = (SimpleEntityItem) m.next();
/* 113 */
                pars.add(SimpleItemEntity2EntityBean(sei));
/*     */
            }
/*     */ 
/*     */
        }
/*     */ 
/* 119 */
        Iterator n;
        if ((e.getSons() != null) && (e.getSons().size() > 0)) {
/* 120 */
            List sons = new ArrayList(e.getSons().size());
/* 121 */
            rs.setSons(sons);
/* 122 */
            for (n = e.getPars().iterator(); n.hasNext(); ) {
                sei = (SimpleEntityItem) n.next();
/* 123 */
                sons.add(SimpleItemEntity2EntityBean(sei));
/*     */
            }
/*     */ 
/*     */
        }
/*     */ 
/* 128 */
        return rs;
/*     */
    }

    /*     */
/*     */
    public static EntityBean ServiceEntityItem2EntityBean(ServiceEntityItem e)
/*     */ {
/* 133 */
        EntityBean self = new EntityBean();
/*     */ 
/* 136 */
        Long id = Long.valueOf(e.getId());
/* 137 */
        String name = e.getName();
/* 138 */
        Integer kgType = Integer.valueOf(e.getType());
/* 139 */
        String meaningTag = e.getMeaningTag();
/* 140 */
        String img = e.getImageUrl();
/*     */ 
/* 142 */
        self.setId(id);
/* 143 */
        self.setName(name);
/* 144 */
        self.setKgType(kgType);
/* 145 */
        self.setMeaningTag(meaningTag);
/* 146 */
        self.setImg(img);
/*     */ 
/* 148 */
        if ((e.getPars() != null) && (e.getPars().size() > 0)) {
/* 149 */
            self.setClassId(((SimpleEntityItem) e.getPars().get(0)).getName());
/*     */
        }
/*     */ 
/* 152 */
        return self;
/*     */
    }
/*     */
}

/* Location:           E:\知识图谱-项目平台\知识图谱\姚远拷贝到软院程序\work\tomcat\webapps\plantdata_console\WEB-INF\classes\
 * Qualified Name:     com.hiekn.plantdata.parser.graph.EntityParser
 * JD-Core Version:    0.5.3
 */