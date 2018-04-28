/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class KGResultListItem<T>
/*    */ {
/*    */   private String code;
/*    */   private String msg;
/*    */   private List<T> data;
/*    */ 
/*    */   public String getCode()
/*    */   {
/* 14 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 18 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getMsg() {
/* 22 */     return this.msg;
/*    */   }
/*    */ 
/*    */   public void setMsg(String msg) {
/* 26 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public List<T> getData() {
/* 30 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setData(List<T> data) {
/* 34 */     this.data = data;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.KGResultListItem
 * JD-Core Version:    0.5.3
 */