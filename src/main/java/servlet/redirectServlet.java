/*     */ package servlet;
/*     */ 
/*     */ import com.Helper.DBMethods;
/*     */ import gen.DataCollector;
/*     */ import gen.Parameter;
/*     */ import gen.helper.TokenHandler;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoUnit;
/*     */ import java.util.Date;
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
/*     */ @WebServlet({"/redirect"})
/*     */ public class redirectServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  43 */   Parameter param = new Parameter();
/*  44 */   private DataCollector dc = new DataCollector();
/*  45 */   String url = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  56 */     doPost(request, response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  63 */     String key = "9g6f7g3f59h7i59gf6i7k980";
/*     */     
/*  65 */     String token = request.getParameter("token");
/*     */     
/*  67 */     System.out.println("token is " + token);
/*     */     
/*  69 */     String ext_id = request.getParameter("ext_id");
/*     */     
/*  71 */     System.out.println("ext_id is " + ext_id);
/*     */ 
/*     */ 
/*     */     
/*  75 */     String servicename = "NT_Stream_R5";
/*  76 */     boolean check = DBMethods.check_ext_id(ext_id, servicename);
/*  77 */     if (check)
/*     */     {
/*  79 */       System.out.println("ext_id present in db");
/*     */     }
/*     */     
/*  82 */     String f = token.replace("-", "+").replace("_", "/");
/*  83 */     System.out.println("After replace is " + f);
/*     */     
/*  85 */     f = String.valueOf(f) + "=";
/*     */     
/*  87 */     System.out.println("final token is " + f);
/*     */     
/*     */     try {
/*  90 */       String decrypt = TokenHandler.decrypt(key, f);
/*  91 */       System.out.println("Number is " + decrypt);
/*     */       
/*  93 */       String[] split = decrypt.split(",");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 100 */       String s1 = null, s2 = null, s3 = null;
/*     */       
/* 102 */       for (int i = 0; i < split.length; i++) {
/*     */         
/* 104 */         s1 = split[0];
/* 105 */         s2 = split[1];
/* 106 */         s3 = split[2];
/*     */       } 
/*     */       
/* 109 */       System.out.println("s1 is " + s1 + " s2 is " + s2 + " s3 is " + s3);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 114 */       String ani = s1.substring(2);
/*     */       
/* 116 */       s3 = "20" + s3;
/* 117 */       System.out.println("date is " + s3);
/*     */       
/* 119 */       StringBuilder sb = new StringBuilder(s3);
/* 120 */       sb.insert(4, "-").insert(7, "-");
/* 121 */       String get = sb.toString().trim();
/* 122 */       System.out.println(sb);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       String service_name = "NT_Stream_R5";
/* 133 */       String service_id = "3786";
/*     */       
/* 135 */       boolean re = DBMethods.save_ext_idFromRedirect(ext_id, service_name, service_id, ani);
/* 136 */       if (re)
/*     */       {
/* 138 */         System.out.println("ext_id come in redirect saved in db");
/*     */       }
/*     */ 
/*     */       
/* 142 */       String service = "NT_Stream_R5";
/*     */       
/* 144 */       HttpSession session = request.getSession();
/* 145 */       session.setAttribute("ani", ani);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 150 */       String checkToken = DBMethods.checkToken(ani, service);
/* 151 */       if (checkToken.equals("new"))
/*     */       {
/* 153 */         boolean b = DBMethods.saveToken(f, ext_id, ani, s2, get);
/*     */         
/* 155 */         if (b)
/*     */         {
/* 157 */           System.out.println("Token Saved First time");
/*     */         }
/*     */         
/* 160 */         response.sendRedirect("index.jsp");
/*     */       }
/* 162 */       else if (checkToken.equals("old"))
/*     */       {
/*     */ 
/*     */         
/* 166 */         System.out.println("Old Token");
/*     */         
/* 168 */         boolean b = DBMethods.saveToken(f, ext_id, ani, s2, get);
/*     */         
/* 170 */         if (b)
/*     */         {
/* 172 */           System.out.println("Token Saved next time");
/*     */         }
/*     */         
/* 175 */         String checkAni = DBMethods.checkAni(ani, service);
/* 176 */         if (checkAni.equals("yes"))
/*     */         {
/* 178 */           System.out.println("Found in tbl_subscription");
/*     */ 
/*     */           
/* 181 */           response.sendRedirect("index.jsp");
/*     */         }
/* 183 */         else if (checkAni.equals("no"))
/*     */         {
/* 185 */           System.out.println("Either Not Recharge or not in tbl_subscription");
/*     */           
/* 187 */           String checkInUnSub = DBMethods.checkInUnSub(ani, service);
/*     */           
/* 189 */           if (checkInUnSub.equals("unsub"))
/*     */           {
/* 191 */             System.out.println("Unsubscribed Today");
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
/* 206 */             response.sendRedirect("unsub.jsp");
/*     */           }
/* 208 */           else if (checkInUnSub.equals("sub"))
/*     */           {
/* 210 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 211 */             Date date = new Date();
/* 212 */             String now = sdf.format(date);
/*     */             
/* 214 */             System.out.println("Date Now is " + now);
/*     */ 
/*     */ 
/*     */             
/* 218 */             LocalDate dd1 = LocalDate.parse(get);
/* 219 */             LocalDate dd2 = LocalDate.parse(now);
/*     */             
/* 221 */             long diff = ChronoUnit.DAYS.between(dd1, dd2);
/* 222 */             System.out.println("Diff b/w dates is " + diff);
/*     */             
/* 224 */             if (diff == 0L)
/*     */             {
/* 226 */               System.out.println("date diff is ok - Send to portal");
/* 227 */               response.sendRedirect("index.jsp");
/*     */             }
/*     */             else
/*     */             {
/* 231 */               System.out.println("Date diff is not ok");
/* 232 */               response.sendRedirect("new_index.jsp");
/*     */             
/*     */             }
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 243 */           response.getWriter().append("Something Went Wrong");
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 248 */     } catch (Exception e) {
/*     */       
/* 250 */       e.printStackTrace();
/* 251 */       response.getWriter().append("Wrong Token");
/*     */     } 
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void call(JSONObject send) {
/* 370 */     String url = "http://91.205.172.123:43972/subscription-callback/collect-cent-callback/";
/* 371 */     HttpURLConnection urlConnection = null;
/* 372 */     System.out.println("Nitin Sir ::: " + url);
/*     */     try {
/* 374 */       URL u = new URL(url);
/* 375 */       urlConnection = (HttpURLConnection)u.openConnection();
/* 376 */       urlConnection.setDoOutput(true);
/* 377 */       urlConnection.setRequestMethod("POST");
/* 378 */       urlConnection.setRequestProperty("Content-Type", "application/json");
/* 379 */       urlConnection.connect();
/*     */       
/* 381 */       System.out.println("Json " + send);
/* 382 */       OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
/* 383 */       wr.write(send.toString());
/* 384 */       wr.flush();
/* 385 */       StringBuilder response = null;
/* 386 */       System.out.println("Reponse Code  :: " + urlConnection.getResponseCode());
/* 387 */       if (urlConnection.getResponseCode() == 200) {
/* 388 */         BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
/* 389 */         String line = null;
/* 390 */         response = new StringBuilder();
/* 391 */         while ((line = br.readLine()) != null) {
/* 392 */           response.append(line);
/* 393 */           System.out.println(response);
/*     */         } 
/*     */       } 
/* 396 */     } catch (Exception e) {
/* 397 */       e.printStackTrace();
/*     */     } finally {
/* 399 */       urlConnection.disconnect();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\servlet\redirectServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */