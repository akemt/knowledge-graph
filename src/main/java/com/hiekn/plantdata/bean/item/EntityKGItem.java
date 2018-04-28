/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class EntityKGItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 869130351435534833L;
/*    */   private long id;
/*    */   private String name;
/*    */   private long classId;
/*    */   private String imageUrl;
/*    */ 
/*    */   public long getClassId()
/*    */   {
/* 15 */     return this.classId; }
/*    */ 
/*    */   public void setClassId(long classId) {
/* 18 */     this.classId = classId; }
/*    */ 
/*    */   public long getId() {
/* 21 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 24 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 27 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 30 */     this.name = name; }
/*    */ 
/*    */   public String getImageUrl() {
/* 33 */     return this.imageUrl;
/*    */   }
/*    */ 
/*    */   public void setImageUrl(String imageUrl) {
/* 37 */     this.imageUrl = imageUrl;
/*    */   }
/*    */ }
