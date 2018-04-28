/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ 
/*    */ public class BaikeItem
/*    */ {
/*    */   private String url;
/*    */   private String articleTitle;
/*    */   private String articleTitleInfo;
/*    */   private Boolean isPolysemy;
/*    */   private String abs;
/*    */   private HashMap<String, String> infoBoxMap;
/*    */   private List<String> categoryList;
/*    */   private String articleHTMLId;
/*    */   private List<PolysemyItem> polysemy;
/*    */ 
/*    */   public String getUrl()
/*    */   {
/* 47 */     return this.url; }
/*    */ 
/*    */   public void setUrl(String url) {
/* 50 */     this.url = url; }
/*    */ 
/*    */   public String getArticleTitle() {
/* 53 */     return this.articleTitle; }
/*    */ 
/*    */   public void setArticleTitle(String articleTitle) {
/* 56 */     this.articleTitle = articleTitle; }
/*    */ 
/*    */   public String getArticleTitleInfo() {
/* 59 */     return this.articleTitleInfo; }
/*    */ 
/*    */   public void setArticleTitleInfo(String articleTitleInfo) {
/* 62 */     this.articleTitleInfo = articleTitleInfo; }
/*    */ 
/*    */   public Boolean getIsPolysemy() {
/* 65 */     return this.isPolysemy; }
/*    */ 
/*    */   public void setIsPolysemy(Boolean isPolysemy) {
/* 68 */     this.isPolysemy = isPolysemy; }
/*    */ 
/*    */   public String getAbs() {
/* 71 */     return this.abs; }
/*    */ 
/*    */   public void setAbs(String abs) {
/* 74 */     this.abs = abs; }
/*    */ 
/*    */   public HashMap<String, String> getInfoBoxMap() {
/* 77 */     return this.infoBoxMap; }
/*    */ 
/*    */   public void setInfoBoxMap(HashMap<String, String> infoBoxMap) {
/* 80 */     this.infoBoxMap = infoBoxMap; }
/*    */ 
/*    */   public List<String> getCategoryList() {
/* 83 */     return this.categoryList; }
/*    */ 
/*    */   public void setCategoryList(List<String> categoryList) {
/* 86 */     this.categoryList = categoryList; }
/*    */ 
/*    */   public String getArticleHTMLId() {
/* 89 */     return this.articleHTMLId; }
/*    */ 
/*    */   public void setArticleHTMLId(String articleHTMLId) {
/* 92 */     this.articleHTMLId = articleHTMLId; }
/*    */ 
/*    */   public List<PolysemyItem> getPolysemy() {
/* 95 */     return this.polysemy; }
/*    */ 
/*    */   public void setPolysemy(List<PolysemyItem> polysemy) {
/* 98 */     this.polysemy = polysemy;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.BaikeItem
 * JD-Core Version:    0.5.3
 */