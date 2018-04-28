/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ImportJsonInstanceItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5094134489372174371L;
/*    */   private long id;
/*    */   private String name;
/*    */   private String meaningTag;
/*    */   private String abs;
/*    */   private String img;
/*    */   private List<Long> pars;
/*    */   private List<String> syns;
/*    */   private List<ImportJsonAttributeItem> atts;
/*    */ 
/*    */   public String getMeaningTag()
/*    */   {
/* 54 */     return this.meaningTag; }
/*    */ 
/*    */   public void setMeaningTag(String meaningTag) {
/* 57 */     this.meaningTag = meaningTag; }
/*    */ 
/*    */   public String getName() {
/* 60 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 63 */     this.name = name; }
/*    */ 
/*    */   public List<String> getSyns() {
/* 66 */     return this.syns; }
/*    */ 
/*    */   public void setSyns(List<String> syns) {
/* 69 */     this.syns = syns; }
/*    */ 
/*    */   public long getId() {
/* 72 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 75 */     this.id = id; }
/*    */ 
/*    */   public List<Long> getPars() {
/* 78 */     return this.pars; }
/*    */ 
/*    */   public void setPars(List<Long> pars) {
/* 81 */     this.pars = pars; }
/*    */ 
/*    */   public List<ImportJsonAttributeItem> getAtts() {
/* 84 */     return this.atts; }
/*    */ 
/*    */   public void setAtts(List<ImportJsonAttributeItem> atts) {
/* 87 */     this.atts = atts; }
/*    */ 
/*    */   public String getAbs() {
/* 90 */     return this.abs; }
/*    */ 
/*    */   public void setAbs(String abs) {
/* 93 */     this.abs = abs; }
/*    */ 
/*    */   public String getImg() {
/* 96 */     return this.img; }
/*    */ 
/*    */   public void setImg(String img) {
/* 99 */     this.img = img;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ImportJsonInstanceItem
 * JD-Core Version:    0.5.3
 */