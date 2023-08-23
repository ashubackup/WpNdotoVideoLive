/*    */ package com.Helper;
/*    */ 
/*    */ import gen.Loader;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetDbMethods
/*    */ {
/*    */   public static ResultSet getRS(String query) {
/* 13 */     ResultSet r = null;
/*    */     
/*    */     try {
/* 16 */       PreparedStatement p = Loader.wpConnection.prepareStatement(query);
/* 17 */       r = p.executeQuery();
/* 18 */     } catch (Exception e) {
/*    */       
/* 20 */       e.printStackTrace();
/*    */     } 
/* 22 */     return r;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PreparedStatement getPS(String query) {
/* 27 */     PreparedStatement p = null;
/*    */     
/*    */     try {
/* 30 */       p = Loader.wpConnection.prepareStatement(query);
/*    */     }
/* 32 */     catch (Exception e) {
/*    */       
/* 34 */       e.printStackTrace();
/*    */     } 
/* 36 */     return p;
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\com\Helper\GetDbMethods.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */