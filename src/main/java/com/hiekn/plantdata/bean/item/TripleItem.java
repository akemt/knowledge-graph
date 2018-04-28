///*    */ package com.hiekn.plantdata.bean.item;
///*    */
///*    */ import com.mongodb.DBObject;
///*    */ import java.io.Serializable;
///*    */ import java.util.Map;
///*    */
///*    */ public class TripleItem
///*    */   implements Serializable
///*    */ {
///*    */   private static final long serialVersionUID = -5253412708739319395L;
///*    */   private String _id;
///*    */   private long entityId;
///*    */   private int attrId;
///*    */   private long attrValue;
///*    */   private Map<String, String> infoMap;
///*    */   private DBObject extraInfoMap;
///*    */
///*    */   public String get_id()
///*    */   {
///* 18 */     return this._id; }
///*    */
///*    */   public void set_id(String _id) {
///* 21 */     this._id = _id; }
///*    */
///*    */   public int getAttrId() {
///* 24 */     return this.attrId; }
///*    */
///*    */   public void setAttrId(int attrId) {
///* 27 */     this.attrId = attrId; }
///*    */
///*    */   public long getAttrValue() {
///* 30 */     return this.attrValue; }
///*    */
///*    */   public void setAttrValue(long attrValue) {
///* 33 */     this.attrValue = attrValue; }
///*    */
///*    */   public long getEntityId() {
///* 36 */     return this.entityId; }
///*    */
///*    */   public void setEntityId(long entityId) {
///* 39 */     this.entityId = entityId; }
///*    */
///*    */   public Map<String, String> getInfoMap() {
///* 42 */     return this.infoMap; }
///*    */
///*    */   public void setInfoMap(Map<String, String> infoMap) {
///* 45 */     this.infoMap = infoMap; }
///*    */
///*    */   public DBObject getExtraInfoMap() {
///* 48 */     return this.extraInfoMap; }
///*    */
///*    */   public void setExtraInfoMap(DBObject extraInfoMap) {
///* 51 */     this.extraInfoMap = extraInfoMap;
///*    */   }
///*    */ }
//
///* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
// * Qualified Name:     cn.edu.ecust.sse.bean.TripleItem
// * JD-Core Version:    0.5.3
// */