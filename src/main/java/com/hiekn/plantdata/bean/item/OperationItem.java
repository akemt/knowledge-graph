/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OperationItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7313526536328281007L;
/*    */   private long id;
/*    */   private String name;
/*    */   private String type;
/*    */   private String meaning;
/*    */   private String abs;
/*    */   private String syns;
/*    */   private String pars;
/*    */   private String sons;
/*    */   private List<String> operationList;
/*    */ 
/*    */   public String getAbs()
/*    */   {
/* 19 */     return this.abs; }
/*    */ 
/*    */   public void setAbs(String abs) {
/* 22 */     this.abs = abs; }
/*    */ 
/*    */   public long getId() {
/* 25 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 28 */     this.id = id; }
/*    */ 
/*    */   public String getMeaning() {
/* 31 */     return this.meaning; }
/*    */ 
/*    */   public void setMeaning(String meaning) {
/* 34 */     this.meaning = meaning; }
/*    */ 
/*    */   public String getName() {
/* 37 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 40 */     this.name = name; }
/*    */ 
/*    */   public List<String> getOperationList() {
/* 43 */     return this.operationList; }
/*    */ 
/*    */   public void setOperationList(List<String> operationList) {
/* 46 */     this.operationList = operationList; }
/*    */ 
/*    */   public String getPars() {
/* 49 */     return this.pars; }
/*    */ 
/*    */   public void setPars(String pars) {
/* 52 */     this.pars = pars; }
/*    */ 
/*    */   public String getSons() {
/* 55 */     return this.sons; }
/*    */ 
/*    */   public void setSons(String sons) {
/* 58 */     this.sons = sons; }
/*    */ 
/*    */   public String getSyns() {
/* 61 */     return this.syns; }
/*    */ 
/*    */   public void setSyns(String syns) {
/* 64 */     this.syns = syns; }
/*    */ 
/*    */   public String getType() {
/* 67 */     return this.type; }
/*    */ 
/*    */   public void setType(String type) {
/* 70 */     this.type = type;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.OperationItem
 * JD-Core Version:    0.5.3
 */