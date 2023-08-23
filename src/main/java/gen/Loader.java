/*    */ package gen;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.annotation.WebServlet;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @WebServlet({"/Loader"})
/*    */ public class Loader
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 16 */   public static Connection contentConn = null;
/* 17 */   public static Connection NdotMTNConn = null;
/* 18 */   public static Connection wpConnection = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void init() throws ServletException {
/* 30 */     contentConn = DbConnection.getDatabse();
/* 31 */     NdotMTNConn = DbConnection.getMTNDatabse();
/* 32 */     wpConnection = DbConnection.getWPDatabase();
/* 33 */     System.out.println("Db Connected in init first time");
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\Loader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */