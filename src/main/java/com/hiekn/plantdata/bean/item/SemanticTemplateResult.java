/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SemanticTemplateResult
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6718716866648288707L;
/*    */   private List<EntityKGItem> entityList;
/*    */   private int entityCount;
/*    */ 
/*    */   public List<EntityKGItem> getEntityList()
/*    */   {
/* 17 */     return this.entityList;
/*    */   }
/*    */ 
/*    */   public void setEntityList(List<EntityKGItem> entityList) {
/* 21 */     this.entityList = entityList;
/*    */   }
/*    */ 
/*    */   public int getEntityCount() {
/* 25 */     return this.entityCount;
/*    */   }
/*    */ 
/*    */   public void setEntityCount(int entityCount) {
/* 29 */     this.entityCount = entityCount;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.SemanticTemplateResult
 * JD-Core Version:    0.5.3
 */