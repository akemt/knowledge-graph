/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EntityItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -487610611491264461L;
/*     */   private long id;
/*     */   private String name;
/*     */   private String type;
/*     */   private String abstraction;
/*     */   private String meaningTag;
/*     */   private String[] meanings;
/*     */   private String syns;
/*     */   private String pars;
/*     */   private String sibs;
/*     */   private String sons;
/*     */   private String atts;
/*     */   private List<AttributeItem> attList;
/*     */   private Map<String, String> reAttsMap;
/*     */   private String inss;
/*     */   private String imgFlag;
/*     */   private String description;
/*     */   private int version;
/*     */ 
/*     */   public String getDescription()
/*     */   {
/*  34 */     return this.description; }
/*     */ 
/*     */   public void setDescription(String description) {
/*  37 */     this.description = description; }
/*     */ 
/*     */   public String getInss() {
/*  40 */     return this.inss; }
/*     */ 
/*     */   public void setInss(String inss) {
/*  43 */     this.inss = inss; }
/*     */ 
/*     */   public String getName() {
/*  46 */     return this.name; }
/*     */ 
/*     */   public void setName(String name) {
/*  49 */     this.name = name; }
/*     */ 
/*     */   public String getPars() {
/*  52 */     return this.pars; }
/*     */ 
/*     */   public void setPars(String pars) {
/*  55 */     this.pars = pars; }
/*     */ 
/*     */   public String getSons() {
/*  58 */     return this.sons; }
/*     */ 
/*     */   public void setSons(String sons) {
/*  61 */     this.sons = sons; }
/*     */ 
/*     */   public String getSyns() {
/*  64 */     return this.syns; }
/*     */ 
/*     */   public void setSyns(String syns) {
/*  67 */     this.syns = syns; }
/*     */ 
/*     */   public String getType() {
/*  70 */     return this.type; }
/*     */ 
/*     */   public void setType(String type) {
/*  73 */     this.type = type; }
/*     */ 
/*     */   public String getSibs() {
/*  76 */     return this.sibs; }
/*     */ 
/*     */   public void setSibs(String sibs) {
/*  79 */     this.sibs = sibs; }
/*     */ 
/*     */   public String getAbstraction() {
/*  82 */     return this.abstraction; }
/*     */ 
/*     */   public void setAbstraction(String abstraction) {
/*  85 */     this.abstraction = abstraction; }
/*     */ 
/*     */   public long getId() {
/*  88 */     return this.id; }
/*     */ 
/*     */   public void setId(long id) {
/*  91 */     this.id = id; }
/*     */ 
/*     */   public String[] getMeanings() {
/*  94 */     return this.meanings; }
/*     */ 
/*     */   public void setMeanings(String[] meanings) {
/*  97 */     this.meanings = meanings; }
/*     */ 
/*     */   public String getImgFlag() {
/* 100 */     return this.imgFlag; }
/*     */ 
/*     */   public void setImgFlag(String imgFlag) {
/* 103 */     this.imgFlag = imgFlag; }
/*     */ 
/*     */   public int getVersion() {
/* 106 */     return this.version; }
/*     */ 
/*     */   public void setVersion(int version) {
/* 109 */     this.version = version; }
/*     */ 
/*     */   public String getMeaningTag() {
/* 112 */     return this.meaningTag; }
/*     */ 
/*     */   public void setMeaningTag(String meaningTag) {
/* 115 */     this.meaningTag = meaningTag; }
/*     */ 
/*     */   public Map<String, String> getReAttsMap() {
/* 118 */     return this.reAttsMap; }
/*     */ 
/*     */   public void setReAttsMap(Map<String, String> reAttsMap) {
/* 121 */     this.reAttsMap = reAttsMap; }
/*     */ 
/*     */   public List<AttributeItem> getAttList() {
/* 124 */     return this.attList; }
/*     */ 
/*     */   public void setAttList(List<AttributeItem> attList) {
/* 127 */     this.attList = attList; }
/*     */ 
/*     */   public String getAtts() {
/* 130 */     return this.atts; }
/*     */ 
/*     */   public void setAtts(String atts) {
/* 133 */     this.atts = atts;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.EntityItem
 * JD-Core Version:    0.5.3
 */