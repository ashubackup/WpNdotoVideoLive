/*     */ package gen;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Iterator;
/*     */ import javolution.util.FastMap;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ public class Parameter
/*     */ {
/*  12 */   public String response = " {\"status\":\"0\",\"data\":{},\"message\":\"Error\"}"; private String videoid; private String ani; private String action; private String category; private String id; private String duration; private String percentage; private String channel; private String data; private String name; private String result;
/*     */   private String status;
/*     */   
/*     */   public String getTime() {
/*  16 */     return this.time;
/*     */   }
/*     */   private String state; private String mod; private String portal; private String message; private String pageurl; private String html; private String line; private String size; private String page; private String time; private JSONArray jsonarray; private String imgurl;
/*     */   public void setTime(String time) {
/*  20 */     this.time = time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSONArray getJsonarray() {
/*  28 */     return this.jsonarray;
/*     */   }
/*     */   
/*     */   public void setJsonarray(JSONArray jsonarray) {
/*  32 */     this.jsonarray = jsonarray;
/*     */   }
/*     */   
/*     */   public String getImgurl() {
/*  36 */     return this.imgurl;
/*     */   }
/*     */   
/*     */   public void setImgurl(String imgurl) {
/*  40 */     this.imgurl = imgurl;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPage() {
/*  46 */     return this.page;
/*     */   }
/*     */   
/*     */   public void setPage(String page) {
/*  50 */     this.page = page;
/*     */   }
/*     */   public String getSize() {
/*  53 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(String size) {
/*  59 */     this.size = size;
/*     */   }
/*     */   
/*     */   public String getLine() {
/*  63 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(String line) {
/*  67 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getPageurl() {
/*  71 */     return this.pageurl;
/*     */   }
/*     */   
/*     */   public void setPageurl(String pageurl) {
/*  75 */     this.pageurl = pageurl;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHtml() {
/*  81 */     return this.html;
/*     */   }
/*     */   
/*     */   public void setHtml(String html) {
/*  85 */     this.html = html;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/*  89 */     return this.message;
/*     */   }
/*     */   
/*     */   public void setMessage(String message) {
/*  93 */     this.message = message;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/*  97 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 101 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getState() {
/* 105 */     return this.state;
/*     */   }
/*     */   
/*     */   public String getMod() {
/* 109 */     return this.mod;
/*     */   }
/*     */   
/*     */   public void setMod(String mod) {
/* 113 */     this.mod = mod;
/*     */   }
/*     */   
/*     */   public String getPortal() {
/* 117 */     return this.portal;
/*     */   }
/*     */   
/*     */   public void setPortal(String portal) {
/* 121 */     this.portal = portal;
/*     */   }
/*     */   
/*     */   public void setState(String state) {
/* 125 */     this.state = state;
/*     */   }
/*     */   
/*     */   public String getResult() {
/* 129 */     return this.result;
/*     */   }
/*     */   
/*     */   public void setResult(String result) {
/* 133 */     this.result = result;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 137 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 141 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getData() {
/* 145 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(String data) {
/* 149 */     this.data = data;
/*     */   }
/*     */   
/*     */   public String getChannel() {
/* 153 */     return this.channel;
/*     */   }
/*     */   
/*     */   public void setChannel(String channel) {
/* 157 */     this.channel = channel;
/*     */   }
/*     */   
/*     */   public String getPercentage() {
/* 161 */     return this.percentage;
/*     */   }
/*     */   
/*     */   public void setPercentage(String percentage) {
/* 165 */     this.percentage = percentage;
/*     */   }
/*     */   
/*     */   public String getId() {
/* 169 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 173 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getDuration() {
/* 177 */     return this.duration;
/*     */   }
/*     */   
/*     */   public void setDuration(String duration) {
/* 181 */     this.duration = duration;
/*     */   }
/*     */   
/*     */   public String getAction() {
/* 185 */     return this.action;
/*     */   }
/*     */   
/*     */   public void setAction(String action) {
/* 189 */     this.action = action;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/* 193 */     return this.category;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCategory(String category) {
/* 198 */     this.category = category;
/*     */   }
/*     */   
/*     */   public String getVideoid() {
/* 202 */     return this.videoid;
/*     */   }
/*     */   
/*     */   public void setVideoid(String videoid) {
/* 206 */     this.videoid = videoid;
/*     */   }
/*     */   
/*     */   public String getAni() {
/* 210 */     return this.ani;
/*     */   }
/*     */   
/*     */   public void setAni(String ani) {
/* 214 */     this.ani = ani;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setField(String quaryString, Parameter ObjParam) throws Exception {
/* 221 */     FastMap params = new FastMap();
/* 222 */     JSONObject jObject = new JSONObject(quaryString);
/* 223 */     Iterator<?> keys = jObject.keys();
/* 224 */     while (keys.hasNext()) {
/* 225 */       String key = (String)keys.next();
/* 226 */       params.put(key, jObject.get(key).toString().trim());
/*     */     } 
/* 228 */     Iterator<String> iterator = params.keySet().iterator();
/* 229 */     Class<?> clazz = ObjParam.getClass();
/*     */     
/* 231 */     while (iterator.hasNext()) {
/*     */ 
/*     */       
/*     */       try {
/* 235 */         String key = iterator.next();
/* 236 */         Field field = clazz.getDeclaredField(key);
/* 237 */         Class<?> type = field.getType();
/* 238 */         String Fieldtype = type.getCanonicalName();
/* 239 */         field.setAccessible(true);
/* 240 */         if ("int".equalsIgnoreCase(Fieldtype) || " java.lang.Integer".equalsIgnoreCase(Fieldtype)) {
/* 241 */           field.set(ObjParam, new Integer((String)params.get(key)));
/* 242 */         } else if ("long".equalsIgnoreCase(Fieldtype) || "java.lang.Long".equalsIgnoreCase(Fieldtype)) {
/* 243 */           field.set(ObjParam, new Long((String)params.get(key)));
/* 244 */         } else if ("double".equalsIgnoreCase(Fieldtype) || " java.lang.Double".equalsIgnoreCase(Fieldtype)) {
/* 245 */           field.set(ObjParam, new Integer((String)params.get(key)));
/* 246 */         } else if ("java.lang.String".equalsIgnoreCase(Fieldtype)) {
/* 247 */           field.set(ObjParam, params.get(key));
/*     */         } 
/* 249 */         Object object = field.get(ObjParam);
/*     */       }
/* 251 */       catch (Exception e) {
/*     */         
/* 253 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     params = null;
/* 266 */     iterator = null;
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\Parameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */