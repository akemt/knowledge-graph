/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DroolsRuleItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7188276969067686691L;
/*    */   private long id;
/*    */   private long entityId;
/*    */   private String entityName;
/*    */   private String ruleGround;
/*    */   private String ruleContent;
/*    */ 
/*    */   public long getEntityId()
/*    */   {
/* 17 */     return this.entityId; }
/*    */ 
/*    */   public void setEntityId(long entityId) {
/* 20 */     this.entityId = entityId; }
/*    */ 
/*    */   public long getId() {
/* 23 */     return this.id; }
/*    */ 
/*    */   public void setId(long id) {
/* 26 */     this.id = id; }
/*    */ 
/*    */   public String getRuleContent() {
/* 29 */     return this.ruleContent; }
/*    */ 
/*    */   public void setRuleContent(String ruleContent) {
/* 32 */     this.ruleContent = ruleContent; }
/*    */ 
/*    */   public String getRuleGround() {
/* 35 */     return this.ruleGround; }
/*    */ 
/*    */   public void setRuleGround(String ruleGround) {
/* 38 */     this.ruleGround = ruleGround; }
/*    */ 
/*    */   public String getEntityName() {
/* 41 */     return this.entityName; }
/*    */ 
/*    */   public void setEntityName(String entityName) {
/* 44 */     this.entityName = entityName;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.DroolsRuleItem
 * JD-Core Version:    0.5.3
 */