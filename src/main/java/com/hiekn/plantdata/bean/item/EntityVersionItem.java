/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class EntityVersionItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -487610611491264461L;
/*     */   private long id;
/*     */   private String id_;
/*     */   private long entityId;
/*     */   private String name;
/*     */   private String type;
/*     */   private String abstraction;
/*     */   private String meaningTag;
/*     */   private String syns;
/*     */   private String pars;
/*     */   private String sons;
/*     */   private String atts;
/*     */   private String inss;
/*     */   private String imgFlag;
/*     */   private int version;
/*     */   private String editUser;
/*     */   private String editTime;
/*     */   private String checkUser;
/*     */   private String checkTime;
/*     */   private String status;
/*     */ 
/*     */   public String getAtts()
/*     */   {
/* 108 */     return this.atts; }
/*     */ 
/*     */   public void setAtts(String atts) {
/* 111 */     this.atts = atts; }
/*     */ 
/*     */   public String getInss() {
/* 114 */     return this.inss; }
/*     */ 
/*     */   public void setInss(String inss) {
/* 117 */     this.inss = inss; }
/*     */ 
/*     */   public String getName() {
/* 120 */     return this.name; }
/*     */ 
/*     */   public void setName(String name) {
/* 123 */     this.name = name; }
/*     */ 
/*     */   public String getPars() {
/* 126 */     return this.pars; }
/*     */ 
/*     */   public void setPars(String pars) {
/* 129 */     this.pars = pars; }
/*     */ 
/*     */   public String getSons() {
/* 132 */     return this.sons; }
/*     */ 
/*     */   public void setSons(String sons) {
/* 135 */     this.sons = sons; }
/*     */ 
/*     */   public String getSyns() {
/* 138 */     return this.syns; }
/*     */ 
/*     */   public void setSyns(String syns) {
/* 141 */     this.syns = syns; }
/*     */ 
/*     */   public String getType() {
/* 144 */     return this.type; }
/*     */ 
/*     */   public void setType(String type) {
/* 147 */     this.type = type; }
/*     */ 
/*     */   public String getAbstraction() {
/* 150 */     return this.abstraction; }
/*     */ 
/*     */   public void setAbstraction(String abstraction) {
/* 153 */     this.abstraction = abstraction; }
/*     */ 
/*     */   public long getId() {
/* 156 */     return this.id; }
/*     */ 
/*     */   public void setId(long id) {
/* 159 */     this.id = id; }
/*     */ 
/*     */   public String getImgFlag() {
/* 162 */     return this.imgFlag; }
/*     */ 
/*     */   public void setImgFlag(String imgFlag) {
/* 165 */     this.imgFlag = imgFlag; }
/*     */ 
/*     */   public int getVersion() {
/* 168 */     return this.version; }
/*     */ 
/*     */   public void setVersion(int version) {
/* 171 */     this.version = version; }
/*     */ 
/*     */   public String getMeaningTag() {
/* 174 */     return this.meaningTag; }
/*     */ 
/*     */   public void setMeaningTag(String meaningTag) {
/* 177 */     this.meaningTag = meaningTag; }
/*     */ 
/*     */   public String getCheckTime() {
/* 180 */     return this.checkTime; }
/*     */ 
/*     */   public void setCheckTime(String checkTime) {
/* 183 */     this.checkTime = checkTime; }
/*     */ 
/*     */   public String getCheckUser() {
/* 186 */     return this.checkUser; }
/*     */ 
/*     */   public void setCheckUser(String checkUser) {
/* 189 */     this.checkUser = checkUser; }
/*     */ 
/*     */   public String getEditTime() {
/* 192 */     return this.editTime; }
/*     */ 
/*     */   public void setEditTime(String editTime) {
/* 195 */     this.editTime = editTime; }
/*     */ 
/*     */   public String getEditUser() {
/* 198 */     return this.editUser; }
/*     */ 
/*     */   public void setEditUser(String editUser) {
/* 201 */     this.editUser = editUser; }
/*     */ 
/*     */   public String getStatus() {
/* 204 */     return this.status; }
/*     */ 
/*     */   public void setStatus(String status) {
/* 207 */     this.status = status; }
/*     */ 
/*     */   public long getEntityId() {
/* 210 */     return this.entityId; }
/*     */ 
/*     */   public void setEntityId(long entityId) {
/* 213 */     this.entityId = entityId; }
/*     */ 
/*     */   public String getId_() {
/* 216 */     return this.id_; }
/*     */ 
/*     */   public void setId_(String id_) {
/* 219 */     this.id_ = id_;
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.EntityVersionItem
 * JD-Core Version:    0.5.3
 */