package team.lte.servicebase.swagger;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${springfox.documentation.enabled}")
    private boolean isEnabled;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.OAS_30).enable(isEnabled).apiInfo(webApiInfo()).select()
            // 请不要用@ApiOperation，并不扫描它
            .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
            // .apis(RequestHandlerSelectors.basePackage("team.lte"))
            .build();

    }

    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder().title("网站-LTE 网络干扰分析系统API文档").description("本文档描述了LTE 网络干扰分析系统微服务接口定义")
            .contact(new Contact("彭笑凯，刘嘉伟，张乐天", "https://www.bupt.edu.cn/", "")).version("1.0").build();
    }

}
