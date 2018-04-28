/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2443060026366485481L;
/*    */   private String name;
/*    */   private String password;
/*    */   private String displayName;
/*    */   private String roles;
/*    */   private int permitted;
/*    */   private String status;
/*    */ 
/*    */   public String getDisplayName()
/*    */   {
/* 18 */     return this.displayName; }
/*    */ 
/*    */   public void setDisplayName(String displayName) {
/* 21 */     this.displayName = displayName; }
/*    */ 
/*    */   public String getName() {
/* 24 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 27 */     this.name = name; }
/*    */ 
/*    */   public String getPassword() {
/* 30 */     return this.password; }
/*    */ 
/*    */   public void setPassword(String password) {
/* 33 */     this.password = password; }
/*    */ 
/*    */   public String getStatus() {
/* 36 */     return this.status; }
/*    */ 
/*    */   public void setStatus(String status) {
/* 39 */     this.status = status; }
/*    */ 
/*    */   public String getRoles() {
/* 42 */     return this.roles; }
/*    */ 
/*    */   public void setRoles(String roles) {
/* 45 */     this.roles = roles; }
/*    */ 
/*    */   public int getPermitted() {
/* 48 */     return this.permitted; }
/*    */ 
/*    */   public void setPermitted(int permitted) {
/* 51 */     this.permitted = permitted;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.UserItem
 * JD-Core Version:    0.5.3
 */