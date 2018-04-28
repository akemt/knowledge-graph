/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ServiceEntityItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -487610611491264461L;
/*     */   private long id;
/*     */   private String name;
/*     */   private int type;
/*     */   private String meaningTag;
/*     */   private String imageUrl;
/*     */   private String abs;
/*     */   private Set<String> syns;
/*     */   private List<SimpleEntityItem> pars;
/*     */   private List<SimpleEntityItem> sibs;
/*     */   private List<SimpleEntityItem> sons;
/*     */   private List<ServiceAttributeItem> atts;
/*     */   private List<ServiceAttributeItem> reAtts;
/*     */   private List<SimpleEntityItem> inss;
/*     */ 
/*     */   public String getAbs()
/*     */   {
/*  81 */     return this.abs; }
/*     */ 
/*     */   public void setAbs(String abs) {
/*  84 */     this.abs = abs; }
/*     */ 
/*     */   public List<ServiceAttributeItem> getAtts() {
/*  87 */     return this.atts; }
/*     */ 
/*     */   public void setAtts(List<ServiceAttributeItem> atts) {
/*  90 */     this.atts = atts; }
/*     */ 
/*     */   public long getId() {
/*  93 */     return this.id; }
/*     */ 
/*     */   public void setId(long id) {
/*  96 */     this.id = id; }
/*     */ 
/*     */   public List<SimpleEntityItem> getInss() {
/*  99 */     return this.inss; }
/*     */ 
/*     */   public void setInss(List<SimpleEntityItem> inss) {
/* 102 */     this.inss = inss; }
/*     */ 
/*     */   public String getName() {
/* 105 */     return this.name; }
/*     */ 
/*     */   public void setName(String name) {
/* 108 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Set<String> getSyns() {
/* 112 */     return this.syns; }
/*     */ 
/*     */   public void setSyns(Set<String> syns) {
/* 115 */     this.syns = syns; }
/*     */ 
/*     */   public int getType() {
/* 118 */     return this.type; }
/*     */ 
/*     */   public void setType(int type) {
/* 121 */     this.type = type; }
/*     */ 
/*     */   public List<SimpleEntityItem> getPars() {
/* 124 */     return this.pars; }
/*     */ 
/*     */   public void setPars(List<SimpleEntityItem> pars) {
/* 127 */     this.pars = pars; }
/*     */ 
/*     */   public List<SimpleEntityItem> getSibs() {
/* 130 */     return this.sibs; }
/*     */ 
/*     */   public void setSibs(List<SimpleEntityItem> sibs) {
/* 133 */     this.sibs = sibs; }
/*     */ 
/*     */   public List<SimpleEntityItem> getSons() {
/* 136 */     return this.sons; }
/*     */ 
/*     */   public void setSons(List<SimpleEntityItem> sons) {
/* 139 */     this.sons = sons; }
/*     */ 
/*     */   public String getMeaningTag() {
/* 142 */     return this.meaningTag; }
/*     */ 
/*     */   public void setMeaningTag(String meaningTag) {
/* 145 */     this.meaningTag = meaningTag; }
/*     */ 
/*     */   public List<ServiceAttributeItem> getReAtts() {
/* 148 */     return this.reAtts; }
/*     */ 
/*     */   public void setReAtts(List<ServiceAttributeItem> reAtts) {
/* 151 */     this.reAtts = reAtts;
/*     */   }
/*     */ 
/*     */   public String getImageUrl() {
/* 155 */     return this.imageUrl;
/*     */   }
/*     */ 
/*     */   public void setImageUrl(String imageUrl) {
/* 159 */     this.imageUrl = imageUrl;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ServiceEntityItem
 * JD-Core Version:    0.5.3
 */