/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TreeJsonConceptItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5094134489372174371L;
/*    */   private long id;
/*    */   private String name;
/*    */   private List<TreeJsonConceptItem> pars;
/*    */   private List<TreeJsonConceptItem> sons;
/*    */   private List<ImportJsonInstanceItem> inss;
/*    */ 
/*    */   public long getId()
/*    */   {
/* 39 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(long id) {
/* 43 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public List<ImportJsonInstanceItem> getInss() {
/* 47 */     return this.inss;
/*    */   }
/*    */ 
/*    */   public void setInss(List<ImportJsonInstanceItem> inss) {
/* 51 */     this.inss = inss;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 55 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 59 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public List<TreeJsonConceptItem> getPars() {
/* 63 */     return this.pars;
/*    */   }
/*    */ 
/*    */   public void setPars(List<TreeJsonConceptItem> pars) {
/* 67 */     this.pars = pars;
/*    */   }
/*    */ 
/*    */   public List<TreeJsonConceptItem> getSons() {
/* 71 */     return this.sons;
/*    */   }
/*    */ 
/*    */   public void setSons(List<TreeJsonConceptItem> sons) {
/* 75 */     this.sons = sons;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.TreeJsonConceptItem
 * JD-Core Version:    0.5.3
 */