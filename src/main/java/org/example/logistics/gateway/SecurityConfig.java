//package org.example.logistics.gateway;
//
//import org.springframework.cloud.gateway.config.GlobalFilter;
//import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory;
//
//    public SecurityConfig(TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory) {
//        this.tokenRelayGatewayFilterFactory = tokenRelayGatewayFilterFactory;
//    }
//
//    @Bean
//    public GlobalFilter jwtGlobalFilter() {
//        return tokenRelayGatewayFilterFactory.apply();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers("/api/auth/**");
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
//}