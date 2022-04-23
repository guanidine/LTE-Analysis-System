package team.lte.bizservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"team.lte"})
public class ServiceBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBizApplication.class, args);
    }

}
