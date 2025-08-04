package com.br.sousa.cassiano.animalcontrol.adapter.aws.ssm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;

import java.net.URI;

@Slf4j
@Configuration
public class AWSSsmConfig {

    private static final Region REGION = Region.US_EAST_1;
    public static final String HTTP_LOCALHOST_4566 = "http://localhost:4566";
    public static final String USER_AWS_LOCAL = "local";
    public static final String PASS_AWS_LOCAL = "local";

    @Bean
    @Profile("!local")
    public SsmClient generateSsmClient() {
        log.info("m=AWSSsmConfig.generateSsmClient, status=start");
        return SsmClient.builder()
                .region(REGION)
                .build();
    }

    @Bean
    @Profile("local")
    public SsmClient generateSsmClientLocal() {
        log.info("m=AWSSsmConfig.generateSsmClientLocal, status=start");
        return SsmClient.builder()
                .region(REGION)
                .endpointOverride(URI.create(HTTP_LOCALHOST_4566))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(USER_AWS_LOCAL, PASS_AWS_LOCAL)))
                .build();
    }
}
