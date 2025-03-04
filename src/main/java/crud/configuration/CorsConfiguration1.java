//package crud.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfiguration1 {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // 모든 엔드포인트에 대해
//                        .allowedOrigins("http://localhost:5173") // React 앱 Origin 허용
//                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
//                        .allowedHeaders("*"); // 모든 헤더 허용
//            }
//        };
//    }
//}
