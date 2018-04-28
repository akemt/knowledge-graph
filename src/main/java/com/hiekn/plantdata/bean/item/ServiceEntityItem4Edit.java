/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ 
/*     */ @XmlRootElement
/*     */ public class ServiceEntityItem4Edit
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -487610611491264461L;
/*     */   private long id;
/*     */   private String name;
/*     */   private int type;
/*     */   private String meaningTag;
/*     */   private String imageUrl;
/*     */   private String abs;
/*     */   private Map<String, String> syns;
/*     */   private Map<String, SimpleEntityItem> pars;
/*     */   private Map<String, SimpleEntityItem> sons;
/*     */   private List<ServiceAttributeItem4Edit> atts;
/*     */   private Map<String, SimpleEntityItem> inss;
/*     */ 
/*     */   public String getAbs()
/*     */   {
/*  74 */     return this.abs;
/*     */   }
/*     */ 
/*     */   public List<ServiceAttributeItem4Edit> getAtts() {
/*  78 */     return this.atts;
/*     */   }
/*     */ 
/*     */   public void setAtts(List<ServiceAttributeItem4Edit> atts) {
/*  82 */     this.atts = atts;
/*     */   }
/*     */ 
/*     */   public long getId() {
/*  86 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(long id) {
/*  90 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Map<String, SimpleEntityItem> getInss() {
/*  94 */     return this.inss;
/*     */   }
/*     */ 
/*     */   public void setInss(Map<String, SimpleEntityItem> inss) {
/*  98 */     this.inss = inss;
/*     */   }
/*     */ 
/*     */   public String getMeaningTag() {
/* 102 */     return this.meaningTag;
/*     */   }
/*     */ 
/*     */   public void setMeaningTag(String meaningTag) {
/* 106 */     this.meaningTag = meaningTag;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 110 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 114 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Map<String, SimpleEntityItem> getPars() {
/* 118 */     return this.pars;
/*     */   }
/*     */ 
/*     */   public void setPars(Map<String, SimpleEntityItem> pars) {
/* 122 */     this.pars = pars;
/*     */   }
/*     */ 
/*     */   public Map<String, SimpleEntityItem> getSons() {
/* 126 */     return this.sons;
/*     */   }
/*     */ 
/*     */   public void setSons(Map<String, SimpleEntityItem> sons) {
/* 130 */     this.sons = sons;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getSyns() {
/* 134 */     return this.syns;
/*     */   }
/*     */ 
/*     */   public void setSyns(Map<String, String> syns) {
/* 138 */     this.syns = syns;
/*     */   }
/*     */ 
/*     */   public int getType() {
/* 142 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(int type) {
/* 146 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public void setAbs(String abs) {
/* 150 */     this.abs = abs;
/*     */   }
/*     */ 
/*     */   public String getImageUrl() {
/* 154 */     return this.imageUrl;
/*     */   }
/*     */ 
/*     */   public void setImageUrl(String imageUrl) {
/* 158 */     this.imageUrl = imageUrl;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ServiceEntityItem4Edit
 * JD-Core Version:    0.5.3
 */