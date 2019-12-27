package top.westyle.manager;

import com.cxytiandi.encrypt.util.AesEncryptUtils;

/**
 * 测试加密解密
 */
public class EncryTest {
    public static void main(String[] args) throws Exception {
        String content = "{\"userName\":\"yanjunminm\", \"password\":\"yanjunminm\"}";
        System.out.println("加密前：" + content);
        String encrypt = AesEncryptUtils.aesEncrypt(content, "abcdefyjm3456789");
        System.out.println(encrypt.length() + ":加密后：" + encrypt);
        String decrypt = AesEncryptUtils.aesDecrypt(encrypt, "abcdefyjm3456789");
        System.out.println("解密后：" + decrypt);
    }
}
