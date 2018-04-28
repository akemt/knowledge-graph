/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SemanticSearchResult
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8876310680259816642L;
/*     */   private List<String> parsedConceptList;
/*     */   private List<String> parsedEntityList;
/*     */   private List<String> parseResult;
/*     */   private int type;
/*     */   private String dataValue;
/*     */   private int dataType;
/*     */   private List<String> entityList;
/*     */   private String dataUnit;
/*     */ 
/*     */   public String getDataValue()
/*     */   {
/*  57 */     return this.dataValue;
/*     */   }
/*     */ 
/*     */   public void setDataValue(String dataValue) {
/*  61 */     this.dataValue = dataValue;
/*     */   }
/*     */ 
/*     */   public List<String> getEntityList() {
/*  65 */     return this.entityList;
/*     */   }
/*     */ 
/*     */   public void setEntityList(List<String> entityList) {
/*  69 */     this.entityList = entityList;
/*     */   }
/*     */ 
/*     */   public int getType() {
/*  73 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(int type) {
/*  77 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public List<String> getParseResult() {
/*  81 */     return this.parseResult;
/*     */   }
/*     */ 
/*     */   public void setParseResult(List<String> parseResult) {
/*  85 */     this.parseResult = parseResult;
/*     */   }
/*     */ 
/*     */   public String getDataUnit() {
/*  89 */     return this.dataUnit;
/*     */   }
/*     */ 
/*     */   public void setDataUnit(String dataUnit) {
/*  93 */     this.dataUnit = dataUnit;
/*     */   }
/*     */ 
/*     */   public List<String> getParsedEntityList() {
/*  97 */     return this.parsedEntityList;
/*     */   }
/*     */ 
/*     */   public void setParsedEntityList(List<String> parsedEntityList) {
/* 101 */     this.parsedEntityList = parsedEntityList;
/*     */   }
/*     */ 
/*     */   public int getDataType() {
/* 105 */     return this.dataType;
/*     */   }
/*     */ 
/*     */   public void setDataType(int dataType) {
/* 109 */     this.dataType = dataType;
/*     */   }
/*     */ 
/*     */   public List<String> getParsedConceptList() {
/* 113 */     return this.parsedConceptList;
/*     */   }
/*     */ 
/*     */   public void setParsedConceptList(List<String> parsedConceptList) {
/* 117 */     this.parsedConceptList = parsedConceptList;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.SemanticSearchResult
 * JD-Core Version:    0.5.3
 */