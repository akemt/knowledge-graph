/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DeleteAttributeItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7652488731569190667L;
/*    */   private int attDefId;
/*    */   private String attId;
/*    */   private int attType;
/*    */ 
/*    */   public int getAttDefId()
/*    */   {
/* 30 */     return this.attDefId;
/*    */   }
/*    */ 
/*    */   public void setAttDefId(int attDefId) {
/* 34 */     this.attDefId = attDefId;
/*    */   }
/*    */ 
/*    */   public String getAttId() {
/* 38 */     return this.attId;
/*    */   }
/*    */ 
/*    */   public void setAttId(String attId) {
/* 42 */     this.attId = attId;
/*    */   }
/*    */ 
/*    */   public int getAttType() {
/* 46 */     return this.attType;
/*    */   }
/*    */ 
/*    */   public void setAttType(int attType) {
/* 50 */     this.attType = attType;
/*    */   }
/*    */ 
/*    */   public static long getSerialversionuid() {
/* 54 */     return -7652488731569190667L;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.DeleteAttributeItem
 * JD-Core Version:    0.5.3
 */