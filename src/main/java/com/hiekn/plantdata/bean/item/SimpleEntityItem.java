/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SimpleEntityItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2280774782590107819L;
/*    */   private long id;
/*    */   private String name;
/*    */   private String meaningTag;
/*    */   private List<String> syns;
/*    */ 
/*    */   public SimpleEntityItem()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SimpleEntityItem(long id, String name)
/*    */   {
/* 23 */     this.id = id;
/* 24 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public long getId() {
/* 28 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 31 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 34 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 37 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getMeaningTag() {
/* 41 */     return this.meaningTag;
/*    */   }
/*    */ 
/*    */   public void setMeaningTag(String meaningTag) {
/* 45 */     this.meaningTag = meaningTag;
/*    */   }
/*    */ 
/*    */   public List<String> getSyns() {
/* 49 */     return this.syns;
/*    */   }
/*    */ 
/*    */   public void setSyns(List<String> syns) {
/* 53 */     this.syns = syns;
/*    */   }
/*    */ }
