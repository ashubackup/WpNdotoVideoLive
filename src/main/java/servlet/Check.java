/*    */ package servlet;
/*    */ 
/*    */ import com.Helper.DBMethods;
/*    */ import com.google.gson.Gson;
/*    */ import java.io.IOException;
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
/*    */ @WebServlet({"/check"})
/*    */ public class Check
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
/*    */   
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 42 */     String ani = request.getParameter("ani");
/* 43 */     System.out.println(ani);
/*    */     
/* 45 */     Gson gson = new Gson();
/* 46 */     String result = "";
/*    */     
/* 48 */     String service = "NT_Stream_R5";
/*    */     
/* 50 */     String checkAni = DBMethods.checkAni(ani, service);
/*    */     
/* 52 */     if (checkAni.equals("yes")) {
/*    */       
/* 54 */       result = gson.toJson("yes");
/* 55 */       response.getWriter().append(result);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 62 */     else if (checkAni.equals("no")) {
/*    */       
/* 64 */       result = gson.toJson("no");
/* 65 */       response.getWriter().append(result);
/*    */     }
/*    */     else {
/*    */       
/* 69 */       result = gson.toJson("Something Went Wrong");
/* 70 */       response.getWriter().append(result);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\servlet\Check.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */