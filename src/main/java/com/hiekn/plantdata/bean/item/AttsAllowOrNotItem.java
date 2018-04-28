/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class AttsAllowOrNotItem
/*    */ {
/*    */   private Long classId;
/*    */   private List<Integer> allowAtts;
/*    */   private List<Integer> disAllowAtts;
/*    */ 
/*    */   public Long getClassId()
/*    */   {
/* 14 */     return this.classId;
/*    */   }
/*    */ 
/*    */   public void setClassId(Long classId) {
/* 18 */     this.classId = classId;
/*    */   }
/*    */ 
/*    */   public List<Integer> getAllowAtts() {
/* 22 */     return this.allowAtts;
/*    */   }
/*    */ 
/*    */   public void setAllowAtts(List<Integer> allowAtts) {
/* 26 */     this.allowAtts = allowAtts;
/*    */   }
/*    */ 
/*    */   public List<Integer> getDisAllowAtts() {
/* 30 */     return this.disAllowAtts;
/*    */   }
/*    */ 
/*    */   public void setDisAllowAtts(List<Integer> disAllowAtts) {
/* 34 */     this.disAllowAtts = disAllowAtts;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.AttsAllowOrNotItem
 * JD-Core Version:    0.5.3
 */