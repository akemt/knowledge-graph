/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AddAttributeItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5939693033294892857L;
/*    */   private int attDefId;
/*    */   private String attName;
/*    */   private int attType;
/*    */   private String dataValue;
/*    */   private long objValId;
/*    */ 
/*    */   public int getAttDefId()
/*    */   {
/* 40 */     return this.attDefId;
/*    */   }
/*    */ 
/*    */   public void setAttDefId(int attDefId) {
/* 44 */     this.attDefId = attDefId;
/*    */   }
/*    */ 
/*    */   public String getAttName() {
/* 48 */     return this.attName;
/*    */   }
/*    */ 
/*    */   public void setAttName(String attName) {
/* 52 */     this.attName = attName;
/*    */   }
/*    */ 
/*    */   public int getAttType() {
/* 56 */     return this.attType;
/*    */   }
/*    */ 
/*    */   public void setAttType(int attType) {
/* 60 */     this.attType = attType;
/*    */   }
/*    */ 
/*    */   public String getDataValue() {
/* 64 */     return this.dataValue;
/*    */   }
/*    */ 
/*    */   public void setDataValue(String dataValue) {
/* 68 */     this.dataValue = dataValue;
/*    */   }
/*    */ 
/*    */   public long getObjValId() {
/* 72 */     return this.objValId;
/*    */   }
/*    */ 
/*    */   public void setObjValId(long objValId) {
/* 76 */     this.objValId = objValId;
/*    */   }
/*    */ 
/*    */   public static long getSerialversionuid() {
/* 80 */     return 5939693033294892857L;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.AddAttributeItem
 * JD-Core Version:    0.5.3
 */