/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AttributeKGItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 869130351435534833L;
/*    */   private int id;
/*    */   private String name;
/*    */ 
/*    */   public int getId()
/*    */   {
/* 10 */     return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 13 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 16 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 19 */     this.name = name;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.AttributeKGItem
 * JD-Core Version:    0.5.3
 */