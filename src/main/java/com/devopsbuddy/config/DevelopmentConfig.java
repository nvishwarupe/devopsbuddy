package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.MockEmailService;
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
@Profile("dev")
@PropertySource("file:///${user.home}/udemy/devopsbuddy/application-dev.properties")
public class DevelopmentConfig {

    @Value("${stripe.test.private.key}")
    private String stripeDevKey;



    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }


    @Bean
    public ServletRegistrationBean h2ConsoleServletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }

    @Bean
    public String stripeKey() {
        return stripeDevKey;
    }


}
