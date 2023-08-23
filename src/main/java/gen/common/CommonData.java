/*     */ package gen.common;
/*     */ 
/*     */ import gen.Parameter;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.Statement;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ public class CommonData
/*     */ {
/*  14 */   Parameter objpara = new Parameter();
/*  15 */   String response = this.objpara.response;
/*  16 */   JSONObject obj = new JSONObject();
/*     */ 
/*     */   
/*     */   public String addTimeeLogging(Connection con, Parameter objparameter) {
/*     */     try {
/*  21 */       String addCat = "";
/*  22 */       int getValue = getTimeLogging(con, objparameter, objparameter.getChannel());
/*  23 */       if (getValue == 0) {
/*  24 */         addCat = "insert into `tbl_time_logging`(`videoid`,`ani`,`duration`,`portal`,`percentage`,`catid`) values (?,?,?,'" + 
/*  25 */           objparameter.getChannel() + "',?,?)";
/*     */       } else {
/*     */         
/*  28 */         addCat = "update  `tbl_time_logging` set `videoid`=?,`ani`=?,`duration`=?,`percentage`=?,`catid`=?,modifieddatetime=now() where _id='" + 
/*  29 */           getValue + "' and portal='" + objparameter.getChannel() + "'";
/*     */       } 
/*  31 */       userdatausageloging(con, objparameter);
/*     */       
/*  33 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/*  34 */       System.out.println(addCat);
/*  35 */       pstmt.setString(1, objparameter.getVideoid());
/*  36 */       pstmt.setString(2, objparameter.getAni());
/*  37 */       pstmt.setString(3, objparameter.getDuration());
/*  38 */       pstmt.setString(4, objparameter.getPercentage());
/*  39 */       pstmt.setString(5, objparameter.getCategory());
/*  40 */       pstmt.executeUpdate();
/*  41 */       pstmt.close();
/*     */       
/*  43 */       this.obj.put("status", "1");
/*  44 */       this.obj.put("data", "{}");
/*  45 */       this.obj.put("message", "success");
/*  46 */       this.response = this.obj.toString();
/*  47 */     } catch (Exception e) {
/*  48 */       e.printStackTrace();
/*     */     } 
/*  50 */     return this.response;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void userdatausageloging(Connection con, Parameter objparameter) {
/*     */     try {
/*  57 */       int per = (int)Math.ceil(Double.parseDouble(objparameter.getPercentage()));
/*     */       
/*  59 */       if (per == 0) {
/*     */         return;
/*     */       }
/*  62 */       String query = "select videosize from tbl_videos where videoid='" + objparameter.getVideoid() + "'";
/*  63 */       System.out.println(query);
/*  64 */       Statement stm = con.createStatement();
/*  65 */       ResultSet rs = stm.executeQuery(query);
/*  66 */       if (rs.next()) {
/*  67 */         int size = rs.getInt(1);
/*  68 */         int dd = per * size / 100;
/*     */         
/*  70 */         query = "select count(*) as count from tbl_user_datausage where ani='" + objparameter.getAni() + 
/*  71 */           "' and pageurl='" + objparameter.getVideoid() + 
/*  72 */           "' and datetime<=DATE_ADD(now(), INTERVAL 5 MINUTE)";
/*  73 */         System.out.println(query);
/*  74 */         Statement stmt = con.createStatement();
/*  75 */         ResultSet rst = stmt.executeQuery(query);
/*  76 */         int count = 0;
/*  77 */         if (rst.next()) {
/*  78 */           count = rst.getInt("count");
/*     */         }
/*     */         
/*  81 */         if (count != 0) {
/*  82 */           query = "update tbl_user_datausage set size='" + dd + "' where pageurl='" + 
/*  83 */             objparameter.getVideoid() + "' and ani='" + objparameter.getAni() + "'";
/*     */         }
/*     */         else {
/*     */           
/*  87 */           query = "insert into tbl_user_datausage (ani,pageurl,size) values('" + objparameter.getAni() + "','" + 
/*  88 */             objparameter.getVideoid() + "','" + dd + "')";
/*     */         } 
/*     */         
/*  91 */         System.out.println(query);
/*  92 */         Statement st = con.createStatement();
/*  93 */         st.executeUpdate(query);
/*     */       }
/*     */     
/*  96 */     } catch (Exception e) {
/*  97 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String addRecentSearch(Connection con, Parameter objparameter) {
/*     */     try {
/* 105 */       String addCat = "insert into `tbl_recent_search`(`videoid`,`ani`,`name`,`portal`,`sub_cat_id`,`datetime`) values (?,?,?,?,?,now())";
/*     */       
/* 107 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/* 108 */       System.out.println(addCat);
/* 109 */       pstmt.setString(1, objparameter.getVideoid());
/* 110 */       pstmt.setString(2, objparameter.getAni());
/* 111 */       pstmt.setString(3, objparameter.getName());
/* 112 */       pstmt.setString(4, objparameter.getChannel());
/* 113 */       pstmt.setString(5, objparameter.getCategory());
/* 114 */       pstmt.executeUpdate();
/* 115 */       pstmt.close();
/*     */       
/* 117 */       this.obj.put("status", "1");
/* 118 */       this.obj.put("data", "{}");
/* 119 */       this.obj.put("message", "success");
/* 120 */       this.response = this.obj.toString();
/* 121 */     } catch (Exception e) {
/* 122 */       e.printStackTrace();
/*     */     } 
/* 124 */     return this.response;
/*     */   }
/*     */ 
/*     */   
/*     */   public String searchVideo(Connection con, Parameter objparameter) {
/*     */     try {
/* 130 */       JSONArray jarray = new JSONArray();
/* 131 */       JSONObject obj1 = new JSONObject();
/* 132 */       String addCat = "SELECT * FROM tbl_videos WHERE NAME LIKE ('%" + objparameter.getData() + 
/* 133 */         "%') AND   STATUS ='0' AND (startdate >= CURDATE() OR startdate IS NULL) AND category IN (SELECT category_name FROM tbl_cat WHERE STATUS='2' OR STATUS='1')  LIMIT 10\r\n";
/*     */       
/* 135 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/* 136 */       ResultSet res = pstmt.executeQuery();
/*     */       
/* 138 */       while (res.next()) {
/* 139 */         JSONObject obj2 = new JSONObject();
/* 140 */         obj2.put("vurl", res.getString("vurl"));
/* 141 */         obj2.put("imgurl", res.getString("imgurl"));
/* 142 */         obj2.put("sub_cat_id", res.getString("sub_cat_id"));
/* 143 */         obj2.put("name", res.getString("name"));
/* 144 */         obj2.put("videoid", res.getString("videoid"));
/* 145 */         jarray.put(obj2);
/*     */       } 
/* 147 */       obj1.put("list", jarray);
/* 148 */       this.obj.put("status", "1");
/* 149 */       this.obj.put("data", obj1);
/* 150 */       this.obj.put("message", "success");
/* 151 */       this.response = this.obj.toString();
/* 152 */     } catch (Exception e) {
/* 153 */       e.printStackTrace();
/*     */     } 
/* 155 */     return this.response;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeLogging(Connection con, Parameter objparameter, String portal) {
/* 160 */     JSONObject main = new JSONObject();
/* 161 */     int _id = 0;
/*     */     
/*     */     try {
/* 164 */       String addCat = "select _id from tbl_time_logging where videoid=? and ani=? and portal ='" + portal + "' ";
/*     */       
/* 166 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/*     */       
/* 168 */       pstmt.setString(1, objparameter.getVideoid());
/* 169 */       pstmt.setString(2, objparameter.getAni());
/*     */       
/* 171 */       ResultSet res = pstmt.executeQuery();
/* 172 */       if (res.next()) {
/* 173 */         _id = res.getInt("_id");
/*     */       }
/* 175 */     } catch (Exception e) {
/* 176 */       e.printStackTrace();
/*     */     } 
/* 178 */     return _id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTimeDurationLogging(Connection con, Parameter objparameter, String portal) {
/* 183 */     JSONObject main = new JSONObject();
/* 184 */     String duration = "0";
/*     */     
/*     */     try {
/* 187 */       String addCat = "select duration from tbl_time_logging where videoid=? and ani=? and portal ='" + portal + 
/* 188 */         "' ";
/*     */       
/* 190 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/* 191 */       System.out.println(addCat);
/*     */       
/* 193 */       pstmt.setString(1, objparameter.getVideoid());
/* 194 */       pstmt.setString(2, objparameter.getAni());
/* 195 */       ResultSet res = pstmt.executeQuery();
/* 196 */       if (res.next()) {
/* 197 */         duration = res.getString("duration");
/*     */       }
/*     */       
/* 200 */       System.out.println("Final" + duration);
/*     */     }
/* 202 */     catch (Exception e) {
/* 203 */       e.printStackTrace();
/*     */     } 
/* 205 */     return duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDuration(Connection con, Parameter objparameter) {
/* 210 */     JSONObject main = new JSONObject();
/*     */     
/*     */     try {
/* 213 */       String getDuration = getTimeDurationLogging(con, objparameter, objparameter.getChannel());
/* 214 */       main.put("duration", getDuration);
/*     */       
/* 216 */       this.obj.put("status", "1");
/* 217 */       this.obj.put("data", main);
/* 218 */       this.obj.put("message", "success");
/* 219 */       this.response = this.obj.toString();
/* 220 */     } catch (Exception e) {
/* 221 */       e.printStackTrace();
/*     */     } 
/* 223 */     return this.response;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String addWhishlist(Connection con, Parameter objparameter) {
/*     */     try {
/* 230 */       String addCat = "";
/* 231 */       addCat = "insert into `tbl_wishlist`(`videoid`,`ani`,`portal`) values (?,?,'" + objparameter.getChannel() + 
/* 232 */         "')";
/*     */       
/* 234 */       PreparedStatement pstmt = con.prepareStatement(addCat);
/*     */       
/* 236 */       pstmt.setString(1, objparameter.getVideoid());
/* 237 */       pstmt.setString(2, objparameter.getAni());
/* 238 */       pstmt.executeUpdate();
/* 239 */       pstmt.close();
/*     */       
/* 241 */       this.obj.put("status", "1");
/* 242 */       this.obj.put("data", "{}");
/* 243 */       this.obj.put("message", "success");
/* 244 */       this.response = this.obj.toString();
/* 245 */     } catch (Exception e) {
/* 246 */       e.printStackTrace();
/*     */     } 
/* 248 */     return this.response;
/*     */   }
/*     */ 
/*     */   
/*     */   public String addViews(Connection con, Parameter objparameter) {
/* 253 */     JSONObject main = new JSONObject();
/*     */     
/*     */     try {
/* 256 */       String findq = "select * from tbl_video_logging where videoid='" + objparameter.getVideoid() + "' and ani='" + 
/* 257 */         objparameter.getAni() + "' and view='1'";
/* 258 */       PreparedStatement ps = con.prepareStatement(findq);
/* 259 */       ResultSet res = ps.executeQuery();
/* 260 */       if (!res.next()) {
/* 261 */         String addCat = "insert into `tbl_video_logging`(`videoid`,`ani`,`type`,`view`) values (?,?,?,?)";
/*     */         
/* 263 */         PreparedStatement pstmt = con.prepareStatement(addCat);
/*     */         
/* 265 */         pstmt.setString(1, objparameter.getVideoid());
/* 266 */         pstmt.setString(2, objparameter.getAni());
/* 267 */         pstmt.setString(3, objparameter.getChannel());
/* 268 */         pstmt.setString(4, "1");
/* 269 */         pstmt.executeUpdate();
/* 270 */         pstmt.close();
/*     */       } 
/*     */       
/* 273 */       this.obj.put("status", "1");
/* 274 */       this.obj.put("data", main);
/* 275 */       this.obj.put("message", "success");
/* 276 */       this.response = this.obj.toString();
/* 277 */     } catch (Exception e) {
/* 278 */       e.printStackTrace();
/*     */     } 
/* 280 */     return this.response;
/*     */   }
/*     */ 
/*     */   
/*     */   public String UpdateLike(Connection conn, Parameter objparameter) {
/*     */     try {
/* 286 */       ResultSet checkEntry = checkEntry(conn, objparameter);
/* 287 */       if (checkEntry.next())
/* 288 */         if (checkEntry.getString("count").equalsIgnoreCase("1")) {
/* 289 */           ResultSet resultSet = getLikeStatus(conn, objparameter);
/* 290 */           if (resultSet.next()) {
/* 291 */             String upqry = "update tbl_like set status = '0' where videoid = '" + objparameter.getVideoid() + 
/* 292 */               "' and portal='" + objparameter.getChannel() + "' and userid='" + 
/* 293 */               objparameter.getAni() + "' ";
/* 294 */             System.out.println(upqry);
/* 295 */             PreparedStatement ps = conn.prepareStatement(upqry);
/* 296 */             ps.executeUpdate();
/* 297 */             ps.close();
/*     */           } else {
/* 299 */             String upqry = "update tbl_like set status = '1' where videoid = '" + objparameter.getVideoid() + 
/* 300 */               "' and portal='" + objparameter.getChannel() + "' and userid='" + 
/* 301 */               objparameter.getAni() + "' ";
/* 302 */             PreparedStatement ps = conn.prepareStatement(upqry);
/* 303 */             System.out.println(upqry);
/* 304 */             ps.executeUpdate();
/* 305 */             ps.close();
/*     */           } 
/*     */         } else {
/* 308 */           String upqry = "insert into tbl_like (name,userid,videoid,category,tbl_like.like,datetime,status,portal) values('" + 
/* 309 */             objparameter.getAni() + "','" + objparameter.getAni() + "','" + objparameter.getVideoid() + 
/* 310 */             "','" + objparameter.getCategory() + "','1',now(),'1','" + objparameter.getChannel() + 
/* 311 */             "')";
/* 312 */           PreparedStatement ps = conn.prepareStatement(upqry);
/* 313 */           System.out.println(upqry);
/* 314 */           ps.executeUpdate();
/* 315 */           ps.close();
/*     */         }  
/* 317 */       ResultSet getCount = getTotalLikes(conn, objparameter);
/* 318 */       if (getCount.next()) {
/* 319 */         this.obj.put("count", getCount.getString("count"));
/*     */       } else {
/* 321 */         this.obj.put("count", "0");
/*     */       } 
/* 323 */       ResultSet getStatus = getLikeStatus(conn, objparameter);
/* 324 */       if (getStatus.next()) {
/* 325 */         this.obj.put("liked", "1");
/*     */       } else {
/* 327 */         this.obj.put("liked", "0");
/*     */       } 
/* 329 */       this.obj.put("status", "1");
/* 330 */     } catch (Exception e) {
/* 331 */       this.obj.put("status", "0");
/* 332 */       e.printStackTrace();
/*     */     } 
/* 334 */     return this.obj.toString();
/*     */   }
/*     */   
/*     */   public ResultSet getTotalLikes(Connection con, Parameter objparameter) {
/* 338 */     ResultSet rs = null;
/*     */     try {
/* 340 */       String getQuery = "select count(1) as count from tbl_like where videoid = '" + objparameter.getVideoid() + 
/* 341 */         "' and portal='" + objparameter.getChannel() + "' and status ='1'";
/* 342 */       PreparedStatement ps = con.prepareStatement(getQuery);
/* 343 */       System.out.println(getQuery);
/* 344 */       rs = ps.executeQuery();
/* 345 */     } catch (Exception e) {
/* 346 */       e.printStackTrace();
/*     */     } 
/* 348 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet checkEntry(Connection con, Parameter objparameter) {
/* 352 */     ResultSet rs = null;
/*     */     try {
/* 354 */       String getQuery = "select count(1) as count from tbl_like where userid = '" + objparameter.getAni() + 
/* 355 */         "' and videoid = '" + objparameter.getVideoid() + "' and portal='" + objparameter.getChannel() + 
/* 356 */         "'";
/* 357 */       PreparedStatement ps = con.prepareStatement(getQuery);
/* 358 */       System.out.println(getQuery);
/* 359 */       rs = ps.executeQuery();
/* 360 */     } catch (Exception e) {
/* 361 */       e.printStackTrace();
/*     */     } 
/* 363 */     return rs;
/*     */   }
/*     */   
/*     */   public ResultSet getLikeStatus(Connection conn, Parameter objparameter) {
/* 367 */     ResultSet resp = null;
/*     */     try {
/* 369 */       String getQuery = "select * from tbl_like where videoid = '" + objparameter.getVideoid() + "'and userid='" + 
/* 370 */         objparameter.getAni() + "' and status ='1' and portal='" + objparameter.getChannel() + "'";
/* 371 */       PreparedStatement ps = conn.prepareStatement(getQuery);
/* 372 */       System.out.println(getQuery);
/* 373 */       resp = ps.executeQuery();
/* 374 */     } catch (Exception e) {
/* 375 */       e.printStackTrace();
/* 376 */       return resp;
/*     */     } 
/* 378 */     return resp;
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\common\CommonData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */