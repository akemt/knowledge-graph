/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DrugBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2066396878911791389L;
/*    */   private long id;
/*    */   private String name;
/*    */   private double amount;
/*    */   private String method;
/*    */ 
/*    */   public double getAmount()
/*    */   {
/* 16 */     return this.amount; }
/*    */ 
/*    */   public void setAmount(double amount) {
/* 19 */     this.amount = amount; }
/*    */ 
/*    */   public long getId() {
/* 22 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 25 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 28 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 31 */     this.name = name; }
/*    */ 
/*    */   public String getMethod() {
/* 34 */     return this.method; }
/*    */ 
/*    */   public void setMethod(String method) {
/* 37 */     this.method = method;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.DrugBean
 * JD-Core Version:    0.5.3
 */