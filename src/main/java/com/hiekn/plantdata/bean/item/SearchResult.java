/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SearchResult
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5091104401396970910L;
/*    */   private String title;
/*    */   private String url;
/*    */   private String description;
/*    */   private String time;
/*    */   private String content;
/*    */ 
/*    */   public String getDescription()
/*    */   {
/* 17 */     return this.description; }
/*    */ 
/*    */   public void setDescription(String description) {
/* 20 */     this.description = description; }
/*    */ 
/*    */   public String getTitle() {
/* 23 */     return this.title; }
/*    */ 
/*    */   public void setTitle(String title) {
/* 26 */     this.title = title; }
/*    */ 
/*    */   public String getUrl() {
/* 29 */     return this.url; }
/*    */ 
/*    */   public void setUrl(String url) {
/* 32 */     this.url = url; }
/*    */ 
/*    */   public String getTime() {
/* 35 */     return this.time; }
/*    */ 
/*    */   public void setTime(String time) {
/* 38 */     this.time = time; }
/*    */ 
/*    */   public String getContent() {
/* 41 */     return this.content; }
/*    */ 
/*    */   public void setContent(String content) {
/* 44 */     this.content = content;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.SearchResult
 * JD-Core Version:    0.5.3
 */