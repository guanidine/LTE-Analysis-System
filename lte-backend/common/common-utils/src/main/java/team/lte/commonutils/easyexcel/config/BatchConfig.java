package team.lte.commonutils.easyexcel.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.lte.commonutils.easyexcel.injector.CustomizedSqlInjector;

@Configuration
@MapperScan(value = "team.lte.*.mapper")
public class BatchConfig {

    /**
     * 自定义SQL注入器
     */
    @Bean
    public CustomizedSqlInjector customizedSqlInjector() {
        return new CustomizedSqlInjector();
    }

}
