package com.example.taxi_bot.bot.messageUtil;

import org.springframework.stereotype.Component;

@Component
public class UnknownCommandMessageHandler implements MessageHandler {
    @Override
    public MessageType getMessageType() {
        return MessageType.UNKNOWN_COMMAND;
    }

    @Override
    public String getAnswer() {
        return "Ты что-то попутал";
    }
}
