package cn.hrk.spring;

import feign.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;


@SpringBootApplication(exclude={
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,})
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
public class PortalMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalMainApplication.class, args);
    }

    @Bean
    public static Request.Options requestoptions(ConfigurableEnvironment env) {
        return new Request.Options(10000, 10000);

    }
}
