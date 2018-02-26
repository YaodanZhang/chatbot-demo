package com.yaodanzhang.ai.chatbot.services;

import com.amazonaws.services.lexruntime.AmazonLexRuntime;
import com.amazonaws.services.lexruntime.model.PostTextRequest;
import com.amazonaws.services.lexruntime.model.PostTextResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("awsChatService")
public class AwsChatService implements ChatService {
    @Autowired
    private AmazonLexRuntime lexRuntime;

    @Override
    public String answer(String user, String question) {
        PostTextRequest request = new PostTextRequest();
        request.setBotName("MakeAClaim");
        request.setBotAlias("$LATEST");
        request.setUserId(user);
        request.setInputText(question);
        request.setSessionAttributes(new HashMap<>());

        PostTextResult result = lexRuntime.postText(request);
        return result.getMessage();
    }
}
