/*     */ package com.Helper;
/*     */ 
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DBMethods
/*     */ {
/*     */   public static boolean saveQueryStringLogs(String callback, String ani) {
/*  14 */     boolean result = false;
/*     */     
/*     */     try {
/*  17 */       String query = "insert into tbl_sdplogs(callback,ani,service) values(?,?,?)";
/*  18 */       PreparedStatement p = GetDbMethods.getPS(query);
/*  19 */       p.setString(1, callback);
/*  20 */       p.setString(2, ani);
/*  21 */       p.setString(3, "wp-video");
/*  22 */       int r = p.executeUpdate();
/*  23 */       if (r > 0)
/*     */       {
/*  25 */         result = true;
/*     */       }
/*     */     }
/*  28 */     catch (Exception e) {
/*     */       
/*  30 */       e.printStackTrace();
/*     */     } 
/*  32 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean saveJsonLogs(String callback) {
/*  37 */     boolean result = false;
/*     */     
/*     */     try {
/*  40 */       String query = "insert into Json(callback,service) values(?,?)";
/*  41 */       PreparedStatement p = GetDbMethods.getPS(query);
/*  42 */       p.setString(1, callback);
/*  43 */       p.setString(2, "wp-video");
/*  44 */       int r = p.executeUpdate();
/*  45 */       if (r > 0)
/*     */       {
/*  47 */         result = true;
/*     */       }
/*     */     }
/*  50 */     catch (Exception e) {
/*     */       
/*  52 */       e.printStackTrace();
/*     */     } 
/*  54 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean saveToken(String token, String ext_id, String s1, String s2, String s3) {
/*  59 */     boolean b = false;
/*     */     
/*     */     try {
/*  62 */       String query = "insert into token_row(token,ext_id,s1,s2,s3,service) values(?,?,?,?,?,?)";
/*  63 */       PreparedStatement p = GetDbMethods.getPS(query);
/*     */       
/*  65 */       p.setString(1, token);
/*  66 */       p.setString(2, ext_id);
/*  67 */       p.setString(3, s1);
/*  68 */       p.setString(4, s2);
/*  69 */       p.setString(5, s3);
/*  70 */       p.setString(6, "wp-video");
/*  71 */       int i = p.executeUpdate();
/*     */       
/*  73 */       if (i > 0)
/*     */       {
/*  75 */         b = true;
/*     */       }
/*  77 */     } catch (Exception e) {
/*     */       
/*  79 */       e.printStackTrace();
/*     */     } 
/*  81 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean saveDlr(String mt, String client, String requestId, String refId, String operator, String telNo, String amount, String statusId, String wpAuthRefReq, String statusTime, String statusMessage, String typee) {
/*  86 */     boolean result = false;
/*     */     
/*     */     try {
/*  89 */       String query = "insert into dlr(tag,servicename,subscriberid,ref,network,ani,charge,status,wpAuthReqRef,datetime,sub_type,type,m_act) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*  90 */       PreparedStatement p = GetDbMethods.getPS(query);
/*     */       
/*  92 */       p.setString(1, mt);
/*  93 */       p.setString(2, client);
/*  94 */       p.setString(3, requestId);
/*  95 */       p.setString(4, refId);
/*  96 */       p.setString(5, operator);
/*  97 */       p.setString(6, telNo);
/*  98 */       p.setString(7, amount);
/*  99 */       p.setString(8, statusId);
/* 100 */       p.setString(9, wpAuthRefReq);
/* 101 */       p.setString(10, statusTime);
/* 102 */       p.setString(11, statusMessage);
/* 103 */       p.setString(12, typee);
/* 104 */       p.setString(13, "web");
/* 105 */       int r = p.executeUpdate();
/* 106 */       if (r > 0)
/*     */       {
/* 108 */         result = true;
/*     */       }
/*     */     }
/* 111 */     catch (Exception e) {
/*     */       
/* 113 */       e.printStackTrace();
/*     */     } 
/* 115 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean saveBillingSuccess(String requestId, String operator, String telNo, String amount, String wpAuthRefReq, String statusTime) {
/* 120 */     boolean result = false;
/*     */ 
/*     */     
/*     */     try {
/* 124 */       String query = "insert into tbl_billing_success(ani,operator,telNo,totalAmount,wpAuthReqRef,statusTime) values(?,?,?,?,?,?,?)";
/* 125 */       PreparedStatement p = GetDbMethods.getPS(query);
/*     */       
/* 127 */       p.setString(1, requestId);
/* 128 */       p.setString(2, operator);
/* 129 */       p.setString(3, telNo);
/* 130 */       p.setString(4, amount);
/* 131 */       p.setString(5, wpAuthRefReq);
/* 132 */       p.setString(6, statusTime);
/*     */       
/* 134 */       int r = p.executeUpdate();
/*     */       
/* 136 */       if (r > 0) {
/*     */         
/* 138 */         System.out.println("Save in tbl_billing_success");
/* 139 */         result = true;
/*     */       } 
/* 141 */     } catch (Exception e) {
/*     */       
/* 143 */       e.printStackTrace();
/*     */     } 
/* 145 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean saveDrlUnsub(String mt, String client, String requestId, String refId, String operator, String telNo, String source, String statusTime, String rxTelNo, String rxMessage) {
/* 150 */     boolean b = false;
/*     */     
/*     */     try {
/* 153 */       String query = "insert into dlr(tag,servicename,subscriberid,ref,network,ani,source,datetime,rxTelNo,sub_type,type,d_act) values(?,?,?,?,?,?,?,?,?,?,?,?)";
/* 154 */       PreparedStatement p = GetDbMethods.getPS(query);
/* 155 */       p.setString(1, mt);
/* 156 */       p.setString(2, client);
/* 157 */       p.setString(3, requestId);
/* 158 */       p.setString(4, refId);
/* 159 */       p.setString(5, operator);
/* 160 */       p.setString(6, telNo);
/* 161 */       p.setString(7, source);
/* 162 */       p.setString(8, statusTime);
/* 163 */       p.setString(9, rxTelNo);
/* 164 */       p.setString(10, rxMessage);
/* 165 */       p.setString(11, "unsub");
/* 166 */       p.setString(12, "web");
/*     */       
/* 168 */       int r = p.executeUpdate();
/* 169 */       if (r > 0)
/*     */       {
/* 171 */         b = true;
/*     */       }
/* 173 */     } catch (Exception e) {
/*     */       
/* 175 */       e.printStackTrace();
/*     */     } 
/* 177 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String checkAni(String token, String service) {
/* 182 */     String result = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 192 */       String query2 = 
/* 193 */         "select ani from tbl_subscription where ani='" + token + "' and DATE(next_billed_date)>=DATE(NOW()- INTERVAL 2 DAY) and service_type='" + service + "' ";
/*     */       
/* 195 */       ResultSet r2 = GetDbMethods.getRS(query2);
/* 196 */       if (r2.next())
/*     */       {
/* 198 */         System.out.println("User Exist and Recharged");
/* 199 */         result = "yes";
/*     */       }
/*     */       else
/*     */       {
/* 203 */         System.out.println("Not Recharged");
/* 204 */         result = "no";
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 212 */     catch (Exception e) {
/*     */       
/* 214 */       e.printStackTrace();
/*     */     } 
/* 216 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getServiceUrl() {
/* 221 */     String serviceurl = null;
/*     */     
/*     */     try {
/* 224 */       String query = "select serviceurl from tbl_service where serviceid='3786' ";
/* 225 */       ResultSet r = GetDbMethods.getRS(query);
/* 226 */       if (r.next())
/*     */       {
/* 228 */         serviceurl = r.getString("serviceurl");
/*     */       }
/* 230 */     } catch (Exception e) {
/*     */       
/* 232 */       e.printStackTrace();
/*     */     } 
/* 234 */     return serviceurl;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean save_ext_id(String ext_id, String servicename, String serviceUrl, String ipAddress, String userAgent) {
/* 239 */     boolean b = false;
/*     */     
/*     */     try {
/* 242 */       String query = "insert into offnet_logging(ext_id,servicename, serviceUrl, ipAddress, userAgent) values(?,?,?,?,?)";
/* 243 */       PreparedStatement p = GetDbMethods.getPS(query);
/* 244 */       p.setString(1, ext_id);
/* 245 */       p.setString(2, servicename);
/* 246 */       p.setString(3, serviceUrl);
/* 247 */       p.setString(4, ipAddress);
/* 248 */       p.setString(5, userAgent);
/*     */       
/* 250 */       int e = p.executeUpdate();
/* 251 */       if (e > 0)
/*     */       {
/* 253 */         b = true;
/*     */       }
/* 255 */     } catch (Exception e) {
/*     */       
/* 257 */       e.printStackTrace();
/*     */     } 
/* 259 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean check_ext_id(String ext_id, String servicename) {
/* 264 */     boolean b = false;
/*     */     
/*     */     try {
/* 267 */       String query = "select count(1) as count from offnet_logging where ext_id='" + ext_id + "' and servicename='" + servicename + "' ";
/* 268 */       ResultSet r = GetDbMethods.getRS(query);
/* 269 */       if (r.next()) {
/*     */         
/* 271 */         int count = r.getInt("count");
/* 272 */         if (count == 1) {
/*     */           
/* 274 */           System.out.println("ext_id present in db");
/* 275 */           b = true;
/*     */         }
/*     */         else {
/*     */           
/* 279 */           System.out.println("ext_id not present in db");
/* 280 */           b = false;
/*     */         } 
/*     */       } 
/* 283 */     } catch (Exception e) {
/*     */       
/* 285 */       e.printStackTrace();
/*     */     } 
/* 287 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String checkExisting(String ani) {
/* 293 */     String type = null;
/*     */     
/*     */     try {
/* 296 */       String service_type = "NT_Stream_R5";
/* 297 */       String query = "select ani from tbl_subscription where ani='" + ani + "' and service_type='" + service_type + "' ";
/* 298 */       ResultSet r = GetDbMethods.getRS(query);
/* 299 */       if (r.next()) {
/*     */         
/* 301 */         type = "ren";
/*     */       }
/*     */       else {
/*     */         
/* 305 */         type = "sub";
/*     */       } 
/* 307 */     } catch (Exception e) {
/*     */       
/* 309 */       e.printStackTrace();
/*     */     } 
/* 311 */     return type;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String checkToken(String ani, String service) {
/* 316 */     String result = null;
/*     */     
/*     */     try {
/* 319 */       String query = "select s1 from token_row where s1='" + ani + "' and s2 = '" + service + "' and status='active' ";
/* 320 */       ResultSet r = GetDbMethods.getRS(query);
/* 321 */       if (r.next()) {
/*     */         
/* 323 */         result = "old";
/*     */       }
/*     */       else {
/*     */         
/* 327 */         result = "new";
/*     */       } 
/* 329 */     } catch (Exception e) {
/*     */       
/* 331 */       e.printStackTrace();
/*     */     } 
/* 333 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void saveTokenToSubscription(String ani, String service_type, String billing_date) {
/*     */     try {
/* 342 */       String query = "insert into tbl_subscription(ani,service_type,billing_date,m_act) values(?,?,?,?)";
/*     */       
/* 344 */       PreparedStatement p = GetDbMethods.getPS(query);
/* 345 */       p.setString(1, ani);
/* 346 */       p.setString(2, service_type);
/* 347 */       p.setString(3, billing_date);
/* 348 */       p.setString(4, "token");
/*     */       
/* 350 */       int i = p.executeUpdate();
/*     */     }
/* 352 */     catch (Exception e) {
/*     */       
/* 354 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String checkInUnSub(String ani, String service_type) {
/* 360 */     String result = null;
/*     */     
/*     */     try {
/* 363 */       String query = "select ani from tbl_subscription_unsub where ANI='" + ani + "' and service_type='" + service_type + "' and Date(unsub_date_time)=Date(Now())";
/* 364 */       ResultSet r = GetDbMethods.getRS(query);
/* 365 */       if (r.next()) {
/*     */         
/* 367 */         result = "unsub";
/*     */       }
/*     */       else {
/*     */         
/* 371 */         result = "sub";
/*     */       } 
/* 373 */     } catch (Exception e) {
/*     */       
/* 375 */       e.printStackTrace();
/*     */     } 
/* 377 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String updateStatus(String ani, String service) {
/* 382 */     String result = null;
/*     */     
/*     */     try {
/* 385 */       String query = "update token_row set status='unactive' where s1='" + ani + "' and s2='" + service + "' and status='active' ";
/* 386 */       PreparedStatement p = GetDbMethods.getPS(query);
/* 387 */       int i = p.executeUpdate();
/* 388 */       if (i > 0) {
/*     */         
/* 390 */         result = "yes";
/*     */       }
/*     */       else {
/*     */         
/* 394 */         result = "no";
/*     */       } 
/* 396 */     } catch (Exception e) {
/*     */       
/* 398 */       e.printStackTrace();
/*     */     } 
/* 400 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean save_ext_idFromRedirect(String ext_id, String service_name, String service_id, String ani) {
/* 408 */     boolean b = false;
/*     */     
/*     */     try {
/* 411 */       String query = "insert into ext_id_check(ext_id, servicename, service_id, ani) values(?,?,?,?) ";
/* 412 */       PreparedStatement p = GetDbMethods.getPS(query);
/* 413 */       p.setString(1, ext_id);
/* 414 */       p.setString(2, service_name);
/* 415 */       p.setString(3, service_id);
/* 416 */       p.setString(4, ani);
/*     */       
/* 418 */       int i = p.executeUpdate();
/* 419 */       if (i > 0)
/*     */       {
/* 421 */         b = true;
/*     */       }
/*     */     }
/* 424 */     catch (Exception e) {
/*     */       
/* 426 */       e.printStackTrace();
/*     */     } 
/* 428 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String match_ext_id(String ani, String service_name, String service_id) {
/* 434 */     String ext_id = "blank";
/*     */     
/*     */     try {
/* 437 */       String query = 
/* 438 */         "select ani, ext_id from ext_id_check where ani='" + ani + "' and servicename='" + service_name + "' and service_id='" + service_id + "' and status='no' ";
/*     */       
/* 440 */       System.out.println("Before Result Set");
/*     */       
/* 442 */       ResultSet r = GetDbMethods.getRS(query);
/* 443 */       if (r.next()) {
/*     */         
/* 445 */         ext_id = r.getString("ext_id");
/* 446 */         if (ext_id == null)
/*     */         {
/* 448 */           ext_id = "null";
/*     */         }
/*     */       } 
/* 451 */       System.out.println("After Result Set");
/* 452 */       return ext_id;
/*     */     }
/* 454 */     catch (Exception e) {
/*     */       
/* 456 */       e.printStackTrace();
/* 457 */       return ext_id;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateStatus_ext_id_check(String ani, String service_id) {
/*     */     try {
/* 466 */       String query = "update ext_id_check set status='yes' where ani='" + ani + "' and service_id='" + service_id + "' ";
/* 467 */       PreparedStatement p = GetDbMethods.getPS(query);
/* 468 */       int i = p.executeUpdate();
/* 469 */     } catch (Exception e) {
/*     */       
/* 471 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void saveResponse(String ani, String ext_id, String service_id, Integer response) {
/*     */     try {
/* 480 */       String query = "insert into save_send_ext_id(ani, ext_id, service_id, response) values(?,?,?,?)";
/* 481 */       PreparedStatement p = GetDbMethods.getPS(query);
/* 482 */       p.setString(1, ani);
/* 483 */       p.setString(2, ext_id);
/* 484 */       p.setString(3, service_id);
/* 485 */       p.setInt(4, response.intValue());
/* 486 */       int i = p.executeUpdate();
/*     */     }
/* 488 */     catch (Exception e) {
/*     */       
/* 490 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\com\Helper\DBMethods.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */