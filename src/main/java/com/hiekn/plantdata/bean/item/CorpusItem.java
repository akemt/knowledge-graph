/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class CorpusItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2647945049168185242L;
/*     */   private long id;
/*     */   private String gid;
/*     */   private String _id;
/*     */   private int type;
/*     */   private String category;
/*     */   private String title;
/*     */   private String author;
/*     */   private String origin;
/*     */   private String issn;
/*     */   private String keyword;
/*     */   private String year;
/*     */   private String tag;
/*     */   private String url;
/*     */   private String publisher;
/*     */   private String filePath;
/*     */   private String patentId;
/*     */   private String jsonContent;
/*     */   private double score;
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/*  30 */     return this.author; }
/*     */ 
/*     */   public void setAuthor(String author) {
/*  33 */     this.author = author; }
/*     */ 
/*     */   public String getCategory() {
/*  36 */     return this.category; }
/*     */ 
/*     */   public void setCategory(String category) {
/*  39 */     this.category = category; }
/*     */ 
/*     */   public String getFilePath() {
/*  42 */     return this.filePath; }
/*     */ 
/*     */   public void setFilePath(String filePath) {
/*  45 */     this.filePath = filePath; }
/*     */ 
/*     */   public long getId() {
/*  48 */     return this.id; }
/*     */ 
/*     */   public void setId(long id) {
/*  51 */     this.id = id; }
/*     */ 
/*     */   public String getIssn() {
/*  54 */     return this.issn; }
/*     */ 
/*     */   public void setIssn(String issn) {
/*  57 */     this.issn = issn; }
/*     */ 
/*     */   public String getKeyword() {
/*  60 */     return this.keyword; }
/*     */ 
/*     */   public void setKeyword(String keyword) {
/*  63 */     this.keyword = keyword; }
/*     */ 
/*     */   public String getOrigin() {
/*  66 */     return this.origin; }
/*     */ 
/*     */   public void setOrigin(String origin) {
/*  69 */     this.origin = origin; }
/*     */ 
/*     */   public String getPatentId() {
/*  72 */     return this.patentId; }
/*     */ 
/*     */   public void setPatentId(String patentId) {
/*  75 */     this.patentId = patentId; }
/*     */ 
/*     */   public String getTitle() {
/*  78 */     return this.title; }
/*     */ 
/*     */   public void setTitle(String title) {
/*  81 */     this.title = title; }
/*     */ 
/*     */   public String getYear() {
/*  84 */     return this.year; }
/*     */ 
/*     */   public void setYear(String year) {
/*  87 */     this.year = year; }
/*     */ 
/*     */   public String getTag() {
/*  90 */     return this.tag; }
/*     */ 
/*     */   public void setTag(String tag) {
/*  93 */     this.tag = tag; }
/*     */ 
/*     */   public String getUrl() {
/*  96 */     return this.url; }
/*     */ 
/*     */   public void setUrl(String url) {
/*  99 */     this.url = url; }
/*     */ 
/*     */   public String getPublisher() {
/* 102 */     return this.publisher; }
/*     */ 
/*     */   public void setPublisher(String publisher) {
/* 105 */     this.publisher = publisher; }
/*     */ 
/*     */   public double getScore() {
/* 108 */     return this.score; }
/*     */ 
/*     */   public void setScore(double score) {
/* 111 */     this.score = score; }
/*     */ 
/*     */   public String get_id() {
/* 114 */     return this._id; }
/*     */ 
/*     */   public void set_id(String _id) {
/* 117 */     this._id = _id; }
/*     */ 
/*     */   public String getJsonContent() {
/* 120 */     return this.jsonContent; }
/*     */ 
/*     */   public void setJsonContent(String jsonContent) {
/* 123 */     this.jsonContent = jsonContent; }
/*     */ 
/*     */   public String getGid() {
/* 126 */     return this.gid; }
/*     */ 
/*     */   public void setGid(String gid) {
/* 129 */     this.gid = gid; }
/*     */ 
/*     */   public int getType() {
/* 132 */     return this.type; }
/*     */ 
/*     */   public void setType(int type) {
/* 135 */     this.type = type;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.CorpusItem
 * JD-Core Version:    0.5.3
 */