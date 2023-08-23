/*    */ package servlet;
/*    */ 
/*    */ import com.Helper.DBMethods;
/*    */ import java.io.IOException;
/*    */ import java.net.InetAddress;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.annotation.WebServlet;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @WebServlet({"/entry"})
/*    */ public class Entry
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 36 */     doPost(request, response);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 44 */     long ext = (long)Math.floor(Math.random() * 9.0E9D);
/*    */     
/* 46 */     String ext_id = String.valueOf(ext);
/*    */     
/* 48 */     String serviceUrl = DBMethods.getServiceUrl();
/* 49 */     serviceUrl = String.valueOf(serviceUrl) + ext_id;
/* 50 */     System.out.println(serviceUrl);
/*    */     
/* 52 */     String servicename = "NT_Stream_R5";
/*    */     
/* 54 */     InetAddress ip = null;
/*    */ 
/*    */     
/*    */     try {
/* 58 */       ip = InetAddress.getByName((new URL(serviceUrl)).getHost());
/*    */ 
/*    */       
/* 61 */       System.out.println("Public IP Address of: " + ip);
/*    */     }
/* 63 */     catch (MalformedURLException e) {
/*    */       
/* 65 */       System.out.println("Invalid URL");
/*    */     } 
/*    */     
/* 68 */     String userAgent = request.getHeader("user-agent");
/*    */     
/* 70 */     boolean r = DBMethods.save_ext_id(ext_id, servicename, serviceUrl, ip.toString(), userAgent);
/* 71 */     if (r)
/*    */     {
/* 73 */       System.out.println("ext_id saved in db");
/*    */     }
/*    */     
/* 76 */     response.sendRedirect(serviceUrl);
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\servlet\Entry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */