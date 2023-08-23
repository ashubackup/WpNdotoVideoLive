/*     */ package gen;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.text.SimpleDateFormat;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.annotation.WebServlet;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ @WebServlet({"/Subscription"})
/*     */ public class SubscriptionStatus
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String CallbackPath;
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  33 */     doPost(request, response);
/*     */   }
/*     */   
/*     */   public String getDateFormat(String date) {
/*  37 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  38 */     String dateInString = date;
/*  39 */     String[] arr = dateInString.split("\\+");
/*  40 */     String[] newarr = arr[0].split("T");
/*  41 */     System.out.println(newarr[0]);
/*  42 */     String data = String.valueOf(String.valueOf(newarr[0])) + " " + newarr[1];
/*  43 */     System.out.println(data);
/*  44 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  49 */     PrintWriter out = response.getWriter();
/*  50 */     String resp = "{\"status\":\"0\",\"data\":{},\"message\":\"Failed\"}";
/*     */     
/*     */     try {
/*  53 */       response.setContentType("text/xml");
/*  54 */       StringBuffer jb = new StringBuffer();
/*  55 */       String line = null;
/*     */       try {
/*  57 */         BufferedReader reader = request.getReader();
/*  58 */         while ((line = reader.readLine()) != null)
/*  59 */           jb.append(line); 
/*  60 */       } catch (Exception e) {
/*  61 */         e.printStackTrace();
/*     */       } 
/*     */       
/*  64 */       JSONObject jsonObj = new JSONObject(jb.toString());
/*  65 */       String ani = (String)jsonObj.get("user_msisdn");
/*  66 */       String countyCode = "27";
/*  67 */       int len = "27".length();
/*  68 */       if (ani.substring(0, len).equals("27")) {
/*  69 */         ani = ani.substring(len);
/*     */       }
/*  71 */       String last_billed_at = (String)jsonObj.get("last_billed_at");
/*  72 */       String next_billing_at = (String)jsonObj.get("next_billing_at");
/*  73 */       String channel_name = (String)jsonObj.get("channel_name");
/*  74 */       String status_name = (String)jsonObj.get("status_name");
/*  75 */       String svc_name = (String)jsonObj.get("svc_name");
/*  76 */       int amount = jsonObj.getInt("billing_rate");
/*  77 */       int campaign_id = jsonObj.getInt("campaign_id");
/*  78 */       String amt = Integer.toString(amount);
/*  79 */       String camp_id = Integer.toString(campaign_id);
/*  80 */       String subscription_at = (String)jsonObj.get("subscription_started_at");
/*     */       
/*  82 */       next_billing_at = getDateFormat(next_billing_at);
/*  83 */       last_billed_at = getDateFormat(last_billed_at);
/*  84 */       subscription_at = getDateFormat(subscription_at);
/*     */       
/*  86 */       String instQry = "insert into tbl_dlr (ani,channel_name,status_name,svc_name,amount,campaign_id,next_billed_date,last_billed_date,type,sub_date_time,result_name) values ('<ani>','<channel_name>','<status_name>','<svc_name>','<amount>','<campaign_id>','<next_billed_date>','<last_billed_date>','<type>','<sub_date_time>','<result_name>')";
/*  87 */       instQry = instQry.replace("<ani>", ani).replace("<last_billed_date>", last_billed_at)
/*  88 */         .replace("<next_billed_date>", next_billing_at).replace("<channel_name>", channel_name)
/*  89 */         .replace("<status_name>", status_name).replace("<svc_name>", svc_name).replace("<amount>", amt)
/*  90 */         .replace("<campaign_id>", camp_id).replace("<type>", "sub")
/*  91 */         .replace("<sub_date_time>", subscription_at).replace("<result_name>", "");
/*  92 */       System.out.println(instQry);
/*  93 */       PreparedStatement stmtup = Loader.NdotMTNConn.prepareStatement(instQry);
/*  94 */       stmtup.executeUpdate(instQry);
/*     */       
/*  96 */       resp = "{\"status\":\"1\",\"data\":{},\"message\":\"Success\"}";
/*  97 */     } catch (Exception e2) {
/*  98 */       e2.printStackTrace();
/*     */     } 
/* 100 */     response.setStatus(204);
/* 101 */     out.println(resp);
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\SubscriptionStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */