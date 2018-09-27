package cn.vpclub.sinotrans.sailor.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author likq
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableWebMvc
@EnableDiscoveryClient
@ComponentScan(basePackages = {"cn.vpclub.sinotrans"})
@MapperScan("cn.vpclub.sinotrans.sailor.server.dao")
public class SailorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SailorServerApplication.class, args);
    }
}
