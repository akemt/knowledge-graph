/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class LinkingItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1804094251536808140L;
/*    */   private String id;
/*    */   private long entityId;
/*    */   private String corpusId;
/*    */   private float score;
/*    */   private int sourceType;
/*    */   private String info;
/*    */ 
/*    */   public long getEntityId()
/*    */   {
/* 20 */     return this.entityId; }
/*    */ 
/*    */   public void setEntityId(long entityId) {
/* 23 */     this.entityId = entityId; }
/*    */ 
/*    */   public String getId() {
/* 26 */     return this.id; }
/*    */ 
/*    */   public void setId(String id) {
/* 29 */     this.id = id; }
/*    */ 
/*    */   public String getInfo() {
/* 32 */     return this.info; }
/*    */ 
/*    */   public void setInfo(String info) {
/* 35 */     this.info = info; }
/*    */ 
/*    */   public float getScore() {
/* 38 */     return this.score; }
/*    */ 
/*    */   public void setScore(float score) {
/* 41 */     this.score = score; }
/*    */ 
/*    */   public int getSourceType() {
/* 44 */     return this.sourceType; }
/*    */ 
/*    */   public void setSourceType(int sourceType) {
/* 47 */     this.sourceType = sourceType; }
/*    */ 
/*    */   public String getCorpusId() {
/* 50 */     return this.corpusId; }
/*    */ 
/*    */   public void setCorpusId(String corpusId) {
/* 53 */     this.corpusId = corpusId;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.LinkingItem
 * JD-Core Version:    0.5.3
 */