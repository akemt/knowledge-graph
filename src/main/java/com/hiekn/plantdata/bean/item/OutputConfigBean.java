/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class OutputConfigBean
/*    */ {
/*    */   private String targetType;
/*    */   private String targetHost;
/*    */   private int targetPort;
/*    */   private String clusterName;
/*    */   private String targetDB;
/*    */   private String targetCollection;
/*    */   private int option;
/*    */   private Map<String, String> mappingField;
/*    */ 
/*    */   public String getClusterName()
/*    */   {
/* 41 */     return this.clusterName; }
/*    */ 
/*    */   public void setClusterName(String clusterName) {
/* 44 */     this.clusterName = clusterName; }
/*    */ 
/*    */   public String getTargetType() {
/* 47 */     return this.targetType; }
/*    */ 
/*    */   public void setTargetType(String targetType) {
/* 50 */     this.targetType = targetType; }
/*    */ 
/*    */   public String getTargetHost() {
/* 53 */     return this.targetHost; }
/*    */ 
/*    */   public void setTargetHost(String targetHost) {
/* 56 */     this.targetHost = targetHost; }
/*    */ 
/*    */   public int getTargetPort() {
/* 59 */     return this.targetPort; }
/*    */ 
/*    */   public void setTargetPort(int targetPort) {
/* 62 */     this.targetPort = targetPort; }
/*    */ 
/*    */   public String getTargetDB() {
/* 65 */     return this.targetDB; }
/*    */ 
/*    */   public void setTargetDB(String targetDB) {
/* 68 */     this.targetDB = targetDB; }
/*    */ 
/*    */   public String getTargetCollection() {
/* 71 */     return this.targetCollection; }
/*    */ 
/*    */   public void setTargetCollection(String targetCollection) {
/* 74 */     this.targetCollection = targetCollection; }
/*    */ 
/*    */   public int getOption() {
/* 77 */     return this.option; }
/*    */ 
/*    */   public void setOption(int option) {
/* 80 */     this.option = option; }
/*    */ 
/*    */   public Map<String, String> getMappingField() {
/* 83 */     return this.mappingField; }
/*    */ 
/*    */   public void setMappingField(Map<String, String> mappingField) {
/* 86 */     this.mappingField = mappingField;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.OutputConfigBean
 * JD-Core Version:    0.5.3
 */