/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class AttributeDefinition
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8347376159343324248L;
/*     */   private int id;
/*     */   private String name;
/*     */   private String alias;
/*     */   private String type;
/*     */   private String domain;
/*     */   private String range;
/*     */   private int dataType;
/*     */   private String dataUnit;
/*     */   private int isFunctional;
/*     */   private List<AttributeExtraInfoItem> extraInfoList;
/*     */   private int tableAlone;
/*     */   private int joinSeqNo;
/*     */   private String gtRangeOperator;
/*     */   private String ltRangeOperator;
/*     */   private String fuzzyOperator;
/*     */   private String gtMostOperator;
/*     */   private String ltMostOperator;
/*     */   private String gtSingleMostOperator;
/*     */   private String ltSingleMostOperator;
/*     */   private String mostOperatorUnit;
/*     */   private String editTip;
/*     */   private int seqNo;
/*     */   private String creator;
/*     */   private String createTime;
/*     */   private String modifier;
/*     */   private String modifyTime;
/*     */   private String status;
/*     */ 
/*     */   public String getAlias()
/*     */   {
/* 137 */     return this.alias; }
/*     */ 
/*     */   public void setAlias(String alias) {
/* 140 */     this.alias = alias; }
/*     */ 
/*     */   public String getCreateTime() {
/* 143 */     return this.createTime; }
/*     */ 
/*     */   public void setCreateTime(String createTime) {
/* 146 */     this.createTime = createTime; }
/*     */ 
/*     */   public String getCreator() {
/* 149 */     return this.creator; }
/*     */ 
/*     */   public void setCreator(String creator) {
/* 152 */     this.creator = creator; }
/*     */ 
/*     */   public String getDomain() {
/* 155 */     return this.domain; }
/*     */ 
/*     */   public void setDomain(String domain) {
/* 158 */     this.domain = domain; }
/*     */ 
/*     */   public int getId() {
/* 161 */     return this.id; }
/*     */ 
/*     */   public void setId(int id) {
/* 164 */     this.id = id; }
/*     */ 
/*     */   public String getModifier() {
/* 167 */     return this.modifier; }
/*     */ 
/*     */   public void setModifier(String modifier) {
/* 170 */     this.modifier = modifier; }
/*     */ 
/*     */   public String getModifyTime() {
/* 173 */     return this.modifyTime; }
/*     */ 
/*     */   public void setModifyTime(String modifyTime) {
/* 176 */     this.modifyTime = modifyTime; }
/*     */ 
/*     */   public String getName() {
/* 179 */     return this.name; }
/*     */ 
/*     */   public void setName(String name) {
/* 182 */     this.name = name; }
/*     */ 
/*     */   public String getRange() {
/* 185 */     return this.range; }
/*     */ 
/*     */   public void setRange(String range) {
/* 188 */     this.range = range; }
/*     */ 
/*     */   public String getStatus() {
/* 191 */     return this.status; }
/*     */ 
/*     */   public void setStatus(String status) {
/* 194 */     this.status = status; }
/*     */ 
/*     */   public String getType() {
/* 197 */     return this.type; }
/*     */ 
/*     */   public void setType(String type) {
/* 200 */     this.type = type; }
/*     */ 
/*     */   public int getSeqNo() {
/* 203 */     return this.seqNo; }
/*     */ 
/*     */   public void setSeqNo(int seqNo) {
/* 206 */     this.seqNo = seqNo; }
/*     */ 
/*     */   public int getDataType() {
/* 209 */     return this.dataType; }
/*     */ 
/*     */   public void setDataType(int dataType) {
/* 212 */     this.dataType = dataType; }
/*     */ 
/*     */   public String getDataUnit() {
/* 215 */     return this.dataUnit; }
/*     */ 
/*     */   public void setDataUnit(String dataUnit) {
/* 218 */     this.dataUnit = dataUnit; }
/*     */ 
/*     */   public int getIsFunctional() {
/* 221 */     return this.isFunctional; }
/*     */ 
/*     */   public void setIsFunctional(int isFunctional) {
/* 224 */     this.isFunctional = isFunctional; }
/*     */ 
/*     */   public String getEditTip() {
/* 227 */     return this.editTip; }
/*     */ 
/*     */   public void setEditTip(String editTip) {
/* 230 */     this.editTip = editTip; }
/*     */ 
/*     */   public String getFuzzyOperator() {
/* 233 */     return this.fuzzyOperator; }
/*     */ 
/*     */   public void setFuzzyOperator(String fuzzyOperator) {
/* 236 */     this.fuzzyOperator = fuzzyOperator; }
/*     */ 
/*     */   public String getGtMostOperator() {
/* 239 */     return this.gtMostOperator; }
/*     */ 
/*     */   public void setGtMostOperator(String gtMostOperator) {
/* 242 */     this.gtMostOperator = gtMostOperator; }
/*     */ 
/*     */   public String getGtRangeOperator() {
/* 245 */     return this.gtRangeOperator; }
/*     */ 
/*     */   public void setGtRangeOperator(String gtRangeOperator) {
/* 248 */     this.gtRangeOperator = gtRangeOperator; }
/*     */ 
/*     */   public String getGtSingleMostOperator() {
/* 251 */     return this.gtSingleMostOperator; }
/*     */ 
/*     */   public void setGtSingleMostOperator(String gtSingleMostOperator) {
/* 254 */     this.gtSingleMostOperator = gtSingleMostOperator; }
/*     */ 
/*     */   public String getLtMostOperator() {
/* 257 */     return this.ltMostOperator; }
/*     */ 
/*     */   public void setLtMostOperator(String ltMostOperator) {
/* 260 */     this.ltMostOperator = ltMostOperator; }
/*     */ 
/*     */   public String getLtRangeOperator() {
/* 263 */     return this.ltRangeOperator; }
/*     */ 
/*     */   public void setLtRangeOperator(String ltRangeOperator) {
/* 266 */     this.ltRangeOperator = ltRangeOperator; }
/*     */ 
/*     */   public String getLtSingleMostOperator() {
/* 269 */     return this.ltSingleMostOperator; }
/*     */ 
/*     */   public void setLtSingleMostOperator(String ltSingleMostOperator) {
/* 272 */     this.ltSingleMostOperator = ltSingleMostOperator; }
/*     */ 
/*     */   public String getMostOperatorUnit() {
/* 275 */     return this.mostOperatorUnit; }
/*     */ 
/*     */   public void setMostOperatorUnit(String mostOperatorUnit) {
/* 278 */     this.mostOperatorUnit = mostOperatorUnit; }
/*     */ 
/*     */   public List<AttributeExtraInfoItem> getExtraInfoList() {
/* 281 */     return this.extraInfoList; }
/*     */ 
/*     */   public void setExtraInfoList(List<AttributeExtraInfoItem> extraInfoList) {
/* 284 */     this.extraInfoList = extraInfoList; }
/*     */ 
/*     */   public int getJoinSeqNo() {
/* 287 */     return this.joinSeqNo; }
/*     */ 
/*     */   public void setJoinSeqNo(int joinSeqNo) {
/* 290 */     this.joinSeqNo = joinSeqNo; }
/*     */ 
/*     */   public int getTableAlone() {
/* 293 */     return this.tableAlone; }
/*     */ 
/*     */   public void setTableAlone(int tableAlone) {
/* 296 */     this.tableAlone = tableAlone;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.AttributeDefinition
 * JD-Core Version:    0.5.3
 */