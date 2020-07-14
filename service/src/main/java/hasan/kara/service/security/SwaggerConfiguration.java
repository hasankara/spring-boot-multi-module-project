//package hasan.kara.service.security;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContext;
//
//import io.swagger.v3.oas.annotations.info.Contact;
//
//@Configuration
//@EnableSwagger2
//@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
//public class SwaggerConfiguration {
//
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
//    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);
//
//    @Bean
//    public Docket swaggerSpringfoxDocket() {
//        log.debug("Starting Swagger");
//        Contact contact = new Contact(
//            "Matyas Albert-Nagy",
//            "https://justrocket.de",
//            "matyas@justrocket.de");
//
//        List<VendorExtension> vext = new ArrayList<>();
//        ApiInfo apiInfo = new ApiInfo(
//            "Backend API",
//            "This is the best stuff since sliced bread - API",
//            "6.6.6",
//            "https://justrocket.de",
//            contact,
//            "MIT",
//            "https://justrocket.de",
//            vext);
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo)
//            .pathMapping("/")
//            .apiInfo(ApiInfo.DEFAULT)
//            .forCodeGeneration(true)
//            .genericModelSubstitutes(ResponseEntity.class)
//            .ignoredParameterTypes(Pageable.class)
//            .ignoredParameterTypes(java.sql.Date.class)
//            .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
//            .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
//            .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
//            .securityContexts(Lists.newArrayList(securityContext()))
//            .securitySchemes(Lists.newArrayList(apiKey()))
//            .useDefaultResponseMessages(false);
//
//        docket = docket.select()
//            .paths(regex(DEFAULT_INCLUDE_PATTERN))
//            .build();
//        watch.stop();
//        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
//        return docket;
//    }
//
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//            .securityReferences(defaultAuth())
//            .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
//            .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//            = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Lists.newArrayList(
//            new SecurityReference("JWT", authorizationScopes));
//    }
//}