/*     */ package gen.common;
/*     */ 
/*     */ import gen.Scrapmodel;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
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
/*     */ public class ScrapingPage
/*     */ {
/*     */   public static Scrapmodel getSize(Scrapmodel param) throws IOException {
/*  31 */     double size = 0.0D;
/*     */ 
/*     */     
/*  34 */     System.out.println("Page url :" + param.getPageurl());
/*     */     
/*  36 */     for (int i = 0; i < param.getJsonarray().length(); i++) {
/*     */       
/*  38 */       String imgurl = (String)param.getJsonarray().get(i);
/*  39 */       size += getimageSize(imgurl);
/*     */     } 
/*     */ 
/*     */     
/*  43 */     param.setSize(String.valueOf(size));
/*  44 */     return param;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getimageSize(String imgurl) {
/*  53 */     double kb = 0.0D;
/*     */     
/*     */     try {
/*  56 */       URL ur = new URL(imgurl);
/*  57 */       URLConnection uconn = ur.openConnection();
/*     */       
/*  59 */       kb = (uconn.getContentLength() / 1024);
/*  60 */       double d = kb / 1024.0D;
/*     */     }
/*  62 */     catch (IOException e) {
/*     */       
/*  64 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  67 */     return kb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Scrapmodel getSize1(Scrapmodel param) throws IOException {
/*  76 */     double size = 0.0D;
/*     */ 
/*     */     
/*  79 */     System.out.println("Page url :" + param.getPageurl());
/*     */     
/*  81 */     for (int i = 0; i < param.getJsonarray1().length(); i++) {
/*     */       
/*  83 */       String imgurl = (String)param.getJsonarray1().get(i);
/*  84 */       size += getimageSize1(imgurl);
/*     */     } 
/*     */ 
/*     */     
/*  88 */     param.setSize1(String.valueOf(size));
/*  89 */     return param;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getimageSize1(String imgurl) {
/*  98 */     double kb = 0.0D;
/*     */     
/*     */     try {
/* 101 */       URL ur = new URL(imgurl);
/* 102 */       URLConnection uconn = ur.openConnection();
/*     */       
/* 104 */       kb = (uconn.getContentLength() / 1024);
/* 105 */       double d = kb / 1024.0D;
/*     */     }
/* 107 */     catch (IOException e) {
/*     */       
/* 109 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 112 */     return kb;
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\common\ScrapingPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */