package com.yaodanzhang.ai.chatbot.services;

import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("googleChatService")
public class GoogleChatService implements ChatService {
    @Autowired
    private AIDataService aiDataService;

    @Override
    public String answer(String user, String question) {
        try {
            return aiDataService.request(new AIRequest(question))
                    .getResult()
                    .getFulfillment()
                    .getSpeech();
        } catch (AIServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
