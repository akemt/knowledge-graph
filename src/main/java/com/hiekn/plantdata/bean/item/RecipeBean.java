/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RecipeBean
/*    */ {
/*    */   private List<Long> symptoms;
/*    */   private Map<Long, DrugBean> drugMap;
/*    */   List<String> optList;
/*    */ 
/*    */   public RecipeBean()
/*    */   {
/* 17 */     this.drugMap = new LinkedHashMap();
/*    */ 
/* 21 */     this.optList = new ArrayList(); }
/*    */ 
/*    */   public Map<Long, DrugBean> getDrugMap() {
/* 24 */     return this.drugMap;
/*    */   }
/*    */ 
/*    */   public void addOrRemoveDrug(long drugId, double amount)
/*    */   {
/* 33 */     if (this.drugMap.containsKey(Long.valueOf(drugId))) {
/* 34 */       ((DrugBean)this.drugMap.get(Long.valueOf(drugId))).setAmount(amount + ((DrugBean)this.drugMap.get(Long.valueOf(drugId))).getAmount());
/*    */     }
/*    */     else {
/* 37 */       DrugBean drug = new DrugBean();
/* 38 */       drug.setId(drugId);
/* 39 */       drug.setAmount(amount);
/* 40 */       this.drugMap.put(Long.valueOf(drugId), drug);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void addDrugMethod(long drugId, String method)
/*    */   {
/* 51 */     ((DrugBean)this.drugMap.get(Long.valueOf(drugId))).setMethod(method);
/*    */   }
/*    */ 
/*    */   public void removeDrug(long drugId)
/*    */   {
/* 59 */     this.drugMap.remove(Long.valueOf(drugId));
/*    */   }
/*    */ 
/*    */   public List<Long> getSymptoms() {
/* 63 */     return this.symptoms;
/*    */   }
/*    */ 
/*    */   public void setSymptoms(List<Long> symptoms) {
/* 67 */     this.symptoms = symptoms;
/*    */   }
/*    */ 
/*    */   public void recordRecipeOpt(String opt) {
/* 71 */     this.optList.add(opt);
/*    */   }
/*    */ 
/*    */   public List<String> getOptList() {
/* 75 */     return this.optList;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.RecipeBean
 * JD-Core Version:    0.5.3
 */