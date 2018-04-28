/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TagItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4410895533092865807L;
/*     */   private String id;
/*     */   private String metaId;
/*     */   private List<Entity> tagEntity;
/*     */   private List<Entity> autoTags;
/*     */   private List<String> tagStr;
/*     */   private double version;
/*     */   private String editorId;
/*     */   private int state;
/*     */   private String reviewerId;
/*     */   private Date editDate;
/*     */   private Date reviewDate;
/*     */ 
/*     */   public String getId()
/*     */   {
/*  37 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  41 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public List<String> getTagStr() {
/*  45 */     return this.tagStr;
/*     */   }
/*     */ 
/*     */   public void setTagStr(List<String> tagStr) {
/*  49 */     this.tagStr = tagStr;
/*     */   }
/*     */ 
/*     */   public double getVersion() {
/*  53 */     return this.version;
/*     */   }
/*     */ 
/*     */   public void setVersion(double version) {
/*  57 */     this.version = version;
/*     */   }
/*     */ 
/*     */   public int getState() {
/*  61 */     return this.state;
/*     */   }
/*     */ 
/*     */   public void setState(int state) {
/*  65 */     this.state = state;
/*     */   }
/*     */ 
/*     */   public Date getEditDate()
/*     */   {
/*  70 */     return this.editDate;
/*     */   }
/*     */ 
/*     */   public void setEditDate(Date editDate) {
/*  74 */     this.editDate = editDate;
/*     */   }
/*     */ 
/*     */   public Date getReviewDate() {
/*  78 */     return this.reviewDate;
/*     */   }
/*     */ 
/*     */   public void setReviewDate(Date reviewDate) {
/*  82 */     this.reviewDate = reviewDate;
/*     */   }
/*     */ 
/*     */   public String getMetaId() {
/*  86 */     return this.metaId;
/*     */   }
/*     */ 
/*     */   public void setMetaId(String metaId) {
/*  90 */     this.metaId = metaId;
/*     */   }
/*     */ 
/*     */   public String getEditorId() {
/*  94 */     return this.editorId;
/*     */   }
/*     */ 
/*     */   public void setEditorId(String editorId) {
/*  98 */     this.editorId = editorId;
/*     */   }
/*     */ 
/*     */   public String getReviewerId() {
/* 102 */     return this.reviewerId;
/*     */   }
/*     */ 
/*     */   public void setReviewerId(String reviewerId) {
/* 106 */     this.reviewerId = reviewerId;
/*     */   }
/*     */ 
/*     */   public List<Entity> getTagEntity() {
/* 110 */     return this.tagEntity;
/*     */   }
/*     */ 
/*     */   public void setTagEntity(List<Entity> tagEntity) {
/* 114 */     this.tagEntity = tagEntity;
/*     */   }
/*     */ 
/*     */   public List<Entity> getAutoTags() {
/* 118 */     return this.autoTags;
/*     */   }
/*     */ 
/*     */   public void setAutoTags(List<Entity> autoTags) {
/* 122 */     this.autoTags = autoTags;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.TagItem
 * JD-Core Version:    0.5.3
 */