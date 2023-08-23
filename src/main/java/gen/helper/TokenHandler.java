/*    */ package gen.helper;
/*    */ 
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.Base64;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.spec.IvParameterSpec;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TokenHandler
/*    */ {
/*    */   public static String decrypt(String key, String encrypt) throws Exception {
/* 16 */     byte[] secretKey = key.getBytes();
/* 17 */     SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");
/*    */     
/* 19 */     byte[] iv = "12345678".getBytes();
/* 20 */     IvParameterSpec ivSpec = new IvParameterSpec(iv);
/*    */     
/* 22 */     Cipher decryptCipher = Cipher.getInstance("TripleDES/CBC/NoPadding");
/* 23 */     decryptCipher.init(2, secretKeySpec, ivSpec);
/*    */     
/* 25 */     byte[] decode = Base64.getDecoder().decode(encrypt);
/*    */     
/* 27 */     byte[] decryptedMessageBytes = decryptCipher.doFinal(decode);
/*    */     
/* 29 */     String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
/*    */     
/* 31 */     return decryptedMessage;
/*    */   }
/*    */ }


/* Location:              D:\@shu\Data\Office\download projects\wp-ndotovideo\WEB-INF\classes\!\gen\helper\TokenHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */