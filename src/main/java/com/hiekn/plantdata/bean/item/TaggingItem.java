/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class TaggingItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1804094251536808140L;
/*    */   private long id;
/*    */   private String name;
/*    */   private double score;
/*    */   private Set<String> refs;
/*    */ 
/*    */   public long getId()
/*    */   {
/* 20 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 23 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 26 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 29 */     this.name = name; }
/*    */ 
/*    */   public Set<String> getRefs() {
/* 32 */     return this.refs; }
/*    */ 
/*    */   public void setRefs(Set<String> refs) {
/* 35 */     this.refs = refs; }
/*    */ 
/*    */   public double getScore() {
/* 38 */     return this.score; }
/*    */ 
/*    */   public void setScore(double score) {
/* 41 */     this.score = score;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.TaggingItem
 * JD-Core Version:    0.5.3
 */