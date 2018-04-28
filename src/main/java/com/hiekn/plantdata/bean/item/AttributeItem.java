/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class AttributeItem
/*     */ {
/*     */   private long id;
/*     */   private String id_;
/*     */   private int seqNo;
/*     */   private int attDefId;
/*     */   private String name;
/*     */   private int type;
/*     */   private int dataType;
/*     */   private String dValue;
/*     */   private List<PairItem> oValues;
/*     */   private Map<String, Double> mapValue;
/*     */   private String dataUnit;
/*     */   private String editTip;
/*     */   private int isFunctional;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  61 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  65 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public int getType() {
/*  69 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(int type) {
/*  73 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getdValue() {
/*  77 */     return this.dValue;
/*     */   }
/*     */ 
/*     */   public void setdValue(String dValue) {
/*  81 */     this.dValue = dValue;
/*     */   }
/*     */ 
/*     */   public List<PairItem> getoValues() {
/*  85 */     return this.oValues;
/*     */   }
/*     */ 
/*     */   public void setoValues(List<PairItem> oValues) {
/*  89 */     this.oValues = oValues;
/*     */   }
/*     */ 
/*     */   public String getDataUnit() {
/*  93 */     return this.dataUnit;
/*     */   }
/*     */ 
/*     */   public void setDataUnit(String dataUnit) {
/*  97 */     this.dataUnit = dataUnit;
/*     */   }
/*     */ 
/*     */   public String getEditTip() {
/* 101 */     return this.editTip;
/*     */   }
/*     */ 
/*     */   public void setEditTip(String editTip) {
/* 105 */     this.editTip = editTip;
/*     */   }
/*     */ 
/*     */   public long getId() {
/* 109 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(long id) {
/* 113 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public int getSeqNo() {
/* 117 */     return this.seqNo;
/*     */   }
/*     */ 
/*     */   public void setSeqNo(int seqNo) {
/* 121 */     this.seqNo = seqNo;
/*     */   }
/*     */ 
/*     */   public int getAttDefId() {
/* 125 */     return this.attDefId;
/*     */   }
/*     */ 
/*     */   public void setAttDefId(int attDefId) {
/* 129 */     this.attDefId = attDefId;
/*     */   }
/*     */ 
/*     */   public int getDataType() {
/* 133 */     return this.dataType;
/*     */   }
/*     */ 
/*     */   public void setDataType(int dataType) {
/* 137 */     this.dataType = dataType;
/*     */   }
/*     */ 
/*     */   public Map<String, Double> getMapValue() {
/* 141 */     return this.mapValue;
/*     */   }
/*     */ 
/*     */   public void setMapValue(Map<String, Double> mapValue) {
/* 145 */     this.mapValue = mapValue;
/*     */   }
/*     */ 
/*     */   public int getIsFunctional() {
/* 149 */     return this.isFunctional;
/*     */   }
/*     */ 
/*     */   public void setIsFunctional(int isFunctional) {
/* 153 */     this.isFunctional = isFunctional;
/*     */   }
/*     */ 
/*     */   public String getId_() {
/* 157 */     return this.id_;
/*     */   }
/*     */ 
/*     */   public void setId_(String id_) {
/* 161 */     this.id_ = id_;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.AttributeItem
 * JD-Core Version:    0.5.3
 */