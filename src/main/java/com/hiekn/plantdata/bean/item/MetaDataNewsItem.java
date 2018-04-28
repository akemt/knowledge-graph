/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ public class MetaDataNewsItem
/*    */ {
/*    */   private long id;
/*    */   private String title;
/*    */   private String publishTime;
/*    */   private String source;
/*    */   private String content;
/*    */ 
/*    */   public String getContent()
/*    */   {
/* 15 */     return this.content; }
/*    */ 
/*    */   public void setContent(String content) {
/* 18 */     this.content = content; }
/*    */ 
/*    */   public String getPublishTime() {
/* 21 */     return this.publishTime; }
/*    */ 
/*    */   public void setPublishTime(String publishTime) {
/* 24 */     this.publishTime = publishTime; }
/*    */ 
/*    */   public String getSource() {
/* 27 */     return this.source; }
/*    */ 
/*    */   public void setSource(String source) {
/* 30 */     this.source = source; }
/*    */ 
/*    */   public String getTitle() {
/* 33 */     return this.title; }
/*    */ 
/*    */   public void setTitle(String title) {
/* 36 */     this.title = title; }
/*    */ 
/*    */   public long getId() {
/* 39 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 42 */     this.id = id;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.MetaDataNewsItem
 * JD-Core Version:    0.5.3
 */