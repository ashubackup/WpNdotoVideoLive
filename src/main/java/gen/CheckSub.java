/*    */ package gen;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.Statement;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.annotation.WebServlet;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @WebServlet({"/CheckSub"})
/*    */ public class CheckSub
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 27 */     doPost(request, response);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 32 */     HttpSession session = request.getSession(true);
/* 33 */     session.setMaxInactiveInterval(600);
/* 34 */     int status = 3;
/* 35 */     JSONObject obj = new JSONObject();
/* 36 */     response.setContentType("text/html");
/* 37 */     PrintWriter out = response.getWriter();
/* 38 */     String ani = request.getParameter("ani");
/* 39 */     String cat = request.getParameter("category");
/* 40 */     String countyCode = "234";
/* 41 */     System.out.println("Called");
/* 42 */     int len = "234".length();
/* 43 */     if (ani.startsWith("+")) {
/* 44 */       ani = ani.substring(1);
/*    */     }
/* 46 */     if (ani.startsWith("0")) {
/* 47 */       ani = ani.substring(1);
/*    */     }
/* 49 */     if (ani.startsWith("00234")) {
/* 50 */       ani = ani.substring(5);
/*    */     }
/* 52 */     if (ani.substring(0, len).equals("234")) {
/* 53 */       ani = ani.substring(len);
/*    */     }
/* 55 */     ani = ani.trim();
/*    */     try {
/* 57 */       Statement stmt1 = Loader.contentConn.createStatement();
/* 58 */       Statement stmt2 = Loader.contentConn.createStatement();
/* 59 */       Statement stmt3 = Loader.contentConn.createStatement();
/* 60 */       String chkqry = "select count(1) from tbl_subscription where ani='" + ani + "' and service_type='" + cat + "' ";
/* 61 */       System.out.println(chkqry);
/* 62 */       ResultSet rs = stmt1.executeQuery(chkqry);
/* 63 */       rs.next();
/* 64 */       int count = rs.getInt(1);
/* 65 */       System.out.println("count : " + count);
/* 66 */       if (count > 0) {
/* 67 */         String qry = "select * from tbl_subscription where ani='" + ani + "' and service_type='" + cat + "' and NEXT_BILLED_DATE is not null ";
/* 68 */         System.out.println(qry);
/* 69 */         ResultSet rsn = stmt2.executeQuery(qry);
/* 70 */         if (rsn.next()) {
/* 71 */           String qry2 = "select count(1) from tbl_subscription where ani='" + ani + "' and service_type='" + cat + "' and date(NEXT_BILLED_DATE) >= date(now())";
/* 72 */           System.out.println(qry2);
/* 73 */           ResultSet rsn2 = stmt2.executeQuery(qry2);
/* 74 */           if (rsn2.next()) {
/* 75 */             int cnt = rsn2.getInt(1);
/* 76 */             if (cnt > 0) {
/* 77 */               status = 1;
/*    */             } else {
/*    */               
/* 80 */               status = 2;
/*    */             } 
/*    */           } 
/*    */         } else {
/*    */           
/* 85 */           status = 3;
/*    */         } 
/*    */       } else {
/*    */         
/* 89 */         String instSub = "insert into tbl_subscription (ani,sub_date_time,unsub_date_time,m_act,lang,service_type,status,charging_date,billing_date,default_amount,RECORDSTATUS,pack_type) values ('" + ani + "',now(),NULL,'WEB','e','" + cat + "',NULL,NULL,NULL,'2','1','Daily')";
/* 90 */         System.out.println(instSub);
/* 91 */         stmt3.executeUpdate(instSub);
/* 92 */         status = 3;
/*    */       } 
/* 94 */       obj.put("status", status);
/* 95 */       out.print(obj.toString());
/*    */     }
/* 97 */     catch (Exception Exception) {
/* 98 */       Exception.printStackTrace();
/* 99 */       out.print("{\"status\":\"0\"}");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\CheckSub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */