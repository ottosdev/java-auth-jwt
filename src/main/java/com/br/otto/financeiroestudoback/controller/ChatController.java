package com.br.otto.financeiroestudoback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatClient.Builder chatClient;

    @GetMapping
    public String perguntar(@RequestParam(value = "mensagem") String mensagem) {
        return chatClient.build().prompt().user(mensagem).call().content();
    }
}
