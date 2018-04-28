/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ImportEntityItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3567253961554652625L;
/*    */   private int type;
/*    */   private int mappingType;
/*    */   private String mappingContent;
/*    */   private Map<String, String> data;
/*    */ 
/*    */   public Map<String, String> getData()
/*    */   {
/* 34 */     return this.data; }
/*    */ 
/*    */   public void setData(Map<String, String> data) {
/* 37 */     this.data = data; }
/*    */ 
/*    */   public int getType() {
/* 40 */     return this.type; }
/*    */ 
/*    */   public void setType(int type) {
/* 43 */     this.type = type; }
/*    */ 
/*    */   public int getMappingType() {
/* 46 */     return this.mappingType; }
/*    */ 
/*    */   public void setMappingType(int mappingType) {
/* 49 */     this.mappingType = mappingType; }
/*    */ 
/*    */   public String getMappingContent() {
/* 52 */     return this.mappingContent; }
/*    */ 
/*    */   public void setMappingContent(String mappingContent) {
/* 55 */     this.mappingContent = mappingContent;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ImportEntityItem
 * JD-Core Version:    0.5.3
 */