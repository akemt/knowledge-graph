/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ImportJsonAttributeItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5094134489372174371L;
/*    */   private int adId;
/*    */   private String name;
/*    */   private String type;
/*    */   private String value;
/*    */   private int mapROA;
/*    */   private List<String[]> mapValue;
/*    */ 
/*    */   public int getAdId()
/*    */   {
/* 45 */     return this.adId; }
/*    */ 
/*    */   public void setAdId(int adId) {
/* 48 */     this.adId = adId; }
/*    */ 
/*    */   public String getName() {
/* 51 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 54 */     this.name = name; }
/*    */ 
/*    */   public String getType() {
/* 57 */     return this.type; }
/*    */ 
/*    */   public void setType(String type) {
/* 60 */     this.type = type; }
/*    */ 
/*    */   public String getValue() {
/* 63 */     return this.value; }
/*    */ 
/*    */   public void setValue(String value) {
/* 66 */     this.value = value; }
/*    */ 
/*    */   public List<String[]> getMapValue() {
/* 69 */     return this.mapValue; }
/*    */ 
/*    */   public void setMapValue(List<String[]> mapValue) {
/* 72 */     this.mapValue = mapValue; }
/*    */ 
/*    */   public int getMapROA() {
/* 75 */     return this.mapROA; }
/*    */ 
/*    */   public void setMapROA(int mapROA) {
/* 78 */     this.mapROA = mapROA;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.ImportJsonAttributeItem
 * JD-Core Version:    0.5.3
 */