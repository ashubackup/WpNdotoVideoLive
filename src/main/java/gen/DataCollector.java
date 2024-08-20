 package gen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataCollector {
   Parameter objpara = new Parameter();
   String response;
   JSONObject obj;
   JSONObject obj1;

   public DataCollector() {
      this.response = this.objpara.response;
      this.obj = new JSONObject();
      this.obj1 = new JSONObject();
   }

   public ResultSet getCat() {
      ResultSet rs = null;

      try {
         String getQuery = "select * from tbl_cat where status = '2' and name= 'Hollywood'";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return rs;
   }

   public static Connection checkConn(Connection conn) throws SQLException {
      if (conn == null) {
         System.out.println("Inside COnnection " + Loader.NdotMTNConn);
         conn = DbConnection.getMTNDatabse();
      }

      return conn;
   }

   public ResultSet getAllCat(String age) {
      ResultSet rs = null;

      try {
         String getQuery = null;
         age = "1";
         if (age.equalsIgnoreCase("1")) {
            getQuery = "select * from tbl_cat where age not in('" + age + "') and  status = '1' or status='2'";
         } else {
            getQuery = "select * from tbl_cat where  status = '1' or status='2'";
         }

         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         System.out.println(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getSubCat(String id) {
      ResultSet rs = null;

      try {
         String getQuery = "select * from tbl_sub_cat where parent_cat_id = '" + id + "' and status = '1'";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getMainCat(String age) {
      ResultSet rs = null;
      String getQuery = null;

      try {
         age = "1";
         if (age.equalsIgnoreCase("1")) {
            getQuery = "select * from tbl_sub_cat where parent_cat_id not in (select category_name from tbl_cat where age='" + age + "') and status = '1' or status='2' ";
         } else {
            getQuery = "select * from tbl_sub_cat where status = '1' or status='2' ";
         }

         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getWatching(String portal) {
      ResultSet rs = null;

      try {
         //String getQuery = "SELECT \r\n    c.sub_cat_id, \r\n    m.duration,\r\n    m.videoid,\r\n    m.percentage, \r\n    c.vurl, \r\n    c.imgurl,\r\n    s.sub_cat_name,\r\nm.modifieddatetime\r\nFROM\r\n    tbl_time_logging m \r\nINNER JOIN tbl_videos c INNER JOIN tbl_sub_cat s  \r\n\tON c.videoid = m.videoid and m.portal='" + portal + "'  AND s.sub_cat_id=c.sub_cat_id  GROUP BY c.sub_cat_id order by m.modifieddatetime desc limit 5 ";
    	  String getQuery = "SELECT c.sub_cat_id,\r\n"
    	  		+ "       m.duration,\r\n"
    	  		+ "       m.videoid,\r\n"
    	  		+ "       m.percentage,\r\n"
    	  		+ "       c.vurl,\r\n"
    	  		+ "       c.imgurl,\r\n"
    	  		+ "       s.sub_cat_name,\r\n"
    	  		+ "       m.modifieddatetime\r\n"
    	  		+ "FROM tbl_time_logging m\r\n"
    	  		+ "INNER JOIN tbl_videos c ON c.videoid = m.videoid\r\n"
    	  		+ "INNER JOIN tbl_sub_cat s ON s.sub_cat_id = c.sub_cat_id\r\n"
    	  		+ "WHERE m.portal = '"+portal+"'\r\n"
    	  		+ "GROUP BY c.sub_cat_id,\r\n"
    	  		+ "         m.duration,\r\n"
    	  		+ "         m.videoid,\r\n"
    	  		+ "         m.percentage,\r\n"
    	  		+ "         c.vurl,\r\n"
    	  		+ "         c.imgurl,\r\n"
    	  		+ "         s.sub_cat_name,\r\n"
    	  		+ "         m.modifieddatetime\r\n"
    	  		+ "ORDER BY m.modifieddatetime DESC\r\n"
    	  		+ "LIMIT 5;\r\n"
    	  		+ "";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getwhishlist(String portal) {
      ResultSet rs = null;

      try {
        // String getQuery = "SELECT    \r\n m.videoid,\r\n    c.sub_cat_id, \r\n    c.vurl, \r\n    c.name, \r\n    s.sub_cat_name,\r\n    c.imgurl\r\nFROM\r\n    tbl_wishlist m \r\nINNER JOIN tbl_videos c INNER JOIN tbl_sub_cat s  \r\n\tON c.videoid = m.videoid and m.portal='" + portal + "'  AND s.sub_cat_id=c.sub_cat_id GROUP BY c.sub_cat_id order by m.datetime desc limit 5 ";
    	  String getQuery = "SELECT \n"
					+ "    m.videoid, \n"
					+ "    c.sub_cat_id, \n"
					+ "    c.vurl, \n"
					+ "    c.name, \n"
					+ "    s.sub_cat_name, \n"
					+ "    c.imgurl \n"
					+ "FROM \n"
					+ "    tbl_wishlist m \n"
					+ "INNER JOIN \n"
					+ "    tbl_videos c ON c.videoid = m.videoid \n"
					+ "INNER JOIN \n"
					+ "    tbl_sub_cat s ON s.sub_cat_id = c.sub_cat_id \n"
					+ "WHERE \n"
					+ "    m.portal = 'DreamStream' \n"
					+ "GROUP BY \n"
					+ "    c.sub_cat_id ,\n"
					+ "    m.videoid,\n"
					+ "    c.vurl, \n"
					+ "    c.name, \n"
					+ "    s.sub_cat_name, \n"
					+ "    c.imgurl,\n"
					+ "    m.datetime\n"
					+ "ORDER BY \n"
					+ "    m.datetime DESC \n"
					+ "LIMIT 5;\n";

         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getVideos(String id, String genre) {
      ResultSet rs = null;

      try {
         String getQuery = "select * from tbl_videos where sub_cat_id ='" + id + "' and status = '0' and (startdate >= curdate() or startdate is null or genres like '" + genre + "%')";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return rs;
   }

   public ResultSet getVideos(String id) {
      ResultSet rs = null;

      try {
         String getQuery = "select * from tbl_videos where sub_cat_id ='" + id + "' and status = '0' and (startdate >= curdate() or startdate is null )";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getTop(String portal) {
      ResultSet rs = null;

      try {
        // String getQuery = "\r\nSELECT COUNT(1),\r\nt1.videoid,\r\nc.name,\r\nc.sub_cat_id, \r\nc.vurl, \r\nc.imgurl \r\nFROM tbl_video_logging t1 INNER JOIN tbl_videos c WHERE t1.VIEW='1' AND t1.videoid=c.videoid AND t1.TYPE='" + portal + "'  GROUP BY 2 ORDER BY COUNT(1) DESC\r\n" + "limit 10";
    	  String getQuery = "SELECT COUNT(1) AS view_count, \n" + "       t1.videoid, \n"
					+ "       MAX(c.name) AS NAME, \n" + "       MAX(c.sub_cat_id) AS sub_cat_id, \n"
					+ "       MAX(c.vurl) AS vurl, \n" + "       MAX(c.imgurl) AS imgurl \n"
					+ "FROM tbl_video_logging t1 \n" + "INNER JOIN tbl_videos c ON t1.videoid = c.videoid \n"
					+ "WHERE t1.VIEW = '1' \n" + "  AND t1.TYPE = 'DreamStream' \n" + "GROUP BY t1.videoid \n"
					+ "ORDER BY view_count DESC \n" + "LIMIT 10;\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n"
					+ "\n" + "\n" + "";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getRCat() {
      ResultSet rs = null;

      try {
         String getQuery = "SELECT category,DATETIME FROM tbl_videos ORDER BY DATETIME DESC";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return rs;
   }

   public ResultSet getRVideo(String id) {
      ResultSet rs = null;

      try {
         String getQuery = "SELECT * FROM tbl_videos WHERE category ='" + id + "' AND STATUS='0' ORDER BY DATETIME DESC LIMIT 2";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getLatestVideos() {
      ResultSet rs = null;

      try {
         String getQuery = "select * from tbl_videos  where status ='0' and (startdate >= curdate() or startdate is null) and category in (select category_name from tbl_cat where status='2' or status='1')  order by datetime desc limit 5";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return rs;
   }

   public ResultSet getDataBySubID(String id) {
      ResultSet rs = null;

      try {
         String getQuery = "select * from tbl_videos where sub_cat_id ='" + id + "' and status = '0' and (startdate >= curdate() or startdate is null)";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getTeaser() {
      ResultSet rs = null;

      try {
         String getQuery = "SELECT t1.* FROM tbl_teaser t1 WHERE video_cat IN (SELECT category_name FROM tbl_cat WHERE STATUS ='1' OR STATUS='2') AND STATUS = '1' AND teaser_id!='0' ORDER BY t1.order ASC";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return rs;
   }

   public ResultSet getTeaserDetails(String id) {
      ResultSet rs = null;

      try {
         String getQuery = "SELECT * FROM tbl_teaser where teaser_id='" + id + "'";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getTeaserByID(String id) {
      ResultSet rs = null;

      try {
         String getQuery = "select t1.* from tbl_teaser t1 where video_cat ='" + id + "' and status = '1'  order by t1.order asc";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
         if (rs.next()) {
            rs.beforeFirst();
            return rs;
         } else {
            return this.getTeaser();
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         return rs;
      }
   }

   public ResultSet getvideoDetailsbyId(String id) {
      ResultSet rs = null;

      try {
         String getQuery = "select * from tbl_videos where videoid='" + id + "'";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rs;
   }

   public ResultSet getvideoDetailsbyId(String id, String ani, String portal) {
      ResultSet rs = null;

      try {
         String getQuery = "select t1.*,(select count(1) from tbl_video_logging where view='1' and videoid='" + id + "') as views,(select count(1) from tbl_wishlist where  videoid='" + id + "' and portal='" + portal + "' and ani = '" + ani + "') as whishlist,(select count(1) as count from tbl_like where videoid = '" + id + "' and portal='" + portal + "' and status ='1') as likes,(select count(1) as count from tbl_like where videoid = '" + id + "' and  portal='" + portal + "' and status ='1' and userid='" + ani + "' ) as userlike from tbl_videos as t1 where videoid='" + id + "'";
         System.out.println(getQuery);
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return rs;
   }

   public ResultSet getGenre() {
      ResultSet rs = null;

      try {
         String getQuery = "select * from tbl_genre";
         PreparedStatement ps = Loader.contentConn.prepareStatement(getQuery);
         rs = ps.executeQuery();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return rs;
   }

   public String addLogging(Connection con, Parameter objparameter) {
      JSONObject main = new JSONObject();

      try {
         String addCat = "insert into `tbl_video_logging`(`videoid`,`ani`,`type`,`channel`,`portal`) values (?,?,?,?,?)";
         PreparedStatement pstmt = con.prepareStatement(addCat);
         pstmt.setString(1, objparameter.getVideoid());
         pstmt.setString(2, objparameter.getAni());
         pstmt.setString(3, objparameter.getCategory());
         pstmt.setString(4, objparameter.getChannel());
         pstmt.setString(5, objparameter.getPortal());
         pstmt.executeUpdate();
         pstmt.close();
         this.obj.put("status", "1");
         this.obj.put("data", main);
         this.obj.put("message", "success");
         this.response = this.obj.toString();
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return this.response;
   }

   public int insertLogs(String clickid, Connection conn) {
      byte resp = 0;

      try {
         String instQry = "insert into tbl_conv_logs(clickid,createddatetime,modifieddatetime,provider,service,mode,pubid) values (?,now(),now(),'TC','videos','vendor','')";
         PreparedStatement statement = conn.prepareStatement(instQry, 1);
         statement.setString(1, clickid);
         statement.executeUpdate();
         Object var7 = null;
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return resp;
   }

   public String getStatus(String ani, String servicename) {
      String State = "";

      try {
         Statement stmt = Loader.NdotMTNConn.createStatement();
         String chkqry = "select * from tbl_subscription where ani='" + ani + "' and service_type='" + servicename + "'  ";
         System.out.println(chkqry);
         ResultSet rs = stmt.executeQuery(chkqry);
         if (rs.next()) {
            State = this.getUserState(ani, servicename);
         } else {
            State = "2";
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return State;
   }

   public String getUserState(String ani, String service) {
      String State = "0";

      try {
         Statement stmt = null;
         stmt = Loader.NdotMTNConn.createStatement();
         int cnt = 0;
         String subQry = "select count(1) as cnt from tbl_subscription where ani='" + ani + "' and service_type='" + service + "' " + "and date(next_billed_date)>= Date(subdate(now(),1))";
         System.out.println("subQry::::" + subQry);
         ResultSet rssub = stmt.executeQuery(subQry);
         if (rssub.next()) {
            cnt = rssub.getInt(1);
            System.out.println("cnt~~" + cnt);
         }

         if (cnt > 0) {
            State = "1";
         } else {
            State = "0";
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return State;
   }

   public Parameter checkAgeLog(String ani) {
      Parameter param = new Parameter();
      String query = "select count(1),status from tbl_video_usr where ani='" + ani + "'";
      System.out.println(query);

      try {
         PreparedStatement ps = Loader.contentConn.prepareStatement(query);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
            param.setState(rs.getString(1));
            param.setStatus(rs.getString(2));
         }
      } catch (SQLException var6) {
         var6.printStackTrace();
      }

      return param;
   }

   public void addPlayLogging(Connection con, Parameter objparameter) {
      String query = "";

      try {
         query = "select * from tbl_play_logs where videoid='" + objparameter.getVideoid() + "'and ani='" + objparameter.getAni() + "'and date(datetime)= Date(now())";
         Statement stmt = con.createStatement();
         ResultSet set = stmt.executeQuery(query);
         PreparedStatement pstmt;
         if (set.next()) {
            query = "UPDATE tbl_play_logs SET duration=?,percentage=? WHERE ani=? AND DATE(DATETIME)=DATE(NOW()) AND videoid=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, objparameter.getDuration());
            pstmt.setString(2, objparameter.getPercentage());
            pstmt.setString(4, objparameter.getVideoid());
            pstmt.setString(3, objparameter.getAni());
            pstmt.executeUpdate();
            pstmt.close();
         } else {
            query = "insert into tbl_play_logs(videoid,ani,duration,percentage,datetime) values(?,?,?,?,now())";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, objparameter.getVideoid());
            pstmt.setString(2, objparameter.getAni());
            pstmt.setString(3, objparameter.getDuration());
            pstmt.setString(4, objparameter.getPercentage());
            pstmt.execute();
            pstmt.close();
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   public String getUserDetail(Parameter objparameter, Connection conn) {
      ResultSet rs = null;
      String resp = objparameter.response;

      try {
         String query = "select * from tbl_video_usr where ani='" + objparameter.getAni() + "'";
         rs = this.getResultSet(conn, query);
         JSONObject json = this.createJson(rs, 2);
         resp = json.toString();
         System.out.println(resp);
         return resp;
      } catch (Exception var7) {
         var7.printStackTrace();
         return resp;
      }
   }

   public JSONObject createJson(ResultSet res, int col) {
      JSONArray jarray = new JSONArray();
      new JSONObject();

      try {
         ResultSetMetaData rsmd = res.getMetaData();
         if (res.next()) {
            res.beforeFirst();

            while(res.next()) {
               JSONObject json1 = new JSONObject();

               for(int i = 1; i <= col; ++i) {
                  json1.put(rsmd.getColumnName(i), res.getString(rsmd.getColumnName(i)));
               }

               jarray.put(json1);
            }
         }

         System.out.println("This is array " + jarray);
         this.obj.put("list", jarray);
         this.obj1.put("status", "1");
         this.obj1.put("data", this.obj);
         this.obj1.put("message", "success");
         return this.obj1;
      } catch (Exception var7) {
         var7.printStackTrace();
         return this.obj1;
      }
   }

   public String getServiceData(Connection conn, String field, String data, String checkData) {
      try {
         String query = "select " + field + " from tbl_service_master where " + checkData + "='" + data + "'";
         ResultSet res = this.getResultSet(conn, query);
         if (res.next()) {
            return res.getString(field);
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return "";
   }

   public ResultSet getResultSet(Connection conn, String query) {
      ResultSet res = null;

      try {
         PreparedStatement ps = conn.prepareStatement(query);
         System.out.println("query ---- " + query);
         res = ps.executeQuery();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return res;
   }

   public Parameter UpdateQuery(Connection conn, String query) {
      Parameter objparameter = new Parameter();

      try {
         System.out.println(query);
         PreparedStatement ps = conn.prepareStatement(query);
         ps.executeUpdate();
         return objparameter;
      } catch (Exception var5) {
         var5.printStackTrace();
         return objparameter;
      }
   }

   public String addTimeeLogging(Connection con, Parameter objparameter) {
      try {
         String addCat = "";
         String type = "hollywood";
         int getValue = this.getTimeLogging(con, objparameter, "hollywood");
         if (getValue == 0) {
            addCat = "insert into `tbl_time_logging`(`videoid`,`ani`,`duration`,`portal`) values (?,?,?,'hollywood')";
         } else {
            addCat = "update  `tbl_time_logging` set `videoid`=?,`ani`=?,`duration`=? where _id='" + getValue + "' and portal='" + "hollywood" + "'";
         }

         PreparedStatement pstmt = con.prepareStatement(addCat);
         pstmt.setString(1, objparameter.getVideoid());
         pstmt.setString(2, objparameter.getAni());
         pstmt.setString(3, objparameter.getDuration());
         pstmt.executeUpdate();
         pstmt.close();
         this.obj.put("status", "1");
         this.obj.put("data", "{}");
         this.obj.put("message", "success");
         this.response = this.obj.toString();
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return this.response;
   }

   public int getTimeLogging(Connection con, Parameter objparameter, String portal) {
      new JSONObject();
      int _id = 0;

      try {
         String addCat = "select _id from tbl_time_logging where videoid=? and ani=? and portal ='" + portal + "' ";
         PreparedStatement pstmt = con.prepareStatement(addCat);
         pstmt.setString(1, objparameter.getVideoid());
         pstmt.setString(2, objparameter.getAni());
         ResultSet res = pstmt.executeQuery();
         if (res.next()) {
            _id = res.getInt("_id");
         }
      } catch (Exception var9) {
         var9.printStackTrace();
      }

      return _id;
   }

   public String getTimeDurationLogging(Connection con, Parameter objparameter, String portal) {
      new JSONObject();
      String duration = "0";

      try {
         String addCat = "select duration from tbl_time_logging where videoid=? and ani=? and portal ='" + portal + "' ";
         PreparedStatement pstmt = con.prepareStatement(addCat);
         pstmt.setString(1, objparameter.getVideoid());
         pstmt.setString(2, objparameter.getAni());
         ResultSet res = pstmt.executeQuery();
         if (res.next()) {
            duration = res.getString("duration");
         }
      } catch (Exception var9) {
         var9.printStackTrace();
      }

      return duration;
   }

   public void userLoginLogs(String msidn, String videoId) {
      String query = "";

      try {
         if (videoId != null) {
            query = "insert into userloginlogs(msidn,portal,log_type,video_id) values('" + msidn + "','NDOTOSTREAM-STS','videoPlay','" + videoId + "')";
         } else {
            query = "insert into userloginlogs(msidn,portal,log_type) values('" + msidn + "','NDOTOSTREAM-STS','Login')";
         }

         Statement prep = Loader.contentConn.createStatement();
         prep.execute(query);
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   public String getDuration(Connection con, Parameter objparameter) {
      JSONObject main = new JSONObject();

      try {
         String getDuration = this.getTimeDurationLogging(con, objparameter, "hollywood");
         main.put("duration", getDuration);
         this.obj.put("status", "1");
         this.obj.put("data", main);
         this.obj.put("message", "success");
         this.response = this.obj.toString();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return this.response;
   }

   public String addUserDetail(Parameter param, Connection contentConn) {
      String status = "1";
      System.out.println(String.valueOf(param.getAni()) + param.getResult());
      if (param.getResult().equalsIgnoreCase("yes")) {
         status = "0";
      }

      String query1 = "select count(1) as cnt from tbl_video_usr where ani='" + param.getAni() + "' ";
      String query = "insert into tbl_video_usr (ani,ageType,status) values('" + param.getAni() + "','" + param.getResult() + "'," + status + ")";

      try {
         PreparedStatement ps = contentConn.prepareStatement(query);
         PreparedStatement ps1 = contentConn.prepareStatement(query1);
         ResultSet rs = ps1.executeQuery();
         int count = 0;
         if (rs.next()) {
            count = rs.getInt("cnt");
         }

         if (count == 0) {
            ps.executeUpdate();
         }
      } catch (SQLException var10) {
         var10.printStackTrace();
      }

      return status;
   }

   public static long insertUserSocialMod(String mod, String provider, String pubid, String clickid) {
      if (provider.length() == 0) {
         provider = "social";
      }

      try {
         String query = "insert into tbl_conv_logs(clickid,createddatetime,modifieddatetime,provider,service,mode,pubid) values (?,now(),now(),?,'videos',?,?)";
         PreparedStatement ps = Loader.NdotMTNConn.prepareStatement(query, 1);
         ps.setString(1, clickid);
         ps.setString(2, provider);
         ps.setString(3, mod);
         ps.setString(4, pubid);
         System.out.println("---------" + query);
         ps.executeUpdate();
         ResultSet rs = ps.getGeneratedKeys();
         if (rs.next()) {
            return rs.getLong(1);
         }

         ps.close();
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return 0L;
   }

   public static String getAgeStatusfromSubcat(String catid) {
      String age = null;

      try {
         String query = "select age from tbl_sub_cat where sub_cat_id='" + catid + "'";
         PreparedStatement ps = Loader.contentConn.prepareStatement(query);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
            age = rs.getString(1);
         }
      } catch (Exception var5) {
      }

      return age;
   }

   public String addShareLog(Parameter param, Connection contentConn) {
      String insertquery = "insert into sharelog(ani,mode,portal) values('" + param.getAni() + "','" + param.getMod() + "','" + param.getPortal() + "')";
      String result = insertquery;

      try {
         Statement st = contentConn.createStatement();
         st.execute(insertquery);
         result = "success";
      } catch (SQLException var6) {
         var6.printStackTrace();
         var6.getMessage();
      }

      return result;
   }

   public String addComment(Parameter param, Connection contentConn) {
      JSONObject jo = new JSONObject();
      String var4 = "0";

      try {
         String query = "insert into tbl_comment (name,comment,videoid,datetime,portal) values(?,?,?,now(),'mtn')";
         System.out.println(query);
         PreparedStatement ps = Loader.contentConn.prepareStatement(query);
         ps.setString(1, param.getAni());
         ps.setString(2, param.getMessage());
         ps.setString(3, param.getVideoid());
         int i = ps.executeUpdate();
         System.out.println("status  " + i);
         jo.put("status", i);
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return jo.toString();
   }

   public String getComment(Parameter param, Connection contentConn) {
      JSONObject jo = new JSONObject();

      try {
         JSONArray ja = new JSONArray();
         String query = "select comment from tbl_comment where videoid='" + param.getVideoid() + "' and portal='mtn'";
         PreparedStatement ps = Loader.contentConn.prepareStatement(query);
         System.out.println(query);
         ResultSet rs = ps.executeQuery();

         while(rs.next()) {
            ja.put(rs.getString(1));
         }

         jo.put("comment", ja);
      } catch (Exception var8) {
         var8.getStackTrace();
      }

      return jo.toString();
   }

   public String addTrackLogging(Connection Conn, Parameter param) {
      JSONObject jo = new JSONObject();

      try {
         String query = "insert into tbl_user_track (ani,portal) values('" + param.getAni() + "','" + param.getPortal() + "')";
         PreparedStatement ps = Conn.prepareStatement(query);
         System.out.println(query);
         int status = ps.executeUpdate();
         jo.put("status", status);
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return jo.toString();
   }

   public static void insertUserDevice(String agent, String ani) {
   }

   public static void insertUserDataUsage(Scrapmodel param) {
      try {
         String query = "insert into tbl_user_datausage (ani,pageurl,size) values('" + param.getAni() + "','" + param.getPageurl() + "','" + param.getSize() + "')";
         System.out.println(query);
         PreparedStatement ps = Loader.contentConn.prepareStatement(query);
         ps.executeUpdate();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static void insertUserDataUsage1(Scrapmodel param) {
      try {
         String query = "insert into tbl_user_datausage (ani,pageurl,size) values('" + param.getAni1() + "','" + param.getPageurl1() + "','" + param.getSize1() + "')";
         System.out.println(query);
         PreparedStatement ps = Loader.contentConn.prepareStatement(query);
         ps.executeUpdate();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public String addActivity(Connection conn, Parameter param) {
      JSONObject jo = new JSONObject();

      try {
         String query = "insert into tbl_user_activity (ani,portal,time,status) values('" + param.getAni() + "','" + param.getPortal() + "','" + param.getTime() + "','" + param.getStatus() + "')";
         PreparedStatement ps = conn.prepareStatement(query);
         System.out.println("User Activity ===> " + query);
         int status = ps.executeUpdate();
         jo.put("status", status);
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return jo.toString();
   }

   public static void checkToken() {
   }
}