package com.ktmo.kmapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.body.RequestBody;
import cn.hutool.json.JSONUtil;
import com.ktmo.kmapiclientsdk.model.User;


import java.util.HashMap;
import java.util.Map;

import static com.ktmo.kmapiclientsdk.utils.SignUtils.getSign;

/**
 * @author ktmo
 * @date 2023/2/27 13:34
 * 调用第三方客户端
 */
public class KtmoClient {

    private String accessKey;
    private String secretKey;
    private static final  String GATEWAY_HOST="http://127.0.0.1:8090/";

    public KtmoClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getName(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result3 = HttpUtil.get(GATEWAY_HOST+"api/name/", paramMap);
        System.out.println("客户端 result3 = " + result3);
        return result3;
    }

    public String getNameByPsot(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result3 = HttpUtil.post(GATEWAY_HOST+"api/name/", paramMap);
        System.out.println("客户端 result3 = " + result3);
        return result3;
    }



    private Map<String,String> getHeadersMap(String body){
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("accessKey",accessKey);
        //不能发给后端
        //hashMap.put("secretKey",secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() /1000));
        hashMap.put("sign", getSign(body,secretKey));
        return hashMap;
    }



    public String getUserNameByPsot(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse execute = HttpRequest.post(GATEWAY_HOST+"api/name/user")
                .addHeaders(getHeadersMap(json))
                .body(json)
                .execute();

        System.out.println(execute.getStatus());
        System.out.println("状态码" + execute.getStatus());
        String body = execute.body();
        System.out.println("body = " + body);

        return execute.body();
    }

}
