package com.lynuc.util;



import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;  

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;  

 

public class EncryptUtils {  

 

  /// <summary>  

  /// 3des����  

  /// </summary>  

  /// <param name="value">�������ַ���</param>  

  /// <param name="key">ԭʼ��Կ�ַ���</param>  

  /// <returns></returns>  

  public static String Decrypt3DES(String value, String key) throws Exception {  

      byte[] b = decryptMode(GetKeyBytes(key), Base64.decode(value));  

      return new String(b);  

  }  

 

  /// <summary>  

  /// 3des����  

  /// </summary>  

  /// <param name="value">�������ַ���</param>  

  /// <param name="strKey">ԭʼ��Կ�ַ���</param>  

  /// <returns></returns>  

  public static String Encrypt3DES(String value, String key) throws Exception {  

      String str = byte2Base64(encryptMode(GetKeyBytes(key), value.getBytes()));  

      return str;  

  }  

 

  //����24λ��������byteֵ,���ȶ�ԭʼ��Կ��MD5��hashֵ������ǰ8λ���ݶ�Ӧ��ȫ��8λ  

  public static byte[] GetKeyBytes(String strKey) throws Exception {  

      if (null == strKey || strKey.length() < 1)  

          throw new Exception("key is null or empty!");  

 

      java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5");  

      alg.update(strKey.getBytes());  

      byte[] bkey = alg.digest();  

      System.out.println("md5key.length=" + bkey.length);  

      System.out.println("md5key=" + byte2hex(bkey));  

      int start = bkey.length;  

      byte[] bkey24 = new byte[24];  

      for (int i = 0; i < start; i++) {  

          bkey24[i] = bkey[i];  

      }  

      for (int i = start; i < 24; i++) {//Ϊ����.net16λkey����  

          bkey24[i] = bkey[i - start];  

      }  

 

      System.out.println("byte24key.length=" + bkey24.length);  

      System.out.println("byte24key=" + byte2hex(bkey24));  

      return bkey24;  

  }  

 

  private static final String Algorithm = "DESede"; //���� �����㷨,���� DES,DESede,Blowfish         

 

  //keybyteΪ������Կ������Ϊ24�ֽ�  

  //srcΪ�����ܵ����ݻ�������Դ��    

  public static byte[] encryptMode(byte[] keybyte, byte[] src) {  

      try {  

          //������Կ  

          SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); //����   

          Cipher c1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");  
          IvParameterSpec iv = new IvParameterSpec("kjshdfku".getBytes());

          c1.init(Cipher.ENCRYPT_MODE, deskey,iv);  

          return c1.doFinal(src);  

     } catch (java.security.NoSuchAlgorithmException e1) {  

          e1.printStackTrace();  

      } catch (javax.crypto.NoSuchPaddingException e2) {  

          e2.printStackTrace();  

      } catch (java.lang.Exception e3) {  

          e3.printStackTrace();  

      }  

      return null;  

  }  

 

  //keybyteΪ������Կ������Ϊ24�ֽ�    

  //srcΪ���ܺ�Ļ�����  

  public static byte[] decryptMode(byte[] keybyte, byte[] src) {  

      try { //������Կ     

          SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);  

          //����       

          Cipher c1 = Cipher.getInstance(Algorithm);  

          c1.init(Cipher.DECRYPT_MODE, deskey);  

          return c1.doFinal(src);  

      } catch (java.security.NoSuchAlgorithmException e1) {  

          e1.printStackTrace();  

      } catch (javax.crypto.NoSuchPaddingException e2) {  

          e2.printStackTrace();  

      } catch (java.lang.Exception e3) {  

          e3.printStackTrace();  

      }  

      return null;  

  }  

 

  //ת����base64����  

  public static String byte2Base64(byte[] b) {  

      return Base64.encode(b);  

  }  

 

  //ת����ʮ�������ַ���    

  public static String byte2hex(byte[] b) {  

      String hs = "";  

      String stmp = "";  

      for (int n = 0; n < b.length; n++) {  

          stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  

          if (stmp.length() == 1)  

              hs = hs + "0" + stmp;  

          else  

              hs = hs + stmp;  

          if (n < b.length - 1)  

              hs = hs + ":";  

      }  

      return hs.toUpperCase();  

  }  

}  

 