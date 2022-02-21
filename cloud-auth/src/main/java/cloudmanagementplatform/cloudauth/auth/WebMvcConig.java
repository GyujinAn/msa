//package cloudmanagementplatform.cloudauth.auth;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author agj017@gmail.com
// * @since 2022/02/08
// */
//@Configuration
//public class WebMvcConig implements WebMvcConfigurer {
//    private static final long MAX_AGE_SECONDS = 3600;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080/","http://localhost:9000/")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true)
//                .maxAge(MAX_AGE_SECONDS);
//    }
//}
