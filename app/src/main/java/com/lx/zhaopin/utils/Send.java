package com.lx.zhaopin.utils;

import java.util.Map;

/**
 * 作者：齐潮
 * 创建日期：2017年1月9日
 * 类说明：用于发送信息和对信息的接收以及验证
 */
public class Send {

    /**
     * 表示不需要登陆就能访问的接口，这里只进行没有登陆时默认token的认证，如果传入了登陆后的token会不通过
     */
    public final static int NO_LOGIN_SIGN = 1;

    /**
     * 表示需要登陆才能访问的接口，必须传入登陆后的token
     */
    public final static int MUST_LOGIN_SIGN = 2;

    /**
     * 表示可以传未登陆或者登陆的token，均可以通过验证
     */
    public final static int ALL_SIGN = 3;

    /**
     * 用于表示userId的字段
     */
    public final static String USERID_NAME = "userid";

    /**
     * 用于保存签名的字段
     */
    public final static String SIGN_NAME = "sign";

    /**
     * 用于保存最终json信息的字段，直接可以用来发送
     */
    public final static String JSON_NAME = "content";

    /**
     * 用于表示时间搓的字段
     */
    public final static String TIMESTAMP_NAME = "timestamp";

    /**
     * 当访问的接口不需要登陆时，需要传入这个token
     */
//    public final static String DEFAULT_TOKEN = "c07c337f88bc477c8cbe6c404208d764";
    public final static String DEFAULT_TOKEN = "";
    public final static String TOKEN_KEY = "defaultToken";

    /**
     * 双方约定的签名密钥
     */
    private final static String SIGN_KEY = "1e86d6bd10de48b59d80afdb6c98ddaf";

    /**
     * 双方约定的des解密密钥
     */
    public final static String DES_KEY = "a2578c19dd824a9aa8ec38f6f17e1edb";

    /**
     * redis客户端
     */
//	@Autowired
//	private static RedisClient redis;


    /**
     * 验证签名。这个方法自动去除map里的签名和时间搓字段。
     * 所有的情况里，map中必须有时间搓字段Send.TIMESTAMP_NAME和签名字段Send.SIGN_NAME。
     * 当选择的类型是Send.MUST_LOGIN_SIGN或者Send.ALL_SIGN时，map中必须携带userId，且userId的字段名是Send.USERID_NAME。
     * @param map
     * @param type
     * @return
     */
    /**
     * 验证签名。这个方法自动去除map里的签名和时间搓字段。
     * map中必须有时间搓字段Send.TIMESTAMP_NAME和签名字段Send.SIGN_NAME。
     *
     * @param map
     * @return
     */
    public final static boolean validateSign(Map<String, Object> map, String token) {
        boolean result = false;
        long timestamp = 0L;
        String oldSign = null;
        try {
            timestamp = Long.parseLong(map.get(Send.TIMESTAMP_NAME).toString());
            oldSign = map.get(Send.SIGN_NAME).toString();
            map.remove(Send.TIMESTAMP_NAME);
            map.remove(Send.SIGN_NAME);
//            String systemSign = Send.createSign(map, token, timestamp);
//            if (systemSign.equals(oldSign))
//                result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
