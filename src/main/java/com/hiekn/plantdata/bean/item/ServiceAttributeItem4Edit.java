/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ 
/*     */ public class ServiceAttributeItem4Edit
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1416787441038129120L;
/*     */   private int attDefId;
/*     */   private String name;
/*     */   private int type;
/*     */   private String dValue;
/*     */   private String dValueTripleId;
/*     */   private int dataType;
/*     */   private String dataUnit;
/*     */   private int isFunctional;
/*     */   private List<AttributeExtraInfoItem> attrExtInfo;
/*     */   private Map<String, ObjectAttributeItem> oValues;
/*     */ 
/*     */   @XmlElement(name="DValue")
/*     */   public String getDValue()
/*     */   {
/*  72 */     return this.dValue; }
/*     */ 
/*     */   public void setDValue(String value) {
/*  75 */     this.dValue = value; }
/*     */ 
/*     */   public String getName() {
/*  78 */     return this.name; }
/*     */ 
/*     */   public void setName(String name) {
/*  81 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public int getType() {
/*  85 */     return this.type; }
/*     */ 
/*     */   public void setType(int type) {
/*  88 */     this.type = type; }
/*     */ 
/*     */   public String getDataUnit() {
/*  91 */     return this.dataUnit; }
/*     */ 
/*     */   public void setDataUnit(String dataUnit) {
/*  94 */     this.dataUnit = dataUnit; }
/*     */ 
/*     */   public int getDataType() {
/*  97 */     return this.dataType; }
/*     */ 
/*     */   public void setDataType(int dataType) {
/* 100 */     this.dataType = dataType; }
/*     */ 
/*     */   public int getAttDefId() {
/* 103 */     return this.attDefId; }
/*     */ 
/*     */   public void setAttDefId(int attDefId) {
/* 106 */     this.attDefId = attDefId;
/*     */   }
/*     */ 
/*     */   @XmlElement(name="DValueTripleId")
/*     */   public String getDValueTripleId() {
/* 111 */     return this.dValueTripleId; }
/*     */ 
/*     */   public void setDValueTripleId(String valueTripleId) {
/* 114 */     this.dValueTripleId = valueTripleId;
/*     */   }
/*     */ 
/*     */   @XmlElement(name="OValues")
/*     */   public Map<String, ObjectAttributeItem> getOValues() {
/* 119 */     return this.oValues; }
/*     */ 
/*     */   public void setOValues(Map<String, ObjectAttributeItem> values) {
/* 122 */     this.oValues = values; }
/*     */ 
/*     */   public int getIsFunctional() {
/* 125 */     return this.isFunctional; }
/*     */ 
/*     */   public void setIsFunctional(int isFunctional) {
/* 128 */     this.isFunctional = isFunctional; }
/*     */ 
/*     */   public List<AttributeExtraInfoItem> getAttrExtInfo() {
/* 131 */     return this.attrExtInfo; }
/*     */ 
/*     */   public void setAttrExtInfo(List<AttributeExtraInfoItem> attrExtInfo) {
/* 134 */     this.attrExtInfo = attrExtInfo;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ServiceAttributeItem4Edit
 * JD-Core Version:    0.5.3
 */