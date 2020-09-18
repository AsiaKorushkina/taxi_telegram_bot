package com.example.taxi_bot.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class MessageServices {

    public SendMessage getSendMessage(Long chatId, String message){
        return new SendMessage(chatId, message);
    }

}
