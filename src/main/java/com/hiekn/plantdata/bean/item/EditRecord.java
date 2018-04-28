/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class EditRecord
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6716886346274335308L;
/*     */   private String id;
/*     */   private long entityId;
/*     */   private String entityName;
/*     */   private String editUser;
/*     */   private float baseVersion;
/*     */   private String operations;
/*     */   private String description;
/*     */   private String editTime;
/*     */   private String checkUser;
/*     */   private String checkTime;
/*     */   private String status;
/*     */   private boolean couldDelete;
/*     */   private boolean couldCheck;
/*     */ 
/*     */   public String getCheckTime()
/*     */   {
/*  28 */     return this.checkTime; }
/*     */ 
/*     */   public void setCheckTime(String checkTime) {
/*  31 */     this.checkTime = checkTime; }
/*     */ 
/*     */   public String getCheckUser() {
/*  34 */     return this.checkUser; }
/*     */ 
/*     */   public void setCheckUser(String checkUser) {
/*  37 */     this.checkUser = checkUser; }
/*     */ 
/*     */   public String getDescription() {
/*  40 */     return this.description; }
/*     */ 
/*     */   public void setDescription(String description) {
/*  43 */     this.description = description; }
/*     */ 
/*     */   public String getEditTime() {
/*  46 */     return this.editTime; }
/*     */ 
/*     */   public void setEditTime(String editTime) {
/*  49 */     this.editTime = editTime; }
/*     */ 
/*     */   public String getEditUser() {
/*  52 */     return this.editUser; }
/*     */ 
/*     */   public void setEditUser(String editUser) {
/*  55 */     this.editUser = editUser; }
/*     */ 
/*     */   public long getEntityId() {
/*  58 */     return this.entityId; }
/*     */ 
/*     */   public void setEntityId(long entityId) {
/*  61 */     this.entityId = entityId; }
/*     */ 
/*     */   public String getId() {
/*  64 */     return this.id; }
/*     */ 
/*     */   public void setId(String id) {
/*  67 */     this.id = id; }
/*     */ 
/*     */   public String getOperations() {
/*  70 */     return this.operations; }
/*     */ 
/*     */   public void setOperations(String operations) {
/*  73 */     this.operations = operations; }
/*     */ 
/*     */   public String getStatus() {
/*  76 */     return this.status; }
/*     */ 
/*     */   public void setStatus(String status) {
/*  79 */     this.status = status; }
/*     */ 
/*     */   public boolean isCouldCheck() {
/*  82 */     return this.couldCheck; }
/*     */ 
/*     */   public void setCouldCheck(boolean couldCheck) {
/*  85 */     this.couldCheck = couldCheck; }
/*     */ 
/*     */   public boolean isCouldDelete() {
/*  88 */     return this.couldDelete; }
/*     */ 
/*     */   public void setCouldDelete(boolean couldDelete) {
/*  91 */     this.couldDelete = couldDelete; }
/*     */ 
/*     */   public String getEntityName() {
/*  94 */     return this.entityName; }
/*     */ 
/*     */   public void setEntityName(String entityName) {
/*  97 */     this.entityName = entityName; }
/*     */ 
/*     */   public float getBaseVersion() {
/* 100 */     return this.baseVersion; }
/*     */ 
/*     */   public void setBaseVersion(float baseVersion) {
/* 103 */     this.baseVersion = baseVersion;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.EditRecord
 * JD-Core Version:    0.5.3
 */