/*    */ package gen;
/*    */ 
/*    */ import gen.common.ScrapingPage;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.annotation.WebServlet;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
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
/*    */ @WebServlet({"/scrapapi"})
/*    */ public class ScrapingServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 29 */   Scrapmodel param = new Scrapmodel();
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 34 */     PrintWriter out = response.getWriter();
/*    */     

			 
/* 36 */     String line = "";
/* 37 */     StringBuffer jd = new StringBuffer();
/* 38 */     JSONObject js = null;
/* 39 */     BufferedReader rd = request.getReader();
/* 40 */     while ((line = rd.readLine()) != null)
/*    */     {
/* 42 */       jd.append(line);
/*    */     }
/*    */ 
/*    */ 
/*    */ 	System.out.println("Request is :" +jd);
/*    */     
/* 48 */     js = new JSONObject(jd.toString());
/*    */     
/* 50 */     String action = js.getString("action");
/* 51 */     if (action.equalsIgnoreCase("1")) {
/*    */       
/* 53 */       int length = js.getJSONArray("imgurl").length();
/* 54 */       JSONArray jsonArray = js.getJSONArray("imgurl");
/* 55 */       Object ani = js.get("ani");
/* 56 */       String url = js.getString("pageurl");
/* 57 */       String html = js.getString("page");
/* 58 */       this.param.setJsonarray1(jsonArray);
/* 59 */       this.param.setAni1(ani.toString());
/* 60 */       this.param.setPageurl1(url);
/*    */       
/* 62 */       this.param = ScrapingPage.getSize1(this.param);
/* 63 */       DataCollector.insertUserDataUsage1(this.param);
/*    */       
/* 65 */       out.print((new JSONObject()).put("size", this.param.getSize1()));
/*    */     
/*    */     }
/* 68 */     else if (action.equalsIgnoreCase("2")) {
/*    */ 
/*    */       
/* 71 */       int length = js.getJSONArray("imgurl").length();
/* 72 */       JSONArray jsonArray = js.getJSONArray("imgurl");
/* 73 */       Object ani = js.get("ani");
/* 74 */       String url = js.getString("pageurl");
/* 75 */       String html = js.getString("page");
/*    */       
/* 77 */       this.param.setJsonarray(jsonArray);
/* 78 */       this.param.setAni(ani.toString());
/* 79 */       this.param.setPageurl(url);
/*    */ 
/*    */       
/* 82 */       this.param = ScrapingPage.getSize(this.param);
/* 83 */       DataCollector.insertUserDataUsage(this.param);
/*    */       
/* 85 */       out.print((new JSONObject()).put("size", this.param.getSize()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\ScrapingServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */