package cn.lynu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrosConfig {
    @Bean
    public CorsFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //1、配置跨域
        // 跨域的请求头
        corsConfiguration.addAllowedHeader("*");
        //  跨域的请求方法
        corsConfiguration.addAllowedMethod("*");
        // 所有的地址都可以访问
        corsConfiguration.addAllowedOrigin("*");
        // 可以携带cookie相关的信息
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);
    }

}
