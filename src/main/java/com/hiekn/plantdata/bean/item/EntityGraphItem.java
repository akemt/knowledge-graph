/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class EntityGraphItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8080095987892797757L;
/*    */   private long id;
/*    */   private String name;
/*    */   private String abs;
/*    */   private String imgFlag;
/*    */   private List<String> similarEntities;
/*    */ 
/*    */   public String getAbs()
/*    */   {
/* 20 */     return this.abs; }
/*    */ 
/*    */   public void setAbs(String abs) {
/* 23 */     this.abs = abs; }
/*    */ 
/*    */   public long getId() {
/* 26 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 29 */     this.id = id; }
/*    */ 
/*    */   public String getImgFlag() {
/* 32 */     return this.imgFlag; }
/*    */ 
/*    */   public void setImgFlag(String imgFlag) {
/* 35 */     this.imgFlag = imgFlag; }
/*    */ 
/*    */   public String getName() {
/* 38 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 41 */     this.name = name; }
/*    */ 
/*    */   public List<String> getSimilarEntities() {
/* 44 */     return this.similarEntities; }
/*    */ 
/*    */   public void setSimilarEntities(List<String> similarEntities) {
/* 47 */     this.similarEntities = similarEntities;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.EntityGraphItem
 * JD-Core Version:    0.5.3
 */