/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ImportJsonConceptItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5094134489372174371L;
/*     */   private long id;
/*     */   private String name;
/*     */   private String meaningTag;
/*     */   private String abs;
/*     */   private String img;
/*     */   private List<Long> pars;
/*     */   private List<String> syns;
/*     */   private List<ImportJsonConceptItem> sons;
/*     */   private List<ImportJsonInstanceItem> inss;
/*     */   private List<ImportJsonAttributeItem> atts;
/*     */ 
/*     */   public List<ImportJsonInstanceItem> getInss()
/*     */   {
/*  64 */     return this.inss; }
/*     */ 
/*     */   public void setInss(List<ImportJsonInstanceItem> inss) {
/*  67 */     this.inss = inss; }
/*     */ 
/*     */   public String getMeaningTag() {
/*  70 */     return this.meaningTag; }
/*     */ 
/*     */   public void setMeaningTag(String meaningTag) {
/*  73 */     this.meaningTag = meaningTag; }
/*     */ 
/*     */   public String getName() {
/*  76 */     return this.name; }
/*     */ 
/*     */   public void setName(String name) {
/*  79 */     this.name = name; }
/*     */ 
/*     */   public List<ImportJsonConceptItem> getSons() {
/*  82 */     return this.sons; }
/*     */ 
/*     */   public void setSons(List<ImportJsonConceptItem> sons) {
/*  85 */     this.sons = sons; }
/*     */ 
/*     */   public List<String> getSyns() {
/*  88 */     return this.syns; }
/*     */ 
/*     */   public void setSyns(List<String> syns) {
/*  91 */     this.syns = syns; }
/*     */ 
/*     */   public long getId() {
/*  94 */     return this.id; }
/*     */ 
/*     */   public void setId(long id) {
/*  97 */     this.id = id; }
/*     */ 
/*     */   public List<Long> getPars() {
/* 100 */     return this.pars; }
/*     */ 
/*     */   public void setPars(List<Long> pars) {
/* 103 */     this.pars = pars; }
/*     */ 
/*     */   public List<ImportJsonAttributeItem> getAtts() {
/* 106 */     return this.atts; }
/*     */ 
/*     */   public void setAtts(List<ImportJsonAttributeItem> atts) {
/* 109 */     this.atts = atts; }
/*     */ 
/*     */   public String getAbs() {
/* 112 */     return this.abs; }
/*     */ 
/*     */   public void setAbs(String abs) {
/* 115 */     this.abs = abs; }
/*     */ 
/*     */   public String getImg() {
/* 118 */     return this.img; }
/*     */ 
/*     */   public void setImg(String img) {
/* 121 */     this.img = img;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ImportJsonConceptItem
 * JD-Core Version:    0.5.3
 */