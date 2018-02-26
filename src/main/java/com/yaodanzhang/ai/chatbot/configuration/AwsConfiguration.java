package com.yaodanzhang.ai.chatbot.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.lexruntime.AmazonLexRuntime;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguration {
    @Bean
    public AmazonLexRuntime lexRuntime(@Autowired AWSCredentialsProvider credentialsProvider) {
        AmazonLexRuntimeClientBuilder builder = AmazonLexRuntimeClientBuilder.standard();
        builder.setCredentials(credentialsProvider);
        builder.setRegion("us-east-1");
        return builder.build();
    }

    @Bean
    public AWSCredentialsProvider awsCredentials() {
        return new SystemPropertiesCredentialsProvider();
    }
}
