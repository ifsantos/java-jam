package com.github.ifsantos.resourceserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.cors()
        .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/user/info", "/api/plattform")
                    .hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "/api/plattform")
                    .hasAnyAuthority("SCOPE_write")
                .anyRequest()
                    .authenticated()
        .and()
            .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

}
