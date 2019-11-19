package cn.hrk.spring.service.impl;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RestClientFactory {
    @Value("${spring.data.elasticsearch.host}")
    private String hostname;
    @Value("${spring.data.elasticsearch.port}")
    private int port;

    @Bean
    public RestHighLevelClient getRestHighLeveClient(){
        HttpHost httpHost=new HttpHost(hostname,port,"http");
        RestClientBuilder builder= RestClient.builder(httpHost).setMaxRetryTimeoutMillis(30*1000);
        return  new RestHighLevelClient(builder);
    }
}
