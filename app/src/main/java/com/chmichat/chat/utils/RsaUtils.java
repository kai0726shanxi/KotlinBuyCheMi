package com.chmichat.chat.utils;

import android.util.Log;


import net.iharder.Base64;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

public class RsaUtils {

    private static final String ALGORITHM = "RSA";

    public static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC31uA586rRz6IAc6j8LtxLUDuk4IIo+recDFyOzOa+qAu+PUJWoyCpy6/r2+AB2mI9llSKMg+LSObzFsVbQy0VdB0eN2aeAT3Cw2uFTBKdCbuVPKjWl9O8YOSAn+NTd4eLnMhducK8DcAtugAvomLLy4a6rTtW89F+48FllgHk1wIDAQAB";
    public static final String DEFAULT_PUBLIC_GAME_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCm5O7F+7OZ79ycI5WqfrHhPkcTIqFkprl1Cxpiww3+I/EeVovWcPjZQJLnkJUOqU1rwUK7KSUyaive2MpNAaoEGOTdai6PdEeziDtEfFyXeWm3GNhoVi9ljBBlITwxjxQen99AV3fEv3le0jiAc8Y1tU/Ozyfy2owBL3zHi93RZwIDAQAB";
    public static final String DEFAULT_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALgv/syFH337KzC29KvR0p6cP+glRqjDYAQno5ifafXZjgf1EhBjZblKv+HiLAzNBOlYU1PnLuOOkZj6pg1A5HUZLpsbYa5Mwr1bUHALjXLaB3THCpZX51/b5L14erGo52Jv/j/63YljEtMm8ALmkY8S+3fPxFeY7ya+2VXMEtplAgMBAAECgYAguvauZWGpQ37zUy+7cLfa061PlYAu8TkYw+qAbqOnupdQtq4VF3S2LqBWhZiKVcxvovB70nM0oNsisjfb1xJBpyfDBFug7d+y2f8yr6aTOezoY5DBYEF3Svg9Kp9ra+vvAYX/7fh+tHCU0HOvp0z8ikZiRSWZaQ+3A2GiCIJrwQJBAPKVji89hGAMEWLJJFZaPiLBqZUwR2W/rp7Ely5ddKfjcosHhggHfOb71BnrMOm0h4S85Gx6a87n9R2To0c51q0CQQDCX6yYdt/9JGORyNSXfzMfSZyVOrMpIo77R0YwKa3UOwwLA56l2Lc4AYO10/lyAyZCKse2/5D9ZZUB7xoYEmGZAkB8MEJVPuoY/bSc3RqENrjetERsAwZaObJcx4oaC3AgTxmhwV1FmQfBfKTODBDDZE+Ijedm/ZlZmHhtBtstKJgVAkBKma/DgHRtUscIT90QHBjB3F3FhJb4pbPcyzksCQMXXmY73/LG0ktXqnUjlyy4zm6jnIm0OZgrOQ6chGkubfeZAkBMCGF2tPfEJh8XODOvlw5ADnUiq+Qe/abcpKowkiT9zP+rYT9XJAx7QxChjdwTZb6ahnJY1+ny1emEHUOs2fm8";
    public static RSAPublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey.getBytes());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey.getBytes());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }





    /**
     * 公钥加密
     * @return
     */
    public static String publicKeyEncryption(String data) throws Exception {

        /*
         * 加密过程
         */
        RSAPublicKey publicKey =getPublicKey(DEFAULT_PUBLIC_KEY);

        Cipher cipher1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher1.init(Cipher.ENCRYPT_MODE, publicKey);

        Log.i("dfc", "publicKeyEncryption2: "+data);

        byte[] output= cipher1.doFinal(data.getBytes());



        Log.i("dfc", "publicKeyEncryption2: "+new String(Base64.encodeBytes(output)));


        return new String(Base64.encodeBytes(output));

    }

    /**
     * 游戏加密
     * @return
     */
    public static String publicKeyGameEncryption(String data) throws Exception {

        /*
         * 加密过程
         */
        RSAPublicKey publicKey =getPublicKey(DEFAULT_PUBLIC_GAME_KEY);

        Cipher cipher1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher1.init(Cipher.ENCRYPT_MODE, publicKey);

        Log.i("dfc", "publicKeyEncryption2: "+data);

        byte[] output= cipher1.doFinal(data.getBytes());



        Log.i("dfc", "publicKeyEncryption2: "+new String(Base64.encodeBytes(output)));


        return new String(Base64.encodeBytes(output));

    }

    /**
     * 解密
     * @return
     */
    public static String PrivateKeyDecrypt(String data) throws Exception {

        RSAPrivateKey privateKey = RsaUtils.getPrivateKey(DEFAULT_PRIVATE_KEY);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] output11 = cipher.doFinal(Base64.decode(data));

        System.out.println("明文:" + new String(output11));
        return new String(output11);
    }


}
