/*     */ package gen.helper;
/*     */ 
/*     */ import com.Helper.GetDbMethods;
/*     */ import java.sql.ResultSet;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoUnit;
/*     */ import java.util.Date;
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
/*     */ public class Test
/*     */ {
/*     */   public static void main(String[] args) throws Exception {
/*  97 */     String s3 = "220127";
/*  98 */     s3 = "20" + s3;
/*  99 */     StringBuilder sb = new StringBuilder(s3);
/* 100 */     sb.insert(4, "-").insert(7, "-");
/* 101 */     String get = sb.toString();
/* 102 */     System.out.println("Final Get Date is " + get);
/*     */ 
/*     */     
/* 105 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 106 */     Date date = new Date();
/* 107 */     String now = sdf.format(date);
/*     */     
/* 109 */     System.out.println("Date Now is " + now);
/*     */ 
/*     */ 
/*     */     
/* 113 */     LocalDate l1 = LocalDate.parse(get);
/* 114 */     LocalDate l2 = LocalDate.parse(now);
/*     */     
/* 116 */     long diff = ChronoUnit.DAYS.between(l1, l2);
/* 117 */     System.out.println("Diff is " + diff);
/*     */     
/* 119 */     System.out.println();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void get_ext_id(String ext_id) {
/*     */     try {
/* 128 */       String query = "select count(1) as count from offnet_logging where ext_id='" + ext_id + "' ";
/* 129 */       ResultSet r = GetDbMethods.getRS(query);
/* 130 */       if (r.next()) {
/*     */         
/* 132 */         int count = r.getInt("count");
/* 133 */         if (count == 1) {
/*     */           
/* 135 */           System.out.println("ext_id present in db");
/*     */         }
/*     */         else {
/*     */           
/* 139 */           System.out.println("ext_id not present in db");
/*     */         } 
/*     */       } 
/* 142 */     } catch (Exception e) {
/*     */       
/* 144 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\helper\Test.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */