package com.devopsbuddy.config;

import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.devopsbuddy.backend.persistence.repositories")
@EntityScan(basePackages = "com.devopsbuddy.backend.persistence.domain.backend")
@PropertySource("file:///${user.home}/udemy/devopsbuddy/application-common.properties")
@EnableTransactionManagement
        public class ApplicationConfig {
}
