/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SegmentEntity
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 9166027767569040117L;
/*    */   private List<String> conceptList;
/*    */   private List<String> instanceList;
/*    */   private List<String> attributeList;
/*    */   private List<String> rangeOperatorList;
/*    */   private List<String> fuzzyOperatorList;
/*    */   private List<String> mostOperatorList;
/*    */   private List<String> boolNonList;
/*    */ 
/*    */   public List<String> getAttributeList()
/*    */   {
/* 22 */     return this.attributeList; }
/*    */ 
/*    */   public void setAttributeList(List<String> attributeList) {
/* 25 */     this.attributeList = attributeList; }
/*    */ 
/*    */   public List<String> getBoolNonList() {
/* 28 */     return this.boolNonList; }
/*    */ 
/*    */   public void setBoolNonList(List<String> boolNonList) {
/* 31 */     this.boolNonList = boolNonList; }
/*    */ 
/*    */   public List<String> getConceptList() {
/* 34 */     return this.conceptList; }
/*    */ 
/*    */   public void setConceptList(List<String> conceptList) {
/* 37 */     this.conceptList = conceptList; }
/*    */ 
/*    */   public List<String> getFuzzyOperatorList() {
/* 40 */     return this.fuzzyOperatorList; }
/*    */ 
/*    */   public void setFuzzyOperatorList(List<String> fuzzyOperatorList) {
/* 43 */     this.fuzzyOperatorList = fuzzyOperatorList; }
/*    */ 
/*    */   public List<String> getInstanceList() {
/* 46 */     return this.instanceList; }
/*    */ 
/*    */   public void setInstanceList(List<String> instanceList) {
/* 49 */     this.instanceList = instanceList; }
/*    */ 
/*    */   public List<String> getMostOperatorList() {
/* 52 */     return this.mostOperatorList; }
/*    */ 
/*    */   public void setMostOperatorList(List<String> mostOperatorList) {
/* 55 */     this.mostOperatorList = mostOperatorList; }
/*    */ 
/*    */   public List<String> getRangeOperatorList() {
/* 58 */     return this.rangeOperatorList; }
/*    */ 
/*    */   public void setRangeOperatorList(List<String> rangeOperatorList) {
/* 61 */     this.rangeOperatorList = rangeOperatorList;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.SegmentEntity
 * JD-Core Version:    0.5.3
 */