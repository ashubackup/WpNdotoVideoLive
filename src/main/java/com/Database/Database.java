/*    */ package com.Database;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ 
/*    */ public class Database
/*    */ {
/*    */   public static Connection getDatabase() {
/*  9 */     Connection con = null;
/*    */     
/*    */     try {
/* 12 */       Class.forName("com.mysql.cj.jdbc.Driver");
/* 13 */       con = DriverManager.getConnection("jdbc:mysql://91.205.172.123:3306/ndotowp?autoReconnect=true&enabledTLSProtocols=TLSv1.2&serverTimezone=UTC", "root", "gloadmin123");
/*    */     }
/* 15 */     catch (Exception e) {
/*    */       
/* 17 */       e.printStackTrace();
/*    */     } 
/* 19 */     return con;
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\com\Database\Database.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */