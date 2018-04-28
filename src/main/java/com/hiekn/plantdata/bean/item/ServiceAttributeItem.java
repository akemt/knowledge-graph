/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ServiceAttributeItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1416787441038129120L;
/*     */   private int attDefId;
/*     */   private String name;
/*     */   private int type;
/*     */   private String dValue;
/*     */   private int dataType;
/*     */   private String dataUnit;
/*     */   private List<SimpleEntityItem> oValues;
/*     */   private List<SimpleEntityItem> revOValues;
/*     */ 
/*     */   public String getDValue()
/*     */   {
/*  58 */     return this.dValue; }
/*     */ 
/*     */   public void setDValue(String value) {
/*  61 */     this.dValue = value; }
/*     */ 
/*     */   public String getName() {
/*  64 */     return this.name; }
/*     */ 
/*     */   public void setName(String name) {
/*  67 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public List<SimpleEntityItem> getOValues() {
/*  71 */     return this.oValues; }
/*     */ 
/*     */   public void setOValues(List<SimpleEntityItem> values) {
/*  74 */     this.oValues = values; }
/*     */ 
/*     */   public int getType() {
/*  77 */     return this.type; }
/*     */ 
/*     */   public void setType(int type) {
/*  80 */     this.type = type; }
/*     */ 
/*     */   public String getDataUnit() {
/*  83 */     return this.dataUnit; }
/*     */ 
/*     */   public void setDataUnit(String dataUnit) {
/*  86 */     this.dataUnit = dataUnit; }
/*     */ 
/*     */   public int getDataType() {
/*  89 */     return this.dataType; }
/*     */ 
/*     */   public void setDataType(int dataType) {
/*  92 */     this.dataType = dataType; }
/*     */ 
/*     */   public List<SimpleEntityItem> getRevOValues() {
/*  95 */     return this.revOValues; }
/*     */ 
/*     */   public void setRevOValues(List<SimpleEntityItem> revOValues) {
/*  98 */     this.revOValues = revOValues; }
/*     */ 
/*     */   public int getAttDefId() {
/* 101 */     return this.attDefId; }
/*     */ 
/*     */   public void setAttDefId(int attDefId) {
/* 104 */     this.attDefId = attDefId;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ServiceAttributeItem
 * JD-Core Version:    0.5.3
 */