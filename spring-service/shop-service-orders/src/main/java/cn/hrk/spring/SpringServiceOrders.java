package cn.hrk.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringCloudApplication
@EnableDiscoveryClient
public class SpringServiceOrders {
    public static void main(String[] args) {
        SpringApplication.run(SpringServiceOrders.class,args);
    }
}
