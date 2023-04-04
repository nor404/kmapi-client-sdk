package com.ktmo.kmapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 生成签名
 * @author ktmo
 * @date 2023/3/9 18:59
 */
public class SignUtils {
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body.toString() +"." + secretKey;
        return md5.digestHex(content);

    }
}
