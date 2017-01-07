package com.lynuc.util;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
 
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
 
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
/**
* ʹ��DES���ܺͽ��ܵķ���
* 
* @�޸���  С�ϻ�2
* @author:azhong 
* @change:exmorning
* */
public class CryptoTools {

   private final byte[] DESkey = "983247kj".getBytes("UTF-8");// ������Կ����ȥ
   //private final byte[] DESIV = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};// ������������ȥ
   private final byte[] DESIV="kjshdfku".getBytes("UTF-8");
   private AlgorithmParameterSpec iv = null;// �����㷨�Ĳ����ӿڣ�IvParameterSpec������һ��ʵ��
   private Key key = null;

   public  CryptoTools() throws Exception {
       DESKeySpec keySpec = new DESKeySpec(DESkey);// ������Կ����
       iv = new IvParameterSpec(DESIV);// ��������
       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// �����Կ����
       key = keyFactory.generateSecret(keySpec);// �õ���Կ����
   }

   public String encode(String data) throws Exception {
       Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// �õ����ܶ���Cipher
       enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// ���ù���ģʽΪ����ģʽ��������Կ������
       byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
       BASE64Encoder base64Encoder = new BASE64Encoder();
       return base64Encoder.encode(pasByte);
   }

   public String decode(String data) throws Exception {
       Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
       deCipher.init(Cipher.DECRYPT_MODE, key, iv);
       BASE64Decoder base64Decoder = new BASE64Decoder();

       byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));

       return new String(pasByte, "UTF-8");
   }
   public static void main(String[] args) {
       try {
           String test = "password";
           CryptoTools des = new CryptoTools();//�Զ�����Կ
           System.out.println("����ǰ���ַ���"+test);
           System.out.println("���ܺ���ַ���"+des.encode(test));
           System.out.println("���ܺ���ַ���"+des.decode(des.encode(test)));
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}