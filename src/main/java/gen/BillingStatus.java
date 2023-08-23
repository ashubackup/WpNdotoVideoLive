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
/*     */ 
/*     */ @WebServlet({"/Billing"})
/*     */ public class BillingStatus
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String CallbackPath;
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  34 */     doPost(request, response);
/*     */   }
/*     */   
/*     */   public String getDateFormat(String date) {
/*  38 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  39 */     String dateInString = date;
/*  40 */     String[] arr = dateInString.split("\\+");
/*  41 */     String[] newarr = arr[0].split("T");
/*  42 */     System.out.println(newarr[0]);
/*  43 */     String data = String.valueOf(String.valueOf(newarr[0])) + " " + newarr[1];
/*  44 */     System.out.println(data);
/*  45 */     return data;
/*     */   }
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  49 */     response.setContentType("text/xml");
/*  50 */     PrintWriter out = response.getWriter();
/*  51 */     StringBuffer jb = new StringBuffer();
/*  52 */     String line = null;
/*  53 */     String resp = "{\"status\":\"0\",\"data\":{},\"message\":\"Failed\"}";
/*     */     
/*     */     try {
/*  56 */       BufferedReader reader = request.getReader();
/*  57 */       while ((line = reader.readLine()) != null) {
/*  58 */         jb.append(line);
/*     */       }
/*  60 */       JSONObject jsonObj = new JSONObject(jb.toString());
/*  61 */       String result_name = (String)jsonObj.get("result_name");
/*  62 */       String insidObj = jsonObj.get("subscription").toString();
/*  63 */       JSONObject jsonObjnew = new JSONObject(insidObj);
/*  64 */       String ani = (String)jsonObjnew.get("user_msisdn");
/*     */       
/*  66 */       String countyCode = "27";
/*  67 */       int len = "27".length();
/*  68 */       if (ani.substring(0, len).equals("27")) {
/*  69 */         ani = ani.substring(len);
/*     */       }
/*  71 */       String last_billed_at = (String)jsonObjnew.get("last_billed_at");
/*  72 */       String next_billing_at = (String)jsonObjnew.get("next_billing_at");
/*  73 */       String channel_name = (String)jsonObjnew.get("channel_name");
/*  74 */       String status_name = (String)jsonObjnew.get("status_name");
/*  75 */       String svc_name = (String)jsonObjnew.get("svc_name");
/*  76 */       String subscription_at = (String)jsonObjnew.get("subscription_started_at");
/*  77 */       int amount = jsonObjnew.getInt("billing_rate");
/*  78 */       int campaign_id = jsonObjnew.getInt("campaign_id");
/*  79 */       String amt = Integer.toString(amount);
/*  80 */       String camp_id = Integer.toString(campaign_id);
/*     */       
/*  82 */       next_billing_at = getDateFormat(next_billing_at);
/*  83 */       last_billed_at = getDateFormat(last_billed_at);
/*  84 */       subscription_at = getDateFormat(subscription_at);
/*     */       
/*  86 */       String instQry = "insert into tbl_dlr (ani,channel_name,status_name,svc_name,amount,campaign_id,next_billed_date,last_billed_date,type,sub_date_time,result_name) values ('<ani>','<channel_name>','<status_name>','<svc_name>','<amount>','<campaign_id>','<next_billed_date>','<last_billed_date>','<type>','<sub_date_time>','<result_name>')";
/*  87 */       instQry = instQry.replace("<ani>", ani).replace("<last_billed_date>", last_billed_at).replace("<next_billed_date>", next_billing_at)
/*  88 */         .replace("<channel_name>", channel_name).replace("<status_name>", status_name).replace("<svc_name>", svc_name).replace("<amount>", amt)
/*  89 */         .replace("<campaign_id>", camp_id).replace("<type>", "billing").replace("<sub_date_time>", subscription_at)
/*  90 */         .replace("<result_name>", result_name);
/*  91 */       System.out.println(instQry);
/*  92 */       PreparedStatement stmtup = Loader.NdotMTNConn.prepareStatement(instQry);
/*  93 */       stmtup.executeUpdate(instQry);
/*  94 */       resp = "{\"status\":\"1\",\"data\":{},\"message\":\"Success\"}";
/*     */     }
/*  96 */     catch (Exception e) {
/*  97 */       e.printStackTrace();
/*     */     } 
/*  99 */     response.setStatus(204);
/* 100 */     out.println(resp);
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\BillingStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */