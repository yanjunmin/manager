package top.westyle.manager.utils;

import java.util.UUID;

/**
 * shiro工具类
 * @author yjm
 * @date 2019-8-5 16:11:55
 */
public class ShiroUtil {
    /**
     * 加盐参数
     */
    private static final String hashAlgorithmName = "MD5";
    /**
     * 循环次数
     */
    private static final  int hashIterations = 1024;

    /**
     * 生成32位的随机盐值
     * @return
     */
    public static String createSalt(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
