/*     */ package gen;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.annotation.WebServlet;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet({"/ApiServlet"})
/*     */ public class ApiServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  35 */     doPost(request, response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  45 */     String result = "{\"status\":\"0\",\"error\":\"action not defined\"}";
/*  46 */     PrintWriter out = response.getWriter();
/*  47 */     Parameter objParameter = new Parameter();
/*  48 */     DataCollector coll = new DataCollector();
/*  49 */     objParameter.setAction(request.getParameter("action"));
/*     */     try {
/*  51 */       if (!objParameter.getAction().equalsIgnoreCase("1"))
/*     */       {
/*     */ 
/*     */         
/*  55 */         if (objParameter.getAction().equalsIgnoreCase("4")) {
/*  56 */           objParameter.setVideoid(request.getParameter("videoid"));
/*  57 */           objParameter.setAni(request.getParameter("ani"));
/*  58 */           objParameter.setCategory(request.getParameter("type"));
/*  59 */           objParameter.setChannel(request.getParameter("channel"));
/*  60 */           objParameter.setPortal(request.getParameter("portal"));
/*  61 */           result = coll.addLogging(Loader.contentConn, objParameter);
/*  62 */         } else if (objParameter.getAction().equalsIgnoreCase("5")) {
/*     */           
/*  64 */           objParameter.setAni(request.getParameter("ani"));
/*  65 */           objParameter.setPortal(request.getParameter("portal"));
/*  66 */           result = coll.addTrackLogging(Loader.contentConn, objParameter);
/*     */         }
/*  68 */         else if (objParameter.getAction().equalsIgnoreCase("6")) {
/*     */           
/*  70 */           objParameter.setAni(request.getParameter("ani"));
/*  71 */           objParameter.setStatus(request.getParameter("status"));
/*  72 */           objParameter.setTime(request.getParameter("time"));
/*  73 */           objParameter.setPortal(request.getParameter("portal"));
/*  74 */           result = coll.addActivity(Loader.contentConn, objParameter);
/*     */         }
/*  76 */         else if (objParameter.getAction().equalsIgnoreCase("7")) {
/*     */           
/*  78 */           objParameter.setAni(request.getParameter("ani"));
/*  79 */           objParameter.setStatus(request.getParameter("status"));
/*  80 */           result = coll.addTrackLogging(Loader.contentConn, objParameter);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  99 */     catch (Exception e) {
/* 100 */       e.printStackTrace();
/*     */     } 
/* 102 */     out.print(result);
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\ApiServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */