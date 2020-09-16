package com.example.taxi_bot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;


@Component
public class BotStateContext {

    @Autowired
    private List<MessageHandler> messageHandlers;


    public SendMessage processInputMessage(BotState botState, Message message) {
        for (MessageHandler messageHandler: messageHandlers){
            if (botState == messageHandler.getBotState()){
                return messageHandler.handle(message);
            }
        }
        return null;
    }
}
