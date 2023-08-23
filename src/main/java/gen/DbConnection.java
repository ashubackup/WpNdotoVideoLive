/*    */ package gen;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ 
/*    */ public class DbConnection {
/*    */   public static Connection getDatabse() {
/*  8 */     Connection conn = null;
/*    */     try {
/* 10 */       Class.forName("com.mysql.jdbc.Driver").newInstance();
/*    */       
/* 12 */       conn = DriverManager.getConnection(
/* 13 */           "jdbc:mysql://5.189.169.12:3306/ndoto?autoReconnect=true&useSSL=false&enabledTLSProtocols=TLSv1.2&serverTimezone=UTC", 
/* 14 */           "root", "gloadmin123");
/* 15 */       System.out.println("ndoto DB connected");
/*    */     }
/* 17 */     catch (Exception e) {
/* 18 */       e.printStackTrace();
/*    */     } 
/* 20 */     return conn;
/*    */   }
/*    */   
/*    */   public static Connection getMTNDatabse() {
/* 24 */     Connection conn = null;
/*    */     try {
/* 26 */       Class.forName("com.mysql.jdbc.Driver").newInstance();
/*    */       
/* 28 */       conn = DriverManager.getConnection(
/* 29 */           "jdbc:mysql://91.205.172.123:3306/ndotosts?autoReconnect=true&useSSL=false&enabledTLSProtocols=TLSv1.2&serverTimezone=UTC", 
/* 30 */           "root", "gloadmin123");
/*    */       
/* 32 */       System.out.println("ndotomtn DB connected");
/*    */     }
/* 34 */     catch (Exception e) {
/* 35 */       e.printStackTrace();
/*    */     } 
/* 37 */     return conn;
/*    */   }
/*    */   
/*    */   public static Connection getWPDatabase() {
/* 41 */     Connection con = null;
/*    */     
/*    */     try {
/* 44 */       Class.forName("com.mysql.jdbc.Driver").newInstance();
/* 45 */       con = DriverManager.getConnection(
/* 46 */           "jdbc:mysql://91.205.172.123:3306/ndotowp?autoReconnect=true&useSSL=false&enabledTLSProtocols=TLSv1.2&serverTimezone=UTC", "root", "gloadmin123");
/* 47 */       System.out.println("wp database connected");
/* 48 */     } catch (Exception e) {
/*    */       
/* 50 */       e.printStackTrace();
/*    */     } 
/* 52 */     return con;
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\DbConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */