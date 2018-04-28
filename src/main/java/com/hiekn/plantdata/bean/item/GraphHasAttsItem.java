/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class GraphHasAttsItem extends GraphItem
/*    */ {
/*    */   private static final long serialVersionUID = 4934488249326001202L;
/*    */   private List<ServiceEntityItem> serviceEntityList;
/*    */ 
/*    */   public List<ServiceEntityItem> getServiceEntityList()
/*    */   {
/* 15 */     return this.serviceEntityList;
/*    */   }
/*    */ 
/*    */   public void setServiceEntityList(List<ServiceEntityItem> serviceEntityList) {
/* 19 */     this.serviceEntityList = serviceEntityList;
/*    */   }
/*    */ }
