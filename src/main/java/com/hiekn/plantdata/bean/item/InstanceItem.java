/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class InstanceItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -5084869789591935746L;
/*    */   private String id;
/*    */   private String name;
/*    */   private String picName;
/*    */   private List<ConceptItem> parentList;
/*    */   private Map<Map<String, String>, List<Map<String, String>>> objectAttributeMap;
/*    */   private Map<Map<String, String>, List<Map<String, String>>> dataAttributeMap;
/*    */ 
/*    */   public String getId()
/*    */   {
/* 22 */     return this.id; }
/*    */ 
/*    */   public void setId(String id) {
/* 25 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 28 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 31 */     this.name = name; }
/*    */ 
/*    */   public String getPicName() {
/* 34 */     return this.picName; }
/*    */ 
/*    */   public void setPicName(String picName) {
/* 37 */     this.picName = picName; }
/*    */ 
/*    */   public List<ConceptItem> getParentList() {
/* 40 */     return this.parentList; }
/*    */ 
/*    */   public void setParentList(List<ConceptItem> parentList) {
/* 43 */     this.parentList = parentList; }
/*    */ 
/*    */   public Map<Map<String, String>, List<Map<String, String>>> getDataAttributeMap() {
/* 46 */     return this.dataAttributeMap;
/*    */   }
/*    */ 
/*    */   public void setDataAttributeMap(Map<Map<String, String>, List<Map<String, String>>> dataAttributeMap) {
/* 50 */     this.dataAttributeMap = dataAttributeMap; }
/*    */ 
/*    */   public Map<Map<String, String>, List<Map<String, String>>> getObjectAttributeMap() {
/* 53 */     return this.objectAttributeMap;
/*    */   }
/*    */ 
/*    */   public void setObjectAttributeMap(Map<Map<String, String>, List<Map<String, String>>> objectAttributeMap) {
/* 57 */     this.objectAttributeMap = objectAttributeMap;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.InstanceItem
 * JD-Core Version:    0.5.3
 */