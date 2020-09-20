package com.example.taxi_bot.bot;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;


@Component
@Getter
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

    public SendMessage processCallbackMessage(BotState botState, CallbackQuery callbackQuery) {
        for (MessageHandler messageHandler: messageHandlers){
            if (botState == messageHandler.getBotState()){
                return messageHandler.callbackHandle(callbackQuery);
            }
        }
        return null;
    }
}
