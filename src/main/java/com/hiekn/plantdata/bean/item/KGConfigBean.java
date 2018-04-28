/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class KGConfigBean
/*    */ {
/*    */   private List<Long> conceptFilter;
/*    */   private List<Long> entityFilter;
/*    */   private String kgDBName;
/*    */ 
/*    */   public List<Long> getConceptFilter()
/*    */   {
/* 22 */     return this.conceptFilter; }
/*    */ 
/*    */   public void setConceptFilter(List<Long> conceptFilter) {
/* 25 */     this.conceptFilter = conceptFilter; }
/*    */ 
/*    */   public List<Long> getEntityFilter() {
/* 28 */     return this.entityFilter; }
/*    */ 
/*    */   public void setEntityFilter(List<Long> entityFilter) {
/* 31 */     this.entityFilter = entityFilter; }
/*    */ 
/*    */   public String getKgDBName() {
/* 34 */     return this.kgDBName; }
/*    */ 
/*    */   public void setKgDBName(String kgDBName) {
/* 37 */     this.kgDBName = kgDBName;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.KGConfigBean
 * JD-Core Version:    0.5.3
 */