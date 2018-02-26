package com.yaodanzhang.ai.chatbot.controller;

import com.yaodanzhang.ai.chatbot.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @RequestMapping(method = RequestMethod.GET, path = "/users/{user}/questions/{question}")
    public @ResponseBody String chat(@PathVariable(value="user") String user,
                                     @PathVariable(value="question") String question) {
        return chatService.answer(user, question);
    }
}
