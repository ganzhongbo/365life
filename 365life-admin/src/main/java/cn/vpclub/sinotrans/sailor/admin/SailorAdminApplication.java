package cn.vpclub.sinotrans.sailor.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by chentao on 2018/7/11.
 */
@Configuration
@EnableDiscoveryClient
@SpringBootApplication
@EnableWebMvc
@EnableFeignClients
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SailorAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SailorAdminApplication.class, args);
    }
}
