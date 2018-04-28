/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SemanticTemplateFilter
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7362572568136575423L;
/*    */   private int attributeId;
/*    */   private String filterType;
/*    */   private String filterValue;
/*    */ 
/*    */   public int getAttributeId()
/*    */   {
/* 22 */     return this.attributeId;
/*    */   }
/*    */ 
/*    */   public void setAttributeId(int attributeId) {
/* 26 */     this.attributeId = attributeId;
/*    */   }
/*    */ 
/*    */   public String getFilterType() {
/* 30 */     return this.filterType;
/*    */   }
/*    */ 
/*    */   public void setFilterType(String filterType) {
/* 34 */     this.filterType = filterType;
/*    */   }
/*    */ 
/*    */   public String getFilterValue() {
/* 38 */     return this.filterValue;
/*    */   }
/*    */ 
/*    */   public void setFilterValue(String filterValue) {
/* 42 */     this.filterValue = filterValue;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.SemanticTemplateFilter
 * JD-Core Version:    0.5.3
 */