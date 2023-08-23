/*     */ package com.Servlet;
/*     */ 
/*     */ import com.Helper.DBMethods;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
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
/*     */ 
/*     */ 
/*     */ @WebServlet({"/callback"})
/*     */ public class Callback
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
/*     */     try {
/*  49 */       String queryString = request.getQueryString();
/*  50 */       String telNo = request.getParameter("telNo");
/*     */       
/*  52 */       String ani = telNo.substring(2);
/*     */       
/*  54 */       System.out.println("Call back from sdp :: " + queryString);
/*     */       
/*  56 */       if (queryString != null) {
/*     */         
/*  58 */         boolean saveLogs = DBMethods.saveQueryStringLogs(queryString, ani);
/*     */         
/*  60 */         if (saveLogs)
/*     */         {
/*  62 */           System.out.println("Query String Logs saved in db");
/*     */         }
/*     */       } 
/*     */       
/*  66 */       String mt = request.getParameter("mt");
/*  67 */       String client = request.getParameter("client");
/*  68 */       String requestId = request.getParameter("requestId");
/*  69 */       String refId = request.getParameter("refId");
/*  70 */       String operator = request.getParameter("operator");
/*     */       
/*  72 */       String statusTime = request.getParameter("statusTime");
/*     */       
/*  74 */       String amount = request.getParameter("amount");
/*  75 */       String statusId = request.getParameter("statusId");
/*  76 */       String wpAuthReqRef = request.getParameter("wpAuthReqRef");
/*  77 */       String statusMessage = request.getParameter("statusMessage");
/*     */       
/*  79 */       String source = request.getParameter("source");
/*  80 */       String rxTelNo = request.getParameter("rxTelNo");
/*  81 */       String rxMessage = request.getParameter("rxMessage");
/*     */       
/*  83 */       System.out.println("mt is " + mt);
/*  84 */       System.out.println("client is " + client);
/*  85 */       System.out.println("requestId is " + requestId);
/*  86 */       System.out.println("refId is " + refId);
/*  87 */       System.out.println("Operator is " + operator);
/*  88 */       System.out.println("telno is " + ani);
/*     */       
/*  90 */       if (statusTime == null || statusTime.equalsIgnoreCase("")) {
/*     */ 
/*     */ 
/*     */         
/*  94 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  95 */         Date date = new Date();
/*  96 */         String format = sdf.format(date);
/*     */         
/*  98 */         statusTime = format;
/*     */       } 
/* 100 */       System.out.println("statusTime is " + statusTime);
/*     */       
/* 102 */       String typee = DBMethods.checkExisting(ani);
/* 103 */       System.out.println(typee);
/*     */       
/* 105 */       if (mt != null && mt.equalsIgnoreCase("sdResult")) {
/*     */         
/* 107 */         boolean saveDlr = DBMethods.saveDlr(mt, client, requestId, refId, operator, ani, amount, statusId, wpAuthReqRef, statusTime, statusMessage, typee);
/*     */ 
/*     */         
/* 110 */         String service_name = "NT_Stream_R5";
/* 111 */         String service_id = "3786";
/*     */         
/* 113 */         String ext_id = DBMethods.match_ext_id(ani, service_name, service_id);
/*     */         
/* 115 */         if (ext_id.equals("blank")) {
/*     */           
/* 117 */           System.out.println("ext_id not found in db or more than 1 time redirect");
/*     */         }
/* 119 */         else if (!ext_id.equals("blank")) {
/*     */           
/* 121 */           System.out.println("ext_id found in db");
/*     */           
/* 123 */           JSONObject object = new JSONObject();
/* 124 */           object.put("ani", ani);
/* 125 */           object.put("service_id", service_id);
/* 126 */           object.put("ext_ref", ext_id);
/*     */           
/*     */           try {
/* 129 */             int sentResponse = call(object);
/*     */             
/* 131 */             DBMethods.updateStatus_ext_id_check(ani, service_id);
/* 132 */             DBMethods.saveResponse(ani, ext_id, service_id, Integer.valueOf(sentResponse));
/* 133 */           } catch (Exception e) {
/*     */             
/* 135 */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*     */         
/* 139 */         if (saveDlr) {
/*     */           
/* 141 */           System.out.println("Data save for sub in tbl_dlr");
/* 142 */           response.getWriter().append("Callback is save for new subscribe");
/*     */         }
/*     */         else {
/*     */           
/* 146 */           System.out.println("Not Save in tbl_dlr");
/* 147 */           response.getWriter().append("Something Went Wrong");
/*     */         } 
/*     */         
/* 150 */         System.out.println("amount is " + amount);
/* 151 */         System.out.println("statusId is " + statusId);
/* 152 */         System.out.println("wpAuthReqRef is " + wpAuthReqRef);
/* 153 */         System.out.println("statusMessage is  " + statusMessage);
/*     */ 
/*     */       
/*     */       }
/* 157 */       else if (mt != null && mt.equalsIgnoreCase("tnUnsubscribe")) {
/*     */         
/* 159 */         boolean saveDrlUnsub = DBMethods.saveDrlUnsub(mt, client, requestId, refId, operator, ani, source, statusTime, rxTelNo, rxMessage);
/* 160 */         if (saveDrlUnsub) {
/*     */           
/* 162 */           System.out.println("Data save for unsub in tbl_dlr");
/* 163 */           response.getWriter().append("Callback is save for unsubscribe");
/*     */         }
/*     */         else {
/*     */           
/* 167 */           System.out.println("Data not save in tbl_dlr for unsub");
/* 168 */           response.getWriter().append("Something Went Wrong");
/*     */         } 
/*     */         
/* 171 */         System.out.println("source is " + source);
/* 172 */         System.out.println("rxTelNo is " + rxTelNo);
/* 173 */         System.out.println("rxMessage is " + rxMessage);
/*     */       }
/*     */       else {
/*     */         
/* 177 */         System.out.println("Neither sub nor unsub");
/* 178 */         response.getWriter().append("Received Callback, but neither for sub nor for unsub");
/*     */       } 
/*     */ 
/*     */       
/* 182 */       String data = null;
/* 183 */       StringBuffer sb = new StringBuffer();
/* 184 */       BufferedReader br = request.getReader();
/*     */       
/* 186 */       while ((data = br.readLine()) != null)
/*     */       {
/* 188 */         sb.append(data);
/*     */       }
/* 190 */       System.out.println("Call back from sdp :: " + sb);
/*     */       
/* 192 */       if (!sb.toString().equals(""))
/*     */       {
/* 194 */         JSONObject object = new JSONObject(sb.toString());
/*     */         
/* 196 */         boolean r = DBMethods.saveJsonLogs(object.toString());
/* 197 */         if (r)
/*     */         {
/* 199 */           System.out.println("Json Logs saved in db");
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 204 */     catch (Exception e) {
/*     */       
/* 206 */       e.printStackTrace();
/* 207 */       System.out.println("Something Went Wrong");
/* 208 */       response.getWriter().append("Something Went Wrong");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int call(JSONObject send) {
/* 217 */     String url = "http://91.205.172.123:43972/subscription-callback/collect-cent-callback/";
/* 218 */     HttpURLConnection urlConnection = null;
/* 219 */     System.out.println("Nitin Sir ::: " + url);
/*     */     try {
/* 221 */       URL u = new URL(url);
/* 222 */       urlConnection = (HttpURLConnection)u.openConnection();
/* 223 */       urlConnection.setDoOutput(true);
/* 224 */       urlConnection.setRequestMethod("POST");
/* 225 */       urlConnection.setRequestProperty("Content-Type", "application/json");
/* 226 */       urlConnection.connect();
/*     */       
/* 228 */       System.out.println("Json " + send);
/* 229 */       OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
/* 230 */       wr.write(send.toString());
/* 231 */       wr.flush();
/* 232 */       StringBuilder response = null;
/* 233 */       System.out.println("Reponse Code  :: " + urlConnection.getResponseCode());
/*     */       
/* 235 */       int responseCode = urlConnection.getResponseCode();
/*     */       
/* 237 */       if (urlConnection.getResponseCode() == 200) {
/* 238 */         BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
/* 239 */         String line = null;
/* 240 */         response = new StringBuilder();
/* 241 */         while ((line = br.readLine()) != null) {
/* 242 */           response.append(line);
/* 243 */           System.out.println(response);
/*     */         } 
/*     */       } 
/* 246 */       return responseCode;
/* 247 */     } catch (Exception e) {
/* 248 */       e.printStackTrace();
/* 249 */       return 0;
/*     */     } finally {
/* 251 */       urlConnection.disconnect();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\com\Servlet\Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */