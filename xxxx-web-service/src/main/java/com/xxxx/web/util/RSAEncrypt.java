package com.xxxx.web.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {

    private static Map<Integer, String> keyMap = new HashMap<>();  //用于封装随机产生的公钥与私钥

    public static void main(String[] args) throws Exception {
        //生成公钥和私钥
//        genKeyPair();
//        //加密字符串
        String message = "123456";
//        System.out.println("随机生成的公钥为:" + keyMap.get(0));
//        System.out.println("随机生成的私钥为:" + keyMap.get(1));
        String messageEn = encrypt(message,"");
        System.out.println(message + "\t加密后的字符串为:" + messageEn);
//        String messageDe = decrypt(messageEn,keyMap.get(1));
//        System.out.println("还原后的字符串为:" + messageDe);
        String messageDe = decrypt("Zh0A+5SnUMAhKnLm4OVs/jTrpfW8tvGS/QzHIx5D8UPTs/gE4oOcko3He/h8Culqgsq83mPwU5CcFFCgzhRRpQRMK237qHreW9WcZuxtPY9cpxArvBfxqWO4JRY0WQes/1VnL+JynA4kk4WanBSK0qHfIB6AhFYhzFWemQd4RK4=", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIeNxO341D5NtSunj449tk2h41LjclatgDQpG/LsgG61yswjBB5caisZx2klXj1HUron52oYlzr2Z8ssE0fzvBTzxOgGaSdBg+eaj9g05ftb1WxfABCoTk68HnBplHn++vsVpsQCnaViI1QevVqDjKBO1Hr3SCizxR6rhlCmHN6VAgMBAAECgYAD4EfrThwzk+FEWExAkv8thR2M9zgDxn5N/4bvEVgy0jv0TmQFwjq+9MPA+/KP6gQxkoaJgB6C7xzUsHP0sMGGBRRyuR8CLTcArFgmkVd3TAGEkz8D+XyejVv5huQdVipYWrCC9WOViy2S+qzPf/O1JS+SikYPAtQtbqMrZP7uAQJBANhmr01Ye8XRTI+bERjW7Ju3lAqaPcAo36pIM2apBDzIYAiFtA3+KCGVxtAv+dN0huI4panlARW0gTHkkx/o3tUCQQCgW8oheApB44akHaKRiRiogk7Mqsei8T62yQDJUiggxcvQoYUtYIXkDfC43E/q8W0tq/18TE15BNTTb7z0iWDBAkB7NNOAJO2rbgO6C16Llup1qeqWssSPoHXfnGXvgDFSbf8HOyCNivPMU1szcu05MDIPoJLpjOG2OuQHxQzi8RHVAkEAnbYI0ufjdq/WMLxwsZ/bH4ehBkuEMm3/lveTDWz+yFSgQl1IxLskk4leUgFjnBa7oOqBUGke3MHyqsw/U2V1QQJAI/F0lwhAiQu3SJ177KbYLuItcS8a00Z+m1uCqczpCKh78YxsY9k5kaM1IkPX0RjPH7gqJMObZ2pVu1gTiNycqw==");
        System.out.println(messageDe);
    }

    /**
     * 随机生成密钥对
     *
     * @throws java.security.NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(0, publicKeyString);  //0表示公钥
        keyMap.put(1, privateKeyString);  //1表示私钥
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}
