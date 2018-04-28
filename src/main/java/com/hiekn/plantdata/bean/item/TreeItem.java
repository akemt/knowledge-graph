/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TreeItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8267710798301096245L;
/*    */   private String id;
/*    */   private String name;
/*    */   private String path;
/*    */   private String parentId;
/*    */   private String action;
/*    */   private int type;
/*    */   private String imageUrl;
/*    */ 
/*    */   public String getId()
/*    */   {
/* 47 */     return this.id; }
/*    */ 
/*    */   public void setId(String id) {
/* 50 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 53 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 56 */     this.name = name; }
/*    */ 
/*    */   public String getPath() {
/* 59 */     return this.path; }
/*    */ 
/*    */   public void setPath(String path) {
/* 62 */     this.path = path; }
/*    */ 
/*    */   public String getParentId() {
/* 65 */     return this.parentId; }
/*    */ 
/*    */   public void setParentId(String parentId) {
/* 68 */     this.parentId = parentId; }
/*    */ 
/*    */   public String getAction() {
/* 71 */     return this.action; }
/*    */ 
/*    */   public void setAction(String action) {
/* 74 */     this.action = action; }
/*    */ 
/*    */   public int getType() {
/* 77 */     return this.type; }
/*    */ 
/*    */   public void setType(int type) {
/* 80 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public String getImageUrl() {
/* 84 */     return this.imageUrl;
/*    */   }
/*    */ 
/*    */   public void setImageUrl(String imageUrl) {
/* 88 */     this.imageUrl = imageUrl;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.TreeItem
 * JD-Core Version:    0.5.3
 */