package com.codingdood.webapisecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    BookInterceptor bookInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // If any URL come with "secure" as path, it will rout request to book interceptor
        registry.addInterceptor(bookInterceptor).addPathPatterns("/secure/**");
    }
}
