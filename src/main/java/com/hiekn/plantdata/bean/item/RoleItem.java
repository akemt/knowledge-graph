/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RoleItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3671478282253089463L;
/*    */   private int id;
/*    */   private String name;
/*    */   private String description;
/*    */ 
/*    */   public String getDescription()
/*    */   {
/* 15 */     return this.description; }
/*    */ 
/*    */   public void setDescription(String description) {
/* 18 */     this.description = description; }
/*    */ 
/*    */   public int getId() {
/* 21 */     return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 24 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 27 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 30 */     this.name = name;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.RoleItem
 * JD-Core Version:    0.5.3
 */