/*    */ package gen.common;
/*    */ 
/*    */ import gen.DataCollector;
/*    */ import gen.Loader;
/*    */ import gen.Parameter;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestAge
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 19 */   Parameter objParameter = new Parameter();
/* 20 */   DataCollector coll = new DataCollector();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 29 */     this.objParameter.setAni(request.getParameter("ani"));
/* 30 */     this.objParameter.setResult(request.getParameter("result"));
/* 31 */     String result = this.coll.addUserDetail(this.objParameter, Loader.contentConn);
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\common\TestAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */