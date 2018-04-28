/*    */ package com.hiekn.plantdata.bean;
/*    */ 
/*    */

import com.hiekn.plantdata.bean.graph.EntityBean;
import com.hiekn.plantdata.bean.graph.KVBean;

import java.util.ArrayList;
import java.util.List;

/*    */
/*    */
/*    */

/*    */
/*    */ public class EntityProfileBean
/*    */ {
/*    */   private EntityBean self;
/*    */   private List<EntityBean> pars;
/*    */   private List<EntityBean> sons;
/*    */   private List<KVBean<String, List<EntityBean>>> atts;
/*    */ 
/*    */   public EntityBean getSelf()
/*    */   {
/* 17 */     return this.self; }
/*    */ 
/*    */   public void setSelf(EntityBean self) {
/* 20 */     this.self = self; }
/*    */ 
/*    */   public List<EntityBean> getPars() {
/* 23 */     return this.pars; }
/*    */ 
/*    */   public void setPars(List<EntityBean> pars) {
/* 26 */     this.pars = pars; }
/*    */ 
/*    */   public List<EntityBean> getSons() {
/* 29 */     return this.sons; }
/*    */ 
/*    */   public void setSons(List<EntityBean> sons) {
/* 32 */     this.sons = sons; }
/*    */ 
/*    */   public List<KVBean<String, List<EntityBean>>> getAtts() {
/* 35 */     return this.atts;
/*    */   }
/*    */ 
/*    */   public void addAtts(String k, List<EntityBean> v) {
/* 39 */     if (this.atts == null) {
/* 40 */       this.atts = new ArrayList();
/*    */     }
/*    */ 
/* 43 */     this.atts.add(new KVBean(k, v));
/*    */   }
/*    */ }

/* Location:           E:\知识图谱-项目平台\知识图谱\姚远拷贝到软院程序\work\tomcat\webapps\plantdata_console\WEB-INF\classes\
 * Qualified Name:     com.hiekn.plantdata.bean.EntityProfileBean
 * JD-Core Version:    0.5.3
 */