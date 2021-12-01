package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
@Configuration
public class ConfigWebMvc implements WebMvcConfigurer {
    @Bean
    public SpringResourceTemplateResolver viewResolver(){
        SpringResourceTemplateResolver impl=new SpringResourceTemplateResolver();
        impl.setPrefix("classpath:/templates/views/");
        impl.setSuffix(".html");
        impl.setTemplateMode(TemplateMode.HTML);
        return impl;
    }
    @Bean
    public SpringTemplateEngine springTemplate(){
        SpringTemplateEngine springTemplate=new SpringTemplateEngine();
        springTemplate.setTemplateResolver(viewResolver());
        springTemplate.setEnableSpringELCompiler(true);
        return springTemplate;
    }
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver thymeleafViewResolver=new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(springTemplate());
        return thymeleafViewResolver;
    }
}
