/*    */ package com.hiekn.plantdata.exception;
/*    */ 
/*    */ public class ServiceException extends BaseException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public ServiceException(Integer code)
/*    */   {
/* 10 */     super(code);
/*    */   }
/*    */ 
/*    */   public static ServiceException newInstance() {
/* 14 */     return newInstance(Integer.valueOf(400));
/*    */   }
/*    */ 
/*    */   public static ServiceException newInstance(Integer code) {
/* 18 */     return new ServiceException(code);
/*    */   }
/*    */ }

/* Location:           E:\知识图谱-项目平台\知识图谱\姚远拷贝到软院程序\work\tomcat\webapps\plantdata_console\WEB-INF\classes\
 * Qualified Name:     com.hiekn.plantdata.exception.ServiceException
 * JD-Core Version:    0.5.3
 */