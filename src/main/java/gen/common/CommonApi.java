/*     */ package gen.common;
/*     */ 
/*     */ import gen.DataCollector;
/*     */ import gen.Loader;
/*     */ import gen.Parameter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.annotation.WebServlet;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.json.JSONObject;
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
/*     */ @WebServlet({"/CommonApi"})
/*     */ public class CommonApi
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  39 */     doPost(request, response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  48 */     String result = "{\"status\":\"0\",\"error\":\"action not defined\"}";
/*  49 */     PrintWriter out = response.getWriter();
/*  50 */     Parameter objParameter = new Parameter();
/*  51 */     DataCollector coll = new DataCollector();
/*  52 */     objParameter.setAction(request.getParameter("action"));
/*  53 */     System.out.println(request.getParameter("action"));
/*  54 */     CommonData cp = new CommonData();
/*  55 */     HttpSession session = request.getSession();
/*     */ 
/*     */     
/*     */     try {
/*  59 */       if (objParameter.getAction().equalsIgnoreCase("12")) {
/*  60 */         objParameter.setAni(request.getParameter("ani"));
/*     */         
/*  62 */         objParameter.setVideoid(request.getParameter("videoid"));
/*  63 */         objParameter.setDuration(request.getParameter("duration"));
/*  64 */         objParameter.setChannel(request.getParameter("channel"));
/*  65 */         objParameter.setPercentage(request.getParameter("percentage"));
/*  66 */         objParameter.setCategory(request.getParameter("catid"));
/*  67 */         result = cp.addTimeeLogging(Loader.contentConn, objParameter);
/*  68 */       } else if (objParameter.getAction().equalsIgnoreCase("13")) {
/*  69 */         objParameter.setAni(request.getParameter("ani"));
/*     */         
/*  71 */         objParameter.setVideoid(request.getParameter("videoid"));
/*  72 */         objParameter.setChannel(request.getParameter("channel"));
/*  73 */         result = cp.getDuration(Loader.contentConn, objParameter);
/*  74 */       } else if (objParameter.getAction().equalsIgnoreCase("14")) {
/*  75 */         objParameter.setAni(request.getParameter("ani"));
/*     */         
/*  77 */         objParameter.setVideoid(request.getParameter("videoid"));
/*  78 */         objParameter.setChannel(request.getParameter("type"));
/*  79 */         result = cp.addWhishlist(Loader.contentConn, objParameter);
/*  80 */       } else if (objParameter.getAction().equalsIgnoreCase("15")) {
/*  81 */         objParameter.setVideoid(request.getParameter("videoid"));
/*  82 */         objParameter.setAni(request.getParameter("ani"));
/*  83 */         objParameter.setChannel(request.getParameter("type"));
/*  84 */         result = cp.addViews(Loader.contentConn, objParameter);
/*  85 */       } else if (objParameter.getAction().equalsIgnoreCase("16")) {
/*  86 */         objParameter.setVideoid(request.getParameter("videoid"));
/*  87 */         objParameter.setAni(request.getParameter("ani"));
/*  88 */         objParameter.setCategory(request.getParameter("category"));
/*  89 */         objParameter.setChannel(request.getParameter("type"));
/*  90 */         result = cp.UpdateLike(Loader.contentConn, objParameter);
/*  91 */       } else if (objParameter.getAction().equalsIgnoreCase("17")) {
/*  92 */         objParameter.setData(request.getParameter("data"));
/*  93 */         result = cp.searchVideo(Loader.contentConn, objParameter);
/*  94 */       } else if (objParameter.getAction().equalsIgnoreCase("18")) {
/*  95 */         objParameter.setAni(request.getParameter("ani"));
/*  96 */         objParameter.setName(request.getParameter("name"));
/*  97 */         objParameter.setCategory(request.getParameter("category"));
/*  98 */         objParameter.setVideoid(request.getParameter("videoid"));
/*  99 */         objParameter.setChannel(request.getParameter("type"));
/* 100 */         result = cp.addRecentSearch(Loader.contentConn, objParameter);
/*     */       }
/* 102 */       else if (objParameter.getAction().equalsIgnoreCase("20")) {
/*     */         
/* 104 */         objParameter.setAni(request.getParameter("ani"));
/*     */         
/* 106 */         objParameter.setVideoid(request.getParameter("videoid"));
/* 107 */         objParameter.setDuration(request.getParameter("duration"));
/*     */         
/* 109 */         objParameter.setPercentage(request.getParameter("percentage"));
/* 110 */         objParameter.setCategory(request.getParameter("catid"));
/* 111 */         coll.addPlayLogging(Loader.contentConn, objParameter);
/* 112 */       } else if (objParameter.getAction().equalsIgnoreCase("21")) {
/*     */         
/* 114 */         objParameter.setAni(request.getParameter("ani"));
/* 115 */         result = coll.getUserDetail(objParameter, Loader.contentConn);
/*     */       }
/* 117 */       else if (objParameter.getAction().equalsIgnoreCase("22")) {
/*     */         
/* 119 */         objParameter.setAni(request.getParameter("ani"));
/* 120 */         objParameter.setResult(request.getParameter("result"));
/* 121 */         result = coll.addUserDetail(objParameter, Loader.contentConn);
/* 122 */         session.setAttribute("ageid", result);
/* 123 */         result = (new JSONObject()).put("age", result).toString();
/*     */       
/*     */       }
/* 126 */       else if (objParameter.getAction().equalsIgnoreCase("23")) {
/*     */         
/* 128 */         objParameter.setPortal(request.getParameter("portal"));
/* 129 */         objParameter.setAni(request.getParameter("ani"));
/* 130 */         objParameter.setMod(request.getParameter("mod"));
/*     */         
/* 132 */         result = coll.addShareLog(objParameter, Loader.contentConn);
/* 133 */         System.out.println(result);
/*     */ 
/*     */       
/*     */       }
/* 137 */       else if (objParameter.getAction().equalsIgnoreCase("24")) {
/*     */         
/* 139 */         objParameter.setMessage(request.getParameter("msg"));
/* 140 */         objParameter.setAni(request.getParameter("ani"));
/* 141 */         objParameter.setVideoid(request.getParameter("vid"));
/* 142 */         result = coll.addComment(objParameter, Loader.contentConn);
/* 143 */         System.out.println(result);
/*     */       
/*     */       }
/* 146 */       else if (objParameter.getAction().equalsIgnoreCase("25")) {
/*     */         
/* 148 */         objParameter.setVideoid(request.getParameter("vid"));
/* 149 */         result = coll.getComment(objParameter, Loader.contentConn);
/* 150 */         System.out.println(result);
/*     */       }
/*     */     
/*     */     }
/* 154 */     catch (Exception e) {
/* 155 */       e.printStackTrace();
/*     */     } 
/* 157 */     out.print(result);
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\common\CommonApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */