/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SemanticSegWord
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7571338172662037956L;
/*    */   private String word;
/*    */   private Map<Long, Long> entityIdClassIdMap;
/*    */   private List<ServiceEntityItem> entityList;
/*    */   private String attributeName;
/*    */   private List<Integer> attributeIdList;
/*    */   private int type;
/*    */ 
/*    */   public String getWord()
/*    */   {
/* 31 */     return this.word;
/*    */   }
/*    */ 
/*    */   public void setWord(String word) {
/* 35 */     this.word = word;
/*    */   }
/*    */ 
/*    */   public Map<Long, Long> getEntityIdClassIdMap() {
/* 39 */     return this.entityIdClassIdMap;
/*    */   }
/*    */ 
/*    */   public void setEntityIdClassIdMap(Map<Long, Long> entityIdClassIdMap) {
/* 43 */     this.entityIdClassIdMap = entityIdClassIdMap;
/*    */   }
/*    */ 
/*    */   public List<Integer> getAttributeIdList() {
/* 47 */     return this.attributeIdList;
/*    */   }
/*    */ 
/*    */   public void setAttributeIdList(List<Integer> attributeIdList) {
/* 51 */     this.attributeIdList = attributeIdList;
/*    */   }
/*    */ 
/*    */   public int getType() {
/* 55 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(int type) {
/* 59 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public String getAttributeName() {
/* 63 */     return this.attributeName;
/*    */   }
/*    */ 
/*    */   public void setAttributeName(String attributeName) {
/* 67 */     this.attributeName = attributeName;
/*    */   }
/*    */ 
/*    */   public List<ServiceEntityItem> getEntityList() {
/* 71 */     return this.entityList;
/*    */   }
/*    */ 
/*    */   public void setEntityList(List<ServiceEntityItem> entityList) {
/* 75 */     this.entityList = entityList;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.SemanticSegWord
 * JD-Core Version:    0.5.3
 */