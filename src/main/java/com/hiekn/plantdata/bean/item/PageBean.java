/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class PageBean<T>
/*    */ {
/*    */   private long dataNum;
/*    */   private long pageNum;
/*  8 */   private int dataPerPage = 20;
/*  9 */   private long curPage = 1L;
/* 10 */   private long nextPage = 0L;
/* 11 */   private long prevPage = 0L;
/*    */   private List list;
/*    */ 
/*    */   public PageBean()
/*    */   {
/*    */   }
/*    */ 
/*    */   public PageBean(long dataNum, long curPage, int dataPerPage)
/*    */   {
/* 20 */     this.dataNum = dataNum;
/* 21 */     this.curPage = curPage;
/* 22 */     this.dataPerPage = dataPerPage;
/* 23 */     this.pageNum = ((dataNum + dataPerPage - 1L) / dataPerPage);
/* 24 */     if (curPage != this.pageNum)
/* 25 */       this.nextPage = (curPage + 1L);
/* 26 */     if (curPage != 1L)
/* 27 */       this.prevPage = (curPage - 1L);
/*    */   }
/*    */ 
/*    */   public long getDataNum() {
/* 31 */     return this.dataNum;
/*    */   }
/*    */ 
/*    */   public void setDataNum(long dataNum) {
/* 35 */     this.dataNum = dataNum;
/*    */   }
/*    */ 
/*    */   public long getPageNum() {
/* 39 */     return this.pageNum;
/*    */   }
/*    */ 
/*    */   public void setPageNum(long pageNum) {
/* 43 */     this.pageNum = pageNum;
/*    */   }
/*    */ 
/*    */   public int getDataPerPage() {
/* 47 */     return this.dataPerPage;
/*    */   }
/*    */ 
/*    */   public void setDataPerPage(int dataPerPage) {
/* 51 */     this.dataPerPage = dataPerPage;
/*    */   }
/*    */ 
/*    */   public long getCurPage() {
/* 55 */     return this.curPage;
/*    */   }
/*    */ 
/*    */   public void setCurPage(long curPage) {
/* 59 */     this.curPage = curPage;
/*    */   }
/*    */ 
/*    */   public long getNextPage() {
/* 63 */     return this.nextPage;
/*    */   }
/*    */ 
/*    */   public void setNextPage(long nextPage) {
/* 67 */     this.nextPage = nextPage;
/*    */   }
/*    */ 
/*    */   public long getPrevPage() {
/* 71 */     return this.prevPage;
/*    */   }
/*    */ 
/*    */   public void setPrevPage(long prevPage) {
/* 75 */     this.prevPage = prevPage;
/*    */   }
/*    */ 
/*    */   public List getList() {
/* 79 */     return this.list;
/*    */   }
/*    */ 
/*    */   public void setList(List list) {
/* 83 */     this.list = list;
/*    */   }
/*    */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.PageBean
 * JD-Core Version:    0.5.3
 */