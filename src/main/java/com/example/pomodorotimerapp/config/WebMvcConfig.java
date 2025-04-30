package com.example.pomodorotimerapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        //registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/register").setViewName("register");
        registry.addViewController("/pomodoro").setViewName("pomodoro");
        registry.addViewController("/history").setViewName("history");
        //registry.addViewController("/settings").setViewName("settings");
        //registry.addViewController("/account-created").setViewName("account-created");

    }
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
