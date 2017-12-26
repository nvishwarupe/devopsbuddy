package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;

/**
 * Created by nvishwarupe
 */
@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/udemy/devopsbuddy/application-prod.properties")
public class ProductionConfig {

    @Value("${stripe.prod.private.key}")
    private String stripeProdKey;


    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }


    @Bean
    public String stripeKey() {
        return stripeProdKey;
    }

    @Bean
    public ServletRegistrationBean h2ConsoleServletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }


}
