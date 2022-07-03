package team.lte.servicebase.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import team.lte.commonutils.Constants;

@Configuration
public class OpenApiConfig {

    private static final String HEADER_NAME = Constants.TOKEN_HEADER;
    private static final String BASE_PACKAGE = "team.lte";

    @Bean
    public GroupedOpenApi baseGroup() {
        return GroupedOpenApi.builder().group("basic").addOperationCustomizer(((operation, handlerMethod) -> {
            operation.addSecurityItem(new SecurityRequirement().addList(HEADER_NAME));
            return operation;
        })).packagesToScan(BASE_PACKAGE).build();
    }

    @Bean
    public OpenAPI customOpenApi() {
        Components components = new Components();
        components.addSecuritySchemes(HEADER_NAME, new SecurityScheme().type(SecurityScheme.Type.APIKEY).scheme("basic")
            .name(HEADER_NAME).in(SecurityScheme.In.HEADER).description("Header"));
        return new OpenAPI().components(components).info(apiInfo());
    }

    private Info apiInfo() {
        Contact contact = new Contact();
        contact.setName("彭笑凯，刘嘉伟，张乐天");
        contact.setUrl("https://gitee.com/pengxiaokay/lte-analysis-system/");
        return new Info().title("网站-LTE 网络干扰分析系统API文档").version("1.0").contact(contact)
            .description("本文档描述了LTE 网络干扰分析系统微服务接口定义")
            .license(new License().name("GUN GPLv3").url("https://springdoc.org"));
    }

}
