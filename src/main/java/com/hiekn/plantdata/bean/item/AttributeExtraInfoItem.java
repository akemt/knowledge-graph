/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AttributeExtraInfoItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3383785867548196236L;
/*    */   private int seqNo;
/*    */   private String name;
/*    */   private int dataType;
/*    */   private int type;
/*    */   private String objRange;
/*    */ 
/*    */   public int getDataType()
/*    */   {
/* 21 */     return this.dataType; }
/*    */ 
/*    */   public void setDataType(int dataType) {
/* 24 */     this.dataType = dataType; }
/*    */ 
/*    */   public String getName() {
/* 27 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 30 */     this.name = name; }
/*    */ 
/*    */   public int getSeqNo() {
/* 33 */     return this.seqNo; }
/*    */ 
/*    */   public void setSeqNo(int seqNo) {
/* 36 */     this.seqNo = seqNo; }
/*    */ 
/*    */   public int getType() {
/* 39 */     return this.type; }
/*    */ 
/*    */   public void setType(int type) {
/* 42 */     this.type = type; }
/*    */ 
/*    */   public String getObjRange() {
/* 45 */     return this.objRange; }
/*    */ 
/*    */   public void setObjRange(String objRange) {
/* 48 */     this.objRange = objRange;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.AttributeExtraInfoItem
 * JD-Core Version:    0.5.3
 */