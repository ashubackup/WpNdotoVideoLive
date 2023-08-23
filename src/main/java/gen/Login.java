/*    */ package gen;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.annotation.WebServlet;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
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
/*    */ @WebServlet({"/Login"})
/*    */ public class Login
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 33 */     doPost(request, response);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 41 */     response.setContentType("text/html");
/* 42 */     HttpSession session = request.getSession(true);
/*    */     
/* 44 */     PrintWriter out = response.getWriter();
/* 45 */     String ani = request.getParameter("ani");
/* 46 */     String category = request.getParameter("cat_id");
/*    */     try {
/* 48 */       session.setAttribute("user", ani);
/* 49 */       session.setAttribute("ani", ani);
/* 50 */       session.setAttribute("mainCat", category);
/* 51 */       response.sendRedirect("index.jsp");
/* 52 */     } catch (Exception e) {
/* 53 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\Login.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */