/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SemanticTemplateItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7379115037621041641L;
/*    */   private String template;
/*    */   private List<Long> classId;
/*    */   private int queryType;
/*    */   private int queryValue;
/*    */   private double priority;
/*    */   private List<SemanticTemplateFilter> filterList;
/*    */ 
/*    */   public String getTemplate()
/*    */   {
/* 30 */     return this.template;
/*    */   }
/*    */ 
/*    */   public void setTemplate(String template) {
/* 34 */     this.template = template;
/*    */   }
/*    */ 
/*    */   public List<SemanticTemplateFilter> getFilterList() {
/* 38 */     return this.filterList;
/*    */   }
/*    */ 
/*    */   public void setFilterList(List<SemanticTemplateFilter> filterList) {
/* 42 */     this.filterList = filterList;
/*    */   }
/*    */ 
/*    */   public double getPriority() {
/* 46 */     return this.priority;
/*    */   }
/*    */ 
/*    */   public void setPriority(double priority) {
/* 50 */     this.priority = priority;
/*    */   }
/*    */ 
/*    */   public List<Long> getClassId() {
/* 54 */     return this.classId;
/*    */   }
/*    */ 
/*    */   public void setClassId(List<Long> classId) {
/* 58 */     this.classId = classId;
/*    */   }
/*    */ 
/*    */   public int getQueryType() {
/* 62 */     return this.queryType;
/*    */   }
/*    */ 
/*    */   public void setQueryType(int queryType) {
/* 66 */     this.queryType = queryType;
/*    */   }
/*    */ 
/*    */   public int getQueryValue() {
/* 70 */     return this.queryValue;
/*    */   }
/*    */ 
/*    */   public void setQueryValue(int queryValue) {
/* 74 */     this.queryValue = queryValue;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.SemanticTemplateItem
 * JD-Core Version:    0.5.3
 */