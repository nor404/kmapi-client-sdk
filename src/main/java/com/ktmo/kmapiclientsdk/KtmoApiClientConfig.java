package com.ktmo.kmapiclientsdk;

import com.ktmo.kmapiclientsdk.client.KtmoClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ktmo
 * @date 2023/3/9 23:23
 */
@Configuration
@ConfigurationProperties("kmapi.client")
@Data
@ComponentScan
public class KtmoApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public KtmoClient KmApiClient(){
       return new KtmoClient(accessKey,secretKey);


    }

}
