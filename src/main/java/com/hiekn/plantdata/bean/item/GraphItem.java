/*    */ package com.hiekn.plantdata.bean.item;
/*    */ 
/*    */

import com.hiekn.plantdata.Entity.Enterprise;

import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class GraphItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8484920776176269336L;
/*    */   private int minDistance;
/*    */   private List<EntityKGItem> searchEntityList;
/*    */   private List<Enterprise> entityList;
/*    */   private List<AttributeKGItem> attributeList;
/*    */   private List<RelationItem> relationList;
/*    */   private Map<Long, Map<Long, Integer>> allCountMap;
/*    */   private Map<Long, Map<Long, Integer>> leftCountMap;
/*    */   private Map<Long, Map<Integer, Integer>> leftAttCountMap;
/*    */ 
/*    */   public List<Enterprise> getEntityList()
/*    */   {
/* 31 */     return this.entityList; }
/*    */ 
/*    */   public void setEntityList(List<Enterprise> entityList) {
/* 34 */     this.entityList = entityList; }
/*    */ 
/*    */   public List<RelationItem> getRelationList() {
/* 37 */     return this.relationList; }
/*    */
/*    */   public void setRelationList(List<RelationItem> relationList) {
/* 40 */     this.relationList = relationList; }
/*    */ 
/*    */   public int getMinDistance() {
/* 43 */     return this.minDistance; }
/*    */ 
/*    */   public void setMinDistance(int minDistance) {
/* 46 */     this.minDistance = minDistance; }
/*    */ 
/*    */   public List<EntityKGItem> getSearchEntityList() {
/* 49 */     return this.searchEntityList; }
/*    */ 
/*    */   public void setSearchEntityList(List<EntityKGItem> searchEntityList) {
/* 52 */     this.searchEntityList = searchEntityList; }
/*    */ 
/*    */   public List<AttributeKGItem> getAttributeList() {
/* 55 */     return this.attributeList; }
/*    */ 
/*    */   public void setAttributeList(List<AttributeKGItem> attributeList) {
/* 58 */     this.attributeList = attributeList; }
/*    */ 
/*    */   public Map<Long, Map<Long, Integer>> getAllCountMap() {
/* 61 */     return this.allCountMap; }
/*    */ 
/*    */   public void setAllCountMap(Map<Long, Map<Long, Integer>> allCountMap) {
/* 64 */     this.allCountMap = allCountMap; }
/*    */ 
/*    */   public Map<Long, Map<Long, Integer>> getLeftCountMap() {
/* 67 */     return this.leftCountMap; }
/*    */ 
/*    */   public void setLeftCountMap(Map<Long, Map<Long, Integer>> leftCountMap) {
/* 70 */     this.leftCountMap = leftCountMap; }
/*    */ 
/*    */   public Map<Long, Map<Integer, Integer>> getLeftAttCountMap() {
/* 73 */     return this.leftAttCountMap; }
/*    */ 
/*    */   public void setLeftAttCountMap(Map<Long, Map<Integer, Integer>> leftAttCountMap) {
/* 76 */     this.leftAttCountMap = leftAttCountMap;
/*    */   }
/*    */ }
