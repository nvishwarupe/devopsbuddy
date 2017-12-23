package com.devopsbuddy.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
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

        @Value("${aws.s3.profile}")
        private String awsProfileName;

        @Bean
        public AmazonS3Client s3Client() {
                AWSCredentials credentials = new ProfileCredentialsProvider(awsProfileName).getCredentials();
                AmazonS3Client s3Client = new AmazonS3Client(credentials);
                Region region = Region.getRegion(Regions.US_WEST_1);
                s3Client.setRegion(region);
                return s3Client;
        }

}
