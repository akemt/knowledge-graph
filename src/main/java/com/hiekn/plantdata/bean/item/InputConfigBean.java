/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class InputConfigBean
/*     */ {
/*     */   private String user;
/*     */   private String password;
/*     */   private String sourceType;
/*     */   private String sourceHost;
/*     */   private int sourcePort;
/*     */   private String sourceDB;
/*     */   private String sourceCollection;
/*     */   private List<String> sourceFields;
/*     */   private String startTime;
/*     */   private String endTime;
/*     */   private Integer limit;
/*     */ 
/*     */   public String getUser()
/*     */   {
/*  12 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(String user) {
/*  16 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public String getPassword() {
/*  20 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/*  24 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public Integer getLimit()
/*     */   {
/*  68 */     return this.limit;
/*     */   }
/*     */ 
/*     */   public void setLimit(Integer limit) {
/*  72 */     this.limit = limit;
/*     */   }
/*     */ 
/*     */   public String getStartTime() {
/*  76 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(String startTime) {
/*  80 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public String getEndTime() {
/*  84 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(String endTime) {
/*  88 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public String getSourceType() {
/*  92 */     return this.sourceType;
/*     */   }
/*     */ 
/*     */   public void setSourceType(String sourceType) {
/*  96 */     this.sourceType = sourceType;
/*     */   }
/*     */ 
/*     */   public String getSourceHost() {
/* 100 */     return this.sourceHost;
/*     */   }
/*     */ 
/*     */   public void setSourceHost(String sourceHost) {
/* 104 */     this.sourceHost = sourceHost;
/*     */   }
/*     */ 
/*     */   public int getSourcePort() {
/* 108 */     return this.sourcePort;
/*     */   }
/*     */ 
/*     */   public void setSourcePort(int sourcePort) {
/* 112 */     this.sourcePort = sourcePort;
/*     */   }
/*     */ 
/*     */   public String getSourceDB() {
/* 116 */     return this.sourceDB;
/*     */   }
/*     */ 
/*     */   public void setSourceDB(String sourceDB) {
/* 120 */     this.sourceDB = sourceDB;
/*     */   }
/*     */ 
/*     */   public String getSourceCollection() {
/* 124 */     return this.sourceCollection;
/*     */   }
/*     */ 
/*     */   public void setSourceCollection(String sourceCollection) {
/* 128 */     this.sourceCollection = sourceCollection;
/*     */   }
/*     */ 
/*     */   public List<String> getSourceFields() {
/* 132 */     return this.sourceFields;
/*     */   }
/*     */ 
/*     */   public void setSourceFields(List<String> sourceFields) {
/* 136 */     this.sourceFields = sourceFields;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.InputConfigBean
 * JD-Core Version:    0.5.3
 */