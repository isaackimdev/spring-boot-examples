package com.example.demo.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private OpenAiChatModel chatModel;

    @GetMapping("/chat")
    public String getChat(@RequestParam("message") String message) {
        return chatModel.call(message);
    }
}
