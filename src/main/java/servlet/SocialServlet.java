/*    */ package servlet;
/*    */ 
/*    */ import gen.DataCollector;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
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
/*    */ @WebServlet({"/social"})
/*    */ public class SocialServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 30 */     doPost(req, resp);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 35 */     PrintWriter out = response.getWriter();
/* 36 */     String mod = request.getParameter("mod");
/* 37 */     String provider = request.getParameter("provider");
/* 38 */     String pubid = request.getParameter("pubid");
/* 39 */     String clickid = request.getParameter("clickid");
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 44 */       long cid = DataCollector.insertUserSocialMod(mod, provider, pubid, clickid);
/* 45 */       out.println(cid);
/* 46 */       response.setStatus(200);
/* 47 */       response.sendRedirect("http://doi.mtndep.co.za/service/7911?cid=" + cid);
/*    */     }
/* 49 */     catch (Exception e) {
/*    */       
/* 51 */       e.printStackTrace();
/* 52 */       response.setStatus(500);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\servlet\SocialServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */