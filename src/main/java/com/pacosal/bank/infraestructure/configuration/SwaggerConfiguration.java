package com.pacosal.bank.infraestructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

  @Bean
  public Docket serviceApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.pacosal.bank"))
      .paths(regex("/*.*"))
      .build()
      .apiInfo(appMetaData());
  }

  private ApiInfo appMetaData() {
    return new ApiInfoBuilder()
      .title("Account Service REST API")
      .description("Account Service")
      .version("1.0.0")
      .license("Any Licence")
      .contact(new Contact("Tugce Konuklar",
        "https://www.linkedin.com/in/tugce-konuklar/",
        "tkonuklar@gmail.com"))
      .contact(new Contact("Paco Salazar", "https://www.linkedin.com/in/francisco-salazar-rodr%C3%ADguez-2616b020/", "pacosal@gmail.com"))
      .build();
  }
}
