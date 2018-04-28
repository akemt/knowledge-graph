/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TaggingAlgoBean
/*    */ {
/*    */   private int modelType;
/*    */   private List<Long> relationFilter;
/*    */   private int level;
/*    */   private double threshold;
/*    */   private Map<String, Double> weightMap;
/*    */ 
/*    */   public int getModelType()
/*    */   {
/* 35 */     return this.modelType; }
/*    */ 
/*    */   public void setModelType(int modelType) {
/* 38 */     this.modelType = modelType; }
/*    */ 
/*    */   public Map<String, Double> getWeightMap() {
/* 41 */     return this.weightMap; }
/*    */ 
/*    */   public void setWeightMap(Map<String, Double> weightMap) {
/* 44 */     this.weightMap = weightMap;
/*    */   }
/*    */ 
/*    */   public double getThreshold() {
/* 48 */     return this.threshold; }
/*    */ 
/*    */   public void setThreshold(double threshold) {
/* 51 */     this.threshold = threshold; }
/*    */ 
/*    */   public List<Long> getRelationFilter() {
/* 54 */     return this.relationFilter; }
/*    */ 
/*    */   public void setRelationFilter(List<Long> relationFilter) {
/* 57 */     this.relationFilter = relationFilter; }
/*    */ 
/*    */   public int getLevel() {
/* 60 */     return this.level; }
/*    */ 
/*    */   public void setLevel(int level) {
/* 63 */     this.level = level;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.TaggingAlgoBean
 * JD-Core Version:    0.5.3
 */