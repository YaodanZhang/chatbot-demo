package com.yaodanzhang.ai.chatbot.configuration;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceContextBuilder;
import com.amazonaws.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ai.api.AIConfiguration.SupportedLanguages.ChineseChina;

@Configuration
public class GoogleConfiguration {

    private static final String GOOGLE_CLIENT_ACCESS_KEY = "google.clientAccessKey";

    @Bean
    public AIConfiguration aiConfiguration() {
        String accessToken = StringUtils.trim(System.getProperty(GOOGLE_CLIENT_ACCESS_KEY));

        return new AIConfiguration(accessToken, ChineseChina);
    }

    @Bean
    public AIDataService aiDataService(@Autowired AIConfiguration aiConfiguration) {
        return new AIDataService(aiConfiguration, new AIServiceContextBuilder().generateSessionId().build());
    }

}
