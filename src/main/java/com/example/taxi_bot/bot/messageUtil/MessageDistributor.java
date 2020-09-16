package com.example.taxi_bot.bot.messageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Component
public class MessageDistributor {

    @Autowired
    private List<MessageHandler> messageHandlerList;

    @Autowired
    private UnknownCommandMessageHandler unknownCommandMessageHandler;


    public String getAnswer(Message message) {
        for (MessageHandler messageHandler : messageHandlerList) {
            if (message.getText().equals(messageHandler.getMessageType().getValue())) {
                return messageHandler.getAnswer();
            }
        }
        return unknownCommandMessageHandler.getAnswer();
    }
}
