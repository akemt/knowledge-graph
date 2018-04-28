/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CubeItemWithChild
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4126617302748682350L;
/*    */   private String id;
/*    */   private String name;
/*    */   private String picName;
/*    */   private Map<String, String> childMap;
/*    */ 
/*    */   public Map<String, String> getChildMap()
/*    */   {
/* 20 */     return this.childMap; }
/*    */ 
/*    */   public void setChildMap(Map<String, String> childMap) {
/* 23 */     this.childMap = childMap; }
/*    */ 
/*    */   public String getId() {
/* 26 */     return this.id; }
/*    */ 
/*    */   public void setId(String id) {
/* 29 */     this.id = id; }
/*    */ 
/*    */   public String getName() {
/* 32 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 35 */     this.name = name; }
/*    */ 
/*    */   public String getPicName() {
/* 38 */     return this.picName; }
/*    */ 
/*    */   public void setPicName(String picName) {
/* 41 */     this.picName = picName;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.CubeItemWithChild
 * JD-Core Version:    0.5.3
 */