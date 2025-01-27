package org.launchcode.taskcrusher.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class WebConfig {

    private static final long MAX_AGE = 3600L;
//    private static final int CORS_FILTER_ORDER = -102;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //<-Accepts frontend credentials
        config.addAllowedOrigin("http://localhost:3000"); //<-React URL
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT
        ));
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()
        ));
     //  config.addAllowedMethod(HttpMethod.OPTIONS);
 //       config.addAllowedOrigin("*");

        config.setMaxAge(MAX_AGE); //<-The time the Options request is accepted(30min)
        source.registerCorsConfiguration("/**", config); //<-Applied for all the requests
        CorsFilter bean = new CorsFilter(source);
//        bean.setOrder(CORS_FILTER_ORDER); //<-Set at lowest position to be used before any Spring Security filter
        return bean;
    }

}