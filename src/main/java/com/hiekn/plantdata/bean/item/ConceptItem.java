/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ConceptItem
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = -1960506345470797777L;
/*    */   private String id;
/*    */   private String name;
/*    */   private String picName;
/*    */   private List<CubeItemWithChild> parentList;
/*    */   private Map<String, String> childMap;
/*    */   private Map<String, String> instanceMap;
/*    */   private Map<Map<String, String>, List<Map<String, String>>> attributeMap;
/*    */ 
/*    */   public Map<Map<String, String>, List<Map<String, String>>> getAttributeMap()
/*    */   {
/* 23 */     return this.attributeMap;
/*    */   }
/*    */ 
/*    */   public void setAttributeMap(Map<Map<String, String>, List<Map<String, String>>> attributeMap) {
/* 27 */     this.attributeMap = attributeMap; }
/*    */ 
/*    */   public String getId() {
/* 30 */     return this.id; }
/*    */ 
/*    */   public void setId(String id) {
/* 33 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 36 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 39 */     this.name = name; }
/*    */ 
/*    */   public List<CubeItemWithChild> getParentList() {
/* 42 */     return this.parentList; }
/*    */ 
/*    */   public void setParentList(List<CubeItemWithChild> parentList) {
/* 45 */     this.parentList = parentList; }
/*    */ 
/*    */   public String getPicName() {
/* 48 */     return this.picName; }
/*    */ 
/*    */   public void setPicName(String picName) {
/* 51 */     this.picName = picName; }
/*    */ 
/*    */   public Map<String, String> getChildMap() {
/* 54 */     return this.childMap; }
/*    */ 
/*    */   public void setChildMap(Map<String, String> childMap) {
/* 57 */     this.childMap = childMap; }
/*    */ 
/*    */   public Map<String, String> getInstanceMap() {
/* 60 */     return this.instanceMap; }
/*    */ 
/*    */   public void setInstanceMap(Map<String, String> instanceMap) {
/* 63 */     this.instanceMap = instanceMap;
/*    */   }
/*    */ 
/*    */   public Object clone() throws CloneNotSupportedException
/*    */   {
/* 68 */     return super.clone();
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ConceptItem
 * JD-Core Version:    0.5.3
 */