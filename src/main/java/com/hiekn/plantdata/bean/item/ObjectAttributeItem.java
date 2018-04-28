/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ObjectAttributeItem extends SimpleEntityItem
/*    */ {
/*    */   private static final long serialVersionUID = -5821625097630756462L;
/*    */   private String attrTimeFrom;
/*    */   private String attrTimeTo;
/*    */   private Map<String, Object> relationDValue;
/*    */   private Map<String, List<SimpleEntityItem>> relationOValues;
/*    */ 
/*    */   public String getAttrTimeFrom()
/*    */   {
/* 19 */     return this.attrTimeFrom;
/*    */   }
/*    */ 
/*    */   public void setAttrTimeFrom(String attrTimeFrom) {
/* 23 */     this.attrTimeFrom = attrTimeFrom;
/*    */   }
/*    */ 
/*    */   public String getAttrTimeTo() {
/* 27 */     return this.attrTimeTo;
/*    */   }
/*    */ 
/*    */   public void setAttrTimeTo(String attrTimeTo) {
/* 31 */     this.attrTimeTo = attrTimeTo;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getRelationDValue() {
/* 35 */     return this.relationDValue;
/*    */   }
/*    */ 
/*    */   public void setRelationDValue(Map<String, Object> relationDValue) {
/* 39 */     this.relationDValue = relationDValue;
/*    */   }
/*    */ 
/*    */   public Map<String, List<SimpleEntityItem>> getRelationOValues() {
/* 43 */     return this.relationOValues;
/*    */   }
/*    */ 
/*    */   public void setRelationOValues(Map<String, List<SimpleEntityItem>> relationOValues) {
/* 47 */     this.relationOValues = relationOValues;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ObjectAttributeItem
 * JD-Core Version:    0.5.3
 */