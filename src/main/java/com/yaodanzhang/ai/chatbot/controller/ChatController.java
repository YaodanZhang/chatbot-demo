package com.yaodanzhang.ai.chatbot.controller;

import com.yaodanzhang.ai.chatbot.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    @Qualifier("awsChatService")
    private ChatService awsChatService;

    @Autowired
    @Qualifier("googleChatService")
    private ChatService googleChatService;

    @RequestMapping(method = RequestMethod.GET, path = "/providers/aws/users/{user}/questions/{question}")
    public @ResponseBody String awsChat(@PathVariable(value="user") String user,
                                     @PathVariable(value="question") String question) {
        return awsChatService.answer(user, question);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/providers/google/users/{user}/questions/{question}")
    public @ResponseBody String googleChat(@PathVariable(value="user") String user,
                                     @PathVariable(value="question") String question) {
        return googleChatService.answer(user, question);
    }

}
