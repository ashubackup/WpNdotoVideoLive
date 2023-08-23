/*     */ package gen;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
///*     */ import net.sf.uadetector.OperatingSystem;
///*     */ import net.sf.uadetector.ReadableUserAgent;
///*     */ import net.sf.uadetector.UserAgentStringParser;
///*     */ import net.sf.uadetector.service.UADetectorServiceFactory;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ public class DataCollector
/*     */ {
/*  19 */   Parameter objpara = new Parameter();
/*  20 */   String response = this.objpara.response;
/*  21 */   JSONObject obj = new JSONObject();
/*  22 */   JSONObject obj1 = new JSONObject();
/*     */   
/*     */   public ResultSet getCat() {
/*  25 */     ResultSet rs = null;
/*     */     try {
/*  27 */       String getQuery = "select * from tbl_cat where status = '2' and name= 'Hollywood'";
/*  28 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/*  30 */       rs = ps.executeQuery();
/*  31 */     } catch (Exception e) {
/*  32 */       e.printStackTrace();
/*     */     } 
/*  34 */     return rs;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Connection checkConn(Connection conn) throws SQLException {
/*  39 */     if (conn == null) {
/*     */       
/*  41 */       System.out.println("Inside COnnection " + Loader.NdotMTNConn);
/*     */       
/*  43 */       conn = DbConnection.getMTNDatabse();
/*     */     } 
/*  45 */     return conn;
/*     */   }
/*     */   
/*     */   public ResultSet getAllCat(String age) {
/*  49 */     ResultSet rs = null;
/*     */     try {
/*  51 */       String getQuery = null;
/*  52 */       age = "1";
/*     */       
/*  54 */       if (age.equalsIgnoreCase("1")) {
/*  55 */         getQuery = "select * from tbl_cat where age not in('" + age + "') and  status = '1' or status='2'";
/*     */       } else {
/*  57 */         getQuery = "select * from tbl_cat where  status = '1' or status='2'";
/*     */       } 
/*     */       
/*  60 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*  61 */       System.out.println(getQuery);
/*  62 */       rs = ps.executeQuery();
/*  63 */     } catch (Exception e) {
/*  64 */       e.printStackTrace();
/*     */     } 
/*  66 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getSubCat(String id) {
/*  70 */     ResultSet rs = null;
/*     */     try {
/*  72 */       String getQuery = "select * from tbl_sub_cat where parent_cat_id = '" + id + "' and status = '1'";
/*  73 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/*  75 */       rs = ps.executeQuery();
/*  76 */     } catch (Exception e) {
/*  77 */       e.printStackTrace();
/*     */     } 
/*  79 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getMainCat(String age) {
/*  83 */     ResultSet rs = null;
/*  84 */     String getQuery = null;
/*     */     try {
/*  86 */       age = "1";
/*     */       
/*  88 */       if (age.equalsIgnoreCase("1")) {
/*  89 */         getQuery = "select * from tbl_sub_cat where parent_cat_id not in (select category_name from tbl_cat where age='" + 
/*  90 */           age + "') and status = '1' or status='2' ";
/*     */       } else {
/*  92 */         getQuery = "select * from tbl_sub_cat where status = '1' or status='2' ";
/*     */       } 
/*     */ 
/*     */       
/*  96 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/*  98 */       rs = ps.executeQuery();
/*  99 */     } catch (Exception e) {
/* 100 */       e.printStackTrace();
/*     */     } 
/* 102 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getWatching(String portal) {
/* 106 */     ResultSet rs = null;
/*     */     try {
/* 108 */       String getQuery = "SELECT \r\n    c.sub_cat_id, \r\n    m.duration,\r\n    m.videoid,\r\n    m.percentage, \r\n    c.vurl, \r\n    c.imgurl,\r\n    s.sub_cat_name,\r\nm.modifieddatetime\r\nFROM\r\n    tbl_time_logging m \r\nINNER JOIN tbl_videos c INNER JOIN tbl_sub_cat s  \r\n\tON c.videoid = m.videoid and m.portal='" + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 114 */         portal + 
/* 115 */         "'  AND s.sub_cat_id=c.sub_cat_id  GROUP BY c.sub_cat_id order by m.modifieddatetime desc limit 5 ";
/* 116 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 118 */       rs = ps.executeQuery();
/* 119 */     } catch (Exception e) {
/* 120 */       e.printStackTrace();
/*     */     } 
/* 122 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getwhishlist(String portal) {
/* 126 */     ResultSet rs = null;
/*     */     try {
/* 128 */       String getQuery = "SELECT    \r\n m.videoid,\r\n    c.sub_cat_id, \r\n    c.vurl, \r\n    c.name, \r\n    s.sub_cat_name,\r\n    c.imgurl\r\nFROM\r\n    tbl_wishlist m \r\nINNER JOIN tbl_videos c INNER JOIN tbl_sub_cat s  \r\n\tON c.videoid = m.videoid and m.portal='" + 
/*     */ 
/*     */         
/* 131 */         portal + 
/* 132 */         "'  AND s.sub_cat_id=c.sub_cat_id GROUP BY c.sub_cat_id order by m.datetime desc limit 5 ";
/* 133 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 135 */       rs = ps.executeQuery();
/* 136 */     } catch (Exception e) {
/* 137 */       e.printStackTrace();
/*     */     } 
/* 139 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getVideos(String id, String genre) {
/* 143 */     ResultSet rs = null;
/*     */     try {
/* 145 */       String getQuery = "select * from tbl_videos where sub_cat_id ='" + id + 
/* 146 */         "' and status = '0' and (startdate >= curdate() or startdate is null or genres like '" + genre + 
/* 147 */         "%')";
/* 148 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 150 */       rs = ps.executeQuery();
/* 151 */     } catch (Exception e) {
/* 152 */       e.printStackTrace();
/*     */     } 
/* 154 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getVideos(String id) {
/* 158 */     ResultSet rs = null;
/*     */     try {
/* 160 */       String getQuery = "select * from tbl_videos where sub_cat_id ='" + id + 
/* 161 */         "' and status = '0' and (startdate >= curdate() or startdate is null )";
/* 162 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 164 */       rs = ps.executeQuery();
/* 165 */     } catch (Exception e) {
/* 166 */       e.printStackTrace();
/*     */     } 
/* 168 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getTop(String portal) {
/* 172 */     ResultSet rs = null;
/*     */     try {
/* 174 */       String getQuery = "\r\nSELECT COUNT(1),\r\nt1.videoid,\r\nc.name,\r\nc.sub_cat_id, \r\nc.vurl, \r\nc.imgurl \r\nFROM tbl_video_logging t1 INNER JOIN tbl_videos c WHERE t1.VIEW='1' AND t1.videoid=c.videoid AND t1.TYPE='" + 
/*     */ 
/*     */         
/* 177 */         portal + "'  GROUP BY 2 ORDER BY COUNT(1) DESC\r\n" + "limit 10";
/* 178 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 180 */       rs = ps.executeQuery();
/* 181 */     } catch (Exception e) {
/* 182 */       e.printStackTrace();
/*     */     } 
/* 184 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getRCat() {
/* 188 */     ResultSet rs = null;
/*     */     try {
/* 190 */       String getQuery = "SELECT category,DATETIME FROM tbl_videos ORDER BY DATETIME DESC";
/* 191 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 193 */       rs = ps.executeQuery();
/* 194 */     } catch (Exception e) {
/* 195 */       e.printStackTrace();
/*     */     } 
/* 197 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getRVideo(String id) {
/* 201 */     ResultSet rs = null;
/*     */     try {
/* 203 */       String getQuery = "SELECT * FROM tbl_videos WHERE category ='" + id + 
/* 204 */         "' AND STATUS='0' ORDER BY DATETIME DESC LIMIT 2";
/* 205 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 207 */       rs = ps.executeQuery();
/* 208 */     } catch (Exception e) {
/* 209 */       e.printStackTrace();
/*     */     } 
/* 211 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getLatestVideos() {
/* 215 */     ResultSet rs = null;
/*     */     try {
/* 217 */       String getQuery = "select * from tbl_videos  where status ='0' and (startdate >= curdate() or startdate is null) and category in (select category_name from tbl_cat where status='2' or status='1')  order by datetime desc limit 5";
/* 218 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 220 */       rs = ps.executeQuery();
/* 221 */     } catch (Exception e) {
/* 222 */       e.printStackTrace();
/*     */     } 
/* 224 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getDataBySubID(String id) {
/* 228 */     ResultSet rs = null;
/*     */     try {
/* 230 */       String getQuery = "select * from tbl_videos where sub_cat_id ='" + id + 
/* 231 */         "' and status = '0' and (startdate >= curdate() or startdate is null)";
/* 232 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 234 */       rs = ps.executeQuery();
/* 235 */     } catch (Exception e) {
/* 236 */       e.printStackTrace();
/*     */     } 
/* 238 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getTeaser() {
/* 242 */     ResultSet rs = null;
/*     */     try {
/* 244 */       String getQuery = "SELECT t1.* FROM tbl_teaser t1 WHERE video_cat IN (SELECT category_name FROM tbl_cat WHERE STATUS ='1' OR STATUS='2') AND STATUS = '1' AND teaser_id!='0' ORDER BY t1.order ASC";
/* 245 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 247 */       rs = ps.executeQuery();
/* 248 */     } catch (Exception e) {
/* 249 */       e.printStackTrace();
/*     */     } 
/* 251 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getTeaserDetails(String id) {
/* 255 */     ResultSet rs = null;
/*     */     try {
/* 257 */       String getQuery = "SELECT * FROM tbl_teaser where teaser_id='" + id + "'";
/* 258 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 260 */       rs = ps.executeQuery();
/* 261 */     } catch (Exception e) {
/* 262 */       e.printStackTrace();
/*     */     } 
/* 264 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getTeaserByID(String id) {
/* 268 */     ResultSet rs = null;
/*     */     try {
/* 270 */       String getQuery = "select t1.* from tbl_teaser t1 where video_cat ='" + id + 
/* 271 */         "' and status = '1'  order by t1.order asc";
/* 272 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/* 273 */       rs = ps.executeQuery();
/* 274 */       if (rs.next()) {
/* 275 */         rs.beforeFirst();
/* 276 */         return rs;
/*     */       } 
/* 278 */       return getTeaser();
/*     */     }
/* 280 */     catch (Exception e) {
/* 281 */       e.printStackTrace();
/*     */       
/* 283 */       return rs;
/*     */     } 
/*     */   }
/*     */   public ResultSet getvideoDetailsbyId(String id) {
/* 287 */     ResultSet rs = null;
/*     */     try {
/* 289 */       String getQuery = "select * from tbl_videos where videoid='" + id + "'";
/* 290 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 292 */       rs = ps.executeQuery();
/* 293 */     } catch (Exception e) {
/* 294 */       e.printStackTrace();
/*     */     } 
/* 296 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getvideoDetailsbyId(String id, String ani, String portal) {
/* 300 */     ResultSet rs = null;
/*     */     try {
/* 302 */       String getQuery = "select t1.*,(select count(1) from tbl_video_logging where view='1' and videoid='" + id + 
/* 303 */         "') as views,(select count(1) from tbl_wishlist where  videoid='" + id + "' and portal='" + portal + 
/* 304 */         "' and ani = '" + ani + 
/* 305 */         "') as whishlist,(select count(1) as count from tbl_like where videoid = '" + id + 
/* 306 */         "' and portal='" + portal + 
/* 307 */         "' and status ='1') as likes,(select count(1) as count from tbl_like where videoid = '" + id + 
/* 308 */         "' and  portal='" + portal + "' and status ='1' and userid='" + ani + 
/* 309 */         "' ) as userlike from tbl_videos as t1 where videoid='" + id + "'";
/* 310 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 312 */       rs = ps.executeQuery();
/* 313 */     } catch (Exception e) {
/* 314 */       e.printStackTrace();
/*     */     } 
/* 316 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getGenre() {
/* 320 */     ResultSet rs = null;
/*     */     try {
/* 322 */       String getQuery = "select * from tbl_genre";
/* 323 */       PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
/*     */       
/* 325 */       rs = ps.executeQuery();
/* 326 */     } catch (Exception e) {
/* 327 */       e.printStackTrace();
/*     */     } 
/* 329 */     return rs;
/*     */   }
/*     */   
/*     */   public String addLogging(Connection con, Parameter objparameter) {
/* 333 */     JSONObject main = new JSONObject();
/*     */     
/*     */     try {
/* 336 */       String addCat = "insert into `tbl_video_logging`(`videoid`,`ani`,`type`,`channel`,`portal`) values (?,?,?,?,?)";
/*     */       
/* 338 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/*     */       
/* 340 */       pstmt.setString(1, objparameter.getVideoid());
/* 341 */       pstmt.setString(2, objparameter.getAni());
/* 342 */       pstmt.setString(3, objparameter.getCategory());
/* 343 */       pstmt.setString(4, objparameter.getChannel());
/* 344 */       pstmt.setString(5, objparameter.getPortal());
/* 345 */       pstmt.executeUpdate();
/* 346 */       pstmt.close();
/*     */       
/* 348 */       this.obj.put("status", "1");
/* 349 */       this.obj.put("data", main);
/* 350 */       this.obj.put("message", "success");
/* 351 */       this.response = this.obj.toString();
/* 352 */     } catch (Exception e) {
/* 353 */       e.printStackTrace();
/*     */     } 
/* 355 */     return this.response;
/*     */   }
/*     */ 
/*     */   
/*     */   public int insertLogs(String clickid, Connection conn) {
/* 360 */     int resp = 0; try {
/*     */       Exception exception2;
/* 362 */       String instQry = "insert into tbl_conv_logs(clickid,createddatetime,modifieddatetime,provider,service,mode,pubid) values (?,now(),now(),'TC','videos','vendor','')";
/* 363 */       PreparedStatement statement = conn.prepareStatement(instQry, 1);
/* 364 */       statement.setString(1, clickid);
/* 365 */       statement.executeUpdate();
/* 366 */       Exception exception1 = null;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 371 */     catch (Exception e) {
/* 372 */       e.printStackTrace();
/*     */     } 
/* 374 */     return resp;
/*     */   }
/*     */   
/*     */   public String getStatus(String ani, String servicename) {
/* 378 */     String State = "";
/*     */     
/*     */     try {
/* 381 */       Statement stmt = Loader.NdotMTNConn.createStatement();
/* 382 */       String chkqry = "select * from tbl_subscription where ani='" + ani + "' and service_type='" + 
/* 383 */         servicename + "'  ";
/* 384 */       System.out.println(chkqry);
/* 385 */       ResultSet rs = stmt.executeQuery(chkqry);
/* 386 */       if (rs.next()) {
/* 387 */         State = getUserState(ani, servicename);
/*     */       } else {
/* 389 */         State = "2";
/*     */       } 
/* 391 */     } catch (Exception e) {
/* 392 */       e.printStackTrace();
/*     */     } 
/* 394 */     return State;
/*     */   }
/*     */   
/*     */   public String getUserState(String ani, String service) {
/* 398 */     String State = "0";
/*     */     try {
/* 400 */       Statement stmt = null;
/* 401 */       stmt = Loader.NdotMTNConn.createStatement();
/* 402 */       int cnt = 0;
/* 403 */       String subQry = "select count(1) as cnt from tbl_subscription where ani='" + ani + 
/* 404 */         "' and service_type='" + service + "' " + "and date(next_billed_date)>= Date(subdate(now(),1))";
/* 405 */       System.out.println("subQry::::" + subQry);
/* 406 */       ResultSet rssub = stmt.executeQuery(subQry);
/* 407 */       if (rssub.next()) {
/* 408 */         cnt = rssub.getInt(1);
/* 409 */         System.out.println("cnt~~" + cnt);
/*     */       } 
/* 411 */       if (cnt > 0) {
/* 412 */         State = "1";
/*     */       } else {
/* 414 */         State = "0";
/*     */       } 
/* 416 */     } catch (Exception e) {
/* 417 */       e.printStackTrace();
/*     */     } 
/* 419 */     return State;
/*     */   }
/*     */   
/*     */   public Parameter checkAgeLog(String ani) {
/* 423 */     Parameter param = new Parameter();
/*     */     
/* 425 */     String query = "select count(1),status from tbl_video_usr where ani='" + ani + "'";
/*     */     
/* 427 */     System.out.println(query);
/*     */     try {
/* 429 */       PreparedStatement ps = Loader.contentConn.prepareStatement(query);
/* 430 */       ResultSet rs = ps.executeQuery();
/* 431 */       if (rs.next()) {
/*     */         
/* 433 */         param.setState(rs.getString(1));
/* 434 */         param.setStatus(rs.getString(2));
/*     */       } 
/* 436 */     } catch (SQLException e) {
/*     */       
/* 438 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 441 */     return param;
/*     */   }
/*     */   
/*     */   public void addPlayLogging(Connection con, Parameter objparameter) {
/* 445 */     String query = "";
/*     */     try {
/* 447 */       query = "select * from tbl_play_logs where videoid='" + objparameter.getVideoid() + "'and ani='" + 
/* 448 */         objparameter.getAni() + "'and date(datetime)= Date(now())";
/* 449 */       Statement stmt = con.createStatement();
/* 450 */       ResultSet set = stmt.executeQuery(query);
/* 451 */       if (set.next()) {
/* 452 */         query = "UPDATE tbl_play_logs SET duration=?,percentage=? WHERE ani=? AND DATE(DATETIME)=DATE(NOW()) AND videoid=?";
/*     */         
/* 454 */         PreparedStatement pstmt = con.prepareStatement(query);
/* 455 */         pstmt.setString(1, objparameter.getDuration());
/* 456 */         pstmt.setString(2, objparameter.getPercentage());
/* 457 */         pstmt.setString(4, objparameter.getVideoid());
/* 458 */         pstmt.setString(3, objparameter.getAni());
/* 459 */         pstmt.executeUpdate();
/* 460 */         pstmt.close();
/*     */       } else {
/* 462 */         query = "insert into tbl_play_logs(videoid,ani,duration,percentage,datetime) values(?,?,?,?,now())";
/*     */         
/* 464 */         PreparedStatement pstmt = con.prepareStatement(query);
/* 465 */         pstmt.setString(1, objparameter.getVideoid());
/* 466 */         pstmt.setString(2, objparameter.getAni());
/* 467 */         pstmt.setString(3, objparameter.getDuration());
/* 468 */         pstmt.setString(4, objparameter.getPercentage());
/* 469 */         pstmt.execute();
/* 470 */         pstmt.close();
/*     */       }
/*     */     
/* 473 */     } catch (Exception e) {
/* 474 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUserDetail(Parameter objparameter, Connection conn) {
/* 480 */     ResultSet rs = null;
/* 481 */     String resp = objparameter.response;
/*     */     try {
/* 483 */       String query = "select * from tbl_video_usr where ani='" + objparameter.getAni() + "'";
/* 484 */       rs = getResultSet(conn, query);
/* 485 */       JSONObject json = createJson(rs, 2);
/* 486 */       resp = json.toString();
/* 487 */       System.out.println(resp);
/* 488 */       return resp;
/* 489 */     } catch (Exception e) {
/* 490 */       e.printStackTrace();
/*     */       
/* 492 */       return resp;
/*     */     } 
/*     */   }
/*     */   public JSONObject createJson(ResultSet res, int col) {
/* 496 */     JSONArray jarray = new JSONArray();
/* 497 */     JSONObject json1 = new JSONObject();
/*     */     
/*     */     try {
/* 500 */       ResultSetMetaData rsmd = res.getMetaData();
/* 501 */       if (res.next()) {
/* 502 */         res.beforeFirst();
/* 503 */         while (res.next()) {
/* 504 */           json1 = new JSONObject();
/* 505 */           for (int i = 1; i <= col; i++) {
/* 506 */             json1.put(rsmd.getColumnName(i), res.getString(rsmd.getColumnName(i)));
/*     */           }
/* 508 */           jarray.put(json1);
/*     */         } 
/*     */       } 
/* 511 */       System.out.println("This is array " + jarray);
/* 512 */       this.obj.put("list", jarray);
/* 513 */       this.obj1.put("status", "1");
/* 514 */       this.obj1.put("data", this.obj);
/* 515 */       this.obj1.put("message", "success");
/* 516 */       return this.obj1;
/* 517 */     } catch (Exception e) {
/* 518 */       e.printStackTrace();
/* 519 */       return this.obj1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getServiceData(Connection conn, String field, String data, String checkData) {
/*     */     try {
/* 525 */       String query = "select " + field + " from tbl_service_master where " + checkData + "='" + data + "'";
/* 526 */       ResultSet res = getResultSet(conn, query);
/* 527 */       if (res.next())
/* 528 */         return res.getString(field); 
/* 529 */     } catch (Exception e) {
/* 530 */       e.printStackTrace();
/*     */     } 
/* 532 */     return "";
/*     */   }
/*     */   
/*     */   public ResultSet getResultSet(Connection conn, String query) {
/* 536 */     ResultSet res = null;
/*     */     try {
/* 538 */       PreparedStatement ps = conn.prepareStatement(query);
/* 539 */       System.out.println("query ---- " + query);
/* 540 */       res = ps.executeQuery();
/* 541 */     } catch (Exception e) {
/* 542 */       e.printStackTrace();
/*     */     } 
/* 544 */     return res;
/*     */   }
/*     */   
/*     */   public Parameter UpdateQuery(Connection conn, String query) {
/* 548 */     Parameter objparameter = new Parameter();
/*     */     try {
/* 550 */       System.out.println(query);
/* 551 */       PreparedStatement ps = conn.prepareStatement(query);
/* 552 */       ps.executeUpdate();
/* 553 */     } catch (Exception e) {
/* 554 */       e.printStackTrace();
/* 555 */       return objparameter;
/*     */     } 
/* 557 */     return objparameter;
/*     */   }
/*     */   
/*     */   public String addTimeeLogging(Connection con, Parameter objparameter) {
/*     */     try {
/* 562 */       String addCat = "";
/* 563 */       String type = "hollywood";
/* 564 */       int getValue = getTimeLogging(con, objparameter, "hollywood");
/* 565 */       if (getValue == 0) {
/* 566 */         addCat = "insert into `tbl_time_logging`(`videoid`,`ani`,`duration`,`portal`) values (?,?,?,'hollywood')";
/*     */       } else {
/*     */         
/* 569 */         addCat = "update  `tbl_time_logging` set `videoid`=?,`ani`=?,`duration`=? where _id='" + getValue + 
/* 570 */           "' and portal='" + "hollywood" + "'";
/*     */       } 
/* 572 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/* 573 */       pstmt.setString(1, objparameter.getVideoid());
/* 574 */       pstmt.setString(2, objparameter.getAni());
/* 575 */       pstmt.setString(3, objparameter.getDuration());
/* 576 */       pstmt.executeUpdate();
/* 577 */       pstmt.close();
/* 578 */       this.obj.put("status", "1");
/* 579 */       this.obj.put("data", "{}");
/* 580 */       this.obj.put("message", "success");
/* 581 */       this.response = this.obj.toString();
/* 582 */     } catch (Exception e) {
/* 583 */       e.printStackTrace();
/*     */     } 
/* 585 */     return this.response;
/*     */   }
/*     */   
/*     */   public int getTimeLogging(Connection con, Parameter objparameter, String portal) {
/* 589 */     JSONObject main = new JSONObject();
/* 590 */     int _id = 0;
/*     */     try {
/* 592 */       String addCat = "select _id from tbl_time_logging where videoid=? and ani=? and portal ='" + portal + 
/* 593 */         "' ";
/* 594 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/* 595 */       pstmt.setString(1, objparameter.getVideoid());
/* 596 */       pstmt.setString(2, objparameter.getAni());
/* 597 */       ResultSet res = pstmt.executeQuery();
/* 598 */       if (res.next()) {
/* 599 */         _id = res.getInt("_id");
/*     */       }
/* 601 */     } catch (Exception e) {
/* 602 */       e.printStackTrace();
/*     */     } 
/* 604 */     return _id;
/*     */   }
/*     */   
/*     */   public String getTimeDurationLogging(Connection con, Parameter objparameter, String portal) {
/* 608 */     JSONObject main = new JSONObject();
/* 609 */     String duration = "0";
/*     */     try {
/* 611 */       String addCat = "select duration from tbl_time_logging where videoid=? and ani=? and portal ='" + 
/* 612 */         portal + "' ";
/* 613 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/* 614 */       pstmt.setString(1, objparameter.getVideoid());
/* 615 */       pstmt.setString(2, objparameter.getAni());
/* 616 */       ResultSet res = pstmt.executeQuery();
/* 617 */       if (res.next()) {
/* 618 */         duration = res.getString("duration");
/*     */       }
/* 620 */     } catch (Exception e) {
/* 621 */       e.printStackTrace();
/*     */     } 
/* 623 */     return duration;
/*     */   }
/*     */   
/*     */   public void userLoginLogs(String msidn, String videoId) {
/* 627 */     String query = "";
/*     */     try {
/* 629 */       if (videoId != null) {
/* 630 */         query = "insert into userloginlogs(msidn,portal,log_type,video_id) values('" + msidn + 
/* 631 */           "','NDOTOSTREAM-STS','videoPlay','" + videoId + "')";
/*     */       } else {
/* 633 */         query = "insert into userloginlogs(msidn,portal,log_type) values('" + msidn + 
/* 634 */           "','NDOTOSTREAM-STS','Login')";
/* 635 */       }  Statement prep = Loader.contentConn.createStatement();
/* 636 */       prep.execute(query);
/*     */     }
/* 638 */     catch (Exception e) {
/* 639 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getDuration(Connection con, Parameter objparameter) {
/* 644 */     JSONObject main = new JSONObject();
/*     */     try {
/* 646 */       String getDuration = getTimeDurationLogging(con, objparameter, "hollywood");
/* 647 */       main.put("duration", getDuration);
/* 648 */       this.obj.put("status", "1");
/* 649 */       this.obj.put("data", main);
/* 650 */       this.obj.put("message", "success");
/* 651 */       this.response = this.obj.toString();
/* 652 */     } catch (Exception e) {
/* 653 */       e.printStackTrace();
/*     */     } 
/* 655 */     return this.response;
/*     */   }
/*     */ 
/*     */   
/*     */   public String addUserDetail(Parameter param, Connection contentConn) {
/* 660 */     String status = "1";
/* 661 */     System.out.println(String.valueOf(param.getAni()) + param.getResult());
/* 662 */     if (param.getResult().equalsIgnoreCase("yes")) {
/* 663 */       status = "0";
/*     */     }
/*     */     
/* 666 */     String query1 = "select count(1) as cnt from tbl_video_usr where ani='" + param.getAni() + "' ";
/*     */     
/* 668 */     String query = "insert into tbl_video_usr (ani,ageType,status) values('" + param.getAni() + "','" + 
/* 669 */       param.getResult() + "'," + status + ")";
/*     */ 
/*     */     
/*     */     try {
/* 673 */       PreparedStatement ps = contentConn.prepareStatement(query);
/* 674 */       PreparedStatement ps1 = contentConn.prepareStatement(query1);
/* 675 */       ResultSet rs = ps1.executeQuery();
/* 676 */       int count = 0;
/* 677 */       if (rs.next()) {
/* 678 */         count = rs.getInt("cnt");
/*     */       }
/*     */       
/* 681 */       if (count == 0) {
/* 682 */         ps.executeUpdate();
/*     */       }
/* 684 */     } catch (SQLException e) {
/*     */       
/* 686 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 689 */     return status;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long insertUserSocialMod(String mod, String provider, String pubid, String clickid) {
/* 694 */     if (provider.length() == 0) {
/* 695 */       provider = "social";
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 700 */       String query = "insert into tbl_conv_logs(clickid,createddatetime,modifieddatetime,provider,service,mode,pubid) values (?,now(),now(),?,'videos',?,?)";
/*     */       
/* 702 */       PreparedStatement ps = Loader.NdotMTNConn.prepareStatement(query, 1);
/* 703 */       ps.setString(1, clickid);
/* 704 */       ps.setString(2, provider);
/* 705 */       ps.setString(3, mod);
/* 706 */       ps.setString(4, pubid);
/* 707 */       System.out.println("---------" + query);
/* 708 */       ps.executeUpdate();
/* 709 */       ResultSet rs = ps.getGeneratedKeys();
/* 710 */       if (rs.next()) {
/* 711 */         return rs.getLong(1);
/*     */       }
/*     */       
/* 714 */       ps.close();
/* 715 */     } catch (Exception e) {
/* 716 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 719 */     return 0L;
/*     */   }
/*     */   
/*     */   public static String getAgeStatusfromSubcat(String catid) {
/* 723 */     String age = null;
/*     */     try {
/* 725 */       String query = "select age from tbl_sub_cat where sub_cat_id='" + catid + "'";
/* 726 */       PreparedStatement ps = Loader.contentConn.prepareStatement(query);
/* 727 */       ResultSet rs = ps.executeQuery();
/* 728 */       if (rs.next()) {
/* 729 */         age = rs.getString(1);
/*     */       }
/* 731 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 734 */     return age;
/*     */   }
/*     */ 
/*     */   
/*     */   public String addShareLog(Parameter param, Connection contentConn) {
/* 739 */     String insertquery = "insert into sharelog(ani,mode,portal) values('" + param.getAni() + "','" + param.getMod() + 
/* 740 */       "','" + param.getPortal() + "')";
/* 741 */     String result = insertquery;
/*     */     try {
/* 743 */       Statement st = contentConn.createStatement();
/* 744 */       st.execute(insertquery);
/* 745 */       result = "success";
/*     */     }
/* 747 */     catch (SQLException e) {
/* 748 */       e.printStackTrace();
/* 749 */       e.getMessage();
/*     */     } 
/* 751 */     return result;
/*     */   }
/*     */   
/*     */   public String addComment(Parameter param, Connection contentConn) {
/* 755 */     JSONObject jo = new JSONObject();
/* 756 */     String status = "0";
/*     */     
/*     */     try {
/* 759 */       String query = "insert into tbl_comment (name,comment,videoid,datetime,portal) values(?,?,?,now(),'mtn')";
/* 760 */       System.out.println(query);
/* 761 */       PreparedStatement ps = Loader.contentConn.prepareStatement(query);
/* 762 */       ps.setString(1, param.getAni());
/* 763 */       ps.setString(2, param.getMessage());
/* 764 */       ps.setString(3, param.getVideoid());
/*     */       
/* 766 */       int i = ps.executeUpdate();
/* 767 */       System.out.println("status  " + i);
/* 768 */       jo.put("status", i);
/* 769 */     } catch (Exception e) {
/* 770 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 773 */     return jo.toString();
/*     */   }
/*     */   
/*     */   public String getComment(Parameter param, Connection contentConn) {
/* 777 */     JSONObject jo = new JSONObject();
/*     */     
/*     */     try {
/* 780 */       JSONArray ja = new JSONArray();
/* 781 */       String query = "select comment from tbl_comment where videoid='" + param.getVideoid() + 
/* 782 */         "' and portal='mtn'";
/* 783 */       PreparedStatement ps = Loader.contentConn.prepareStatement(query);
/* 784 */       System.out.println(query);
/* 785 */       ResultSet rs = ps.executeQuery();
/* 786 */       while (rs.next())
/*     */       {
/* 788 */         ja.put(rs.getString(1));
/*     */       }
/*     */       
/* 791 */       jo.put("comment", ja);
/*     */     }
/* 793 */     catch (Exception e) {
/* 794 */       e.getStackTrace();
/*     */     } 
/*     */     
/* 797 */     return jo.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String addTrackLogging(Connection Conn, Parameter param) {
/* 802 */     JSONObject jo = new JSONObject();
/*     */     
/*     */     try {
/* 805 */       String query = "insert into tbl_user_track (ani,portal) values('" + param.getAni() + "','" + 
/* 806 */         param.getPortal() + "')";
/* 807 */       PreparedStatement ps = Conn.prepareStatement(query);
/* 808 */       System.out.println(query);
/*     */       
/* 810 */       int status = ps.executeUpdate();
/*     */       
/* 812 */       jo.put("status", status);
/*     */     }
/* 814 */     catch (Exception e) {
/*     */       
/* 816 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 819 */     return jo.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void insertUserDevice(String agent, String ani) {
///* 824 */     UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
///*     */     
///* 826 */     ReadableUserAgent ra = parser.parse(agent);
///* 827 */     OperatingSystem os = ra.getOperatingSystem();
///* 828 */     String device = os.getFamilyName().toUpperCase();
///*     */ 
///*     */ 
///*     */     
///*     */     try {
///* 833 */       String query = "insert into tbl_user_device (ani,device_type) values('" + ani + "','" + device + "')";
///* 834 */       PreparedStatement ps = Loader.contentConn.prepareStatement(query);
///* 835 */       ps.executeUpdate();
///*     */     }
///* 837 */     catch (Exception e) {
///*     */       
///* 839 */       e.printStackTrace();
///*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void insertUserDataUsage(Scrapmodel param) {
/*     */     try {
/* 847 */       String query = "insert into tbl_user_datausage (ani,pageurl,size) values('" + param.getAni() + "','" + 
/* 848 */         param.getPageurl() + "','" + param.getSize() + "')";
/* 849 */       System.out.println(query);
/* 850 */       PreparedStatement ps = Loader.contentConn.prepareStatement(query);
/* 851 */       ps.executeUpdate();
/*     */     }
/* 853 */     catch (Exception e) {
/*     */       
/* 855 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void insertUserDataUsage1(Scrapmodel param) {
/*     */     try {
/* 864 */       String query = "insert into tbl_user_datausage (ani,pageurl,size) values('" + param.getAni1() + "','" + 
/* 865 */         param.getPageurl1() + "','" + param.getSize1() + "')";
/* 866 */       System.out.println(query);
/* 867 */       PreparedStatement ps = Loader.contentConn.prepareStatement(query);
/* 868 */       ps.executeUpdate();
/*     */     }
/* 870 */     catch (Exception e) {
/*     */       
/* 872 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String addActivity(Connection conn, Parameter param) {
/* 879 */     JSONObject jo = new JSONObject();
/*     */     
/*     */     try {
/* 882 */       String query = "insert into tbl_user_activity (ani,portal,time,status) values('" + param.getAni() + "','" + 
/* 883 */         param.getPortal() + "','" + param.getTime() + "','" + param.getStatus() + "')";
/* 884 */       PreparedStatement ps = conn.prepareStatement(query);
/* 885 */       System.out.println("User Activity ===> " + query);
/*     */       
/* 887 */       int status = ps.executeUpdate();
/*     */       
/* 889 */       jo.put("status", status);
/*     */     }
/* 891 */     catch (Exception e) {
/* 892 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 895 */     return jo.toString();
/*     */   }
/*     */   
/*     */   public static void checkToken() {}
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\DataCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */