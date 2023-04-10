package com.wang.jk.common.cfg;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Configuration
public class SwaggerCfg {
    @Autowired
    private Environment environment;

    @Bean
    public Docket sysDocket() {
        return groupDocket("/(sys.+)",
                "01_系统", "用户，角色，资源");
    }

    @Bean
    public Docket metadataDocket() {
        return groupDocket("/(dict.+|plateRegions.+)",
                "02_元数据", "数据字典类型，数据字典条目，省份，城市");
    }

    @Bean
    public Docket examDocket() {
        return groupDocket("/(exam.+)",
                "03_考场", "考场，科2科3");
    }

    @Bean
    public Docket testDocket() {
        return groupDocket("/(test.+)",
                "00_测试", "测试");
    }

    public Docket basicDocket() {
        boolean enable = environment.acceptsProfiles(Profiles.of("dev", "test"));
        RequestParameter tokenParam = new RequestParameterBuilder()
                .description("用户登录令牌")
                .in(ParameterType.HEADER)
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .globalRequestParameters(Arrays.asList(tokenParam))
                .enable(enable)
                .ignoredParameterTypes(
                        HttpSession.class,
                        HttpServletRequest.class,
                        HttpServletResponse.class);
    }

    public Docket groupDocket(String regex, String title, String desc) {
        return basicDocket().groupName(title)
                .apiInfo(apiInfo(title, desc))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex(regex))
                .build();
    }

    private ApiInfo apiInfo(String title, String desc) {
        return new ApiInfoBuilder()
                .title(title)
                .version("1.0.0")
                .description(desc)
                .build();
    }
}
