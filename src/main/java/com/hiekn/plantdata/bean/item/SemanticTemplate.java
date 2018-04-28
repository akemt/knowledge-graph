/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SemanticTemplate
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5111515280835206045L;
/*    */   private Map<Long, List<SemanticTemplateItem>> templateMap;
/*    */   private List<Long> typeList;
/*    */ 
/*    */   public Map<Long, List<SemanticTemplateItem>> getTemplateMap()
/*    */   {
/* 23 */     return this.templateMap;
/*    */   }
/*    */ 
/*    */   public void setTemplateMap(Map<Long, List<SemanticTemplateItem>> templateMap) {
/* 27 */     this.templateMap = templateMap;
/*    */   }
/*    */ 
/*    */   public List<Long> getTypeList() {
/* 31 */     return this.typeList;
/*    */   }
/*    */ 
/*    */   public void setTypeList(List<Long> typeList) {
/* 35 */     this.typeList = typeList;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.SemanticTemplate
 * JD-Core Version:    0.5.3
 */