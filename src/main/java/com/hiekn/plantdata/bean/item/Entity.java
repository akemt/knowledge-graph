/*     */ package com.hiekn.plantdata.bean.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Entity
/*     */   implements Comparable<Entity>
/*     */ {
/*     */   private long id;
/*     */   private String name;
/*     */   private int type;
/*     */   private String abs;
/*     */   private Set<String> syns;
/*     */   private List<PairItem> pars;
/*     */   private List<PairItem> sibs;
/*     */   private List<PairItem> sons;
/*     */   private List<AttributeItem> atts;
/*     */   private List<PairItem> inss;
/*     */ 
/*     */   public long getId()
/*     */   {
/*  58 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(long id) {
/*  62 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  66 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  70 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public int getType() {
/*  74 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(int type) {
/*  78 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getAbs() {
/*  82 */     return this.abs;
/*     */   }
/*     */ 
/*     */   public void setAbs(String abs) {
/*  86 */     this.abs = abs;
/*     */   }
/*     */ 
/*     */   public Set<String> getSyns() {
/*  90 */     return this.syns;
/*     */   }
/*     */ 
/*     */   public void setSyns(Set<String> syns) {
/*  94 */     this.syns = syns;
/*     */   }
/*     */ 
/*     */   public List<AttributeItem> getAtts()
/*     */   {
/*  99 */     return this.atts;
/*     */   }
/*     */ 
/*     */   public void setAtts(List<AttributeItem> atts) {
/* 103 */     this.atts = atts;
/*     */   }
/*     */ 
/*     */   public List<PairItem> getPars() {
/* 107 */     return this.pars;
/*     */   }
/*     */ 
/*     */   public void setPars(List<PairItem> pars) {
/* 111 */     this.pars = pars;
/*     */   }
/*     */ 
/*     */   public List<PairItem> getSibs() {
/* 115 */     return this.sibs;
/*     */   }
/*     */ 
/*     */   public void setSibs(List<PairItem> sibs) {
/* 119 */     this.sibs = sibs;
/*     */   }
/*     */ 
/*     */   public List<PairItem> getSons() {
/* 123 */     return this.sons;
/*     */   }
/*     */ 
/*     */   public void setSons(List<PairItem> sons) {
/* 127 */     this.sons = sons;
/*     */   }
/*     */ 
/*     */   public List<PairItem> getInss() {
/* 131 */     return this.inss;
/*     */   }
/*     */ 
/*     */   public void setInss(List<PairItem> inss) {
/* 135 */     this.inss = inss;
/*     */   }
/*     */ 
/*     */   public int compareTo(Entity o) {
/* 139 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 144 */     Entity e = (Entity)obj;
/* 145 */     return (this.id == e.id);
/*     */   }
/*     */ }

/* Location:           E:\IDEAProject\plantdata_console\src\main\webapp\WEB-INF\lib\com.hiekn.kg.bean-2.1.4-SNAPSHOT.jar
 * Qualified Name:     cn.edu.ecust.sse.bean.Entity
 * JD-Core Version:    0.5.3
 */