/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ public class KGResultItem<T>
/*    */ {
/*    */   private String code;
/*    */   private String msg;
/*    */   private T data;
/*    */ 
/*    */   public String getCode()
/*    */   {
/* 12 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 16 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getMsg() {
/* 20 */     return this.msg;
/*    */   }
/*    */ 
/*    */   public void setMsg(String msg) {
/* 24 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public T getData() {
/* 28 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setData(T data) {
/* 32 */     this.data = data;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.KGResultItem
 * JD-Core Version:    0.5.3
 */