package com.hiekn.plantdata.util;

import com.hiekn.plantdata.Entity.Enterprise;
import com.hiekn.plantdata.bean.item.RelationItem;
import com.hiekn.plantdata.common.TreeNode;
import org.neo4j.ogm.model.Property;
import org.neo4j.ogm.response.model.RelationshipModel;

import java.util.*;

/**
 * 数组转换
 *
 */
public class ArrayUtils {

    /**
     * List<long> 转Long数组
     *
     * @param list
     * @return
     */
    public static long[] listDataToLong(List<Long> list) {
        long[] longs = new long[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            longs[i] = list.get(i);
        }
        return longs;
    }



    /**
     * List<String> 转String数组
     *
     * @param list
     * @return
     */
    public static String[] listDataToString(List<String> list) {
        String[] longs = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            longs[i] = list.get(i);
        }
        return longs;
    }

    /**
     *  转String数组
     *
     * @param distance
     * @return
     */
    public static String[] intDataToString(Integer distance) {
        String[] longs = new String[1];
        if(distance == 1){
            longs[0] = "*1";
        }else{
            longs[0] = "*1.."+distance;
        }
        longs[0] = longs[0].replaceAll("\"","");
        return longs;
    }


    /**
     * 删除ArrayList中重复元素
     *
     * @param list
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }


    /**
     * 通过Hashset的add方法判断是否已经添加过相同的数据，如果已存在相同的数据则不添加
     * @param arlList
     */
    public static List removeDuplicateEnterprise(List<Enterprise> arlList)
    {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = arlList.iterator(); iter.hasNext(); )
        {
            Enterprise element = (Enterprise)iter.next();
            if (set.add(element.getId()))
                newList.add(element);
        }
        arlList.clear();
        arlList.addAll(newList);
        return arlList;
    }

    public static List removeDuplicateRelationshipModel(List<RelationItem> arlList)
    {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = arlList.iterator(); iter.hasNext(); )
        {
            RelationItem element = (RelationItem)iter.next();
            if (set.add(element.getId()))
                newList.add(element);
        }
        arlList.clear();
        arlList.addAll(newList);
        return arlList;
    }


    //  删除ArrayList中重复元素 
    public static List removeDuplicateList(List<Enterprise> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).toString().equals(list.get(i).toString())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }


    /**
     * 遍历RelationshipModel 值
     * @param properties
     * @param key
     * @return
     */
    public  static Object property(List<Property<String, Object>> properties,String key) {
        Iterator i$ = properties.iterator();

        Property property;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            property = (Property)i$.next();
        } while(!property.getKey().equals(key));

        return property.getValue();
    }


    public static List removeDuplicateTreeNode(List<TreeNode> arlList)
    {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = arlList.iterator(); iter.hasNext(); )
        {
            TreeNode element = (TreeNode)iter.next();
            if (set.add(element.getId()))
                newList.add(element);
        }
        arlList.clear();
        arlList.addAll(newList);
        return arlList;
    }

    /**
     *  获取List 信息中的id
     * @param mapList
     * @return
     */
    public static List<Long>  getMapListToListLong(List<Map<String,String>>  mapList){

        List<Long> longList = new ArrayList<>();
        Map<String,String> map = null;
        for(int i=0;i<mapList.size();i++){
            map = mapList.get(i);
            String strID = map.get("id");
            longList.add(Long.valueOf(strID));
        }
        return longList;
    }
}
