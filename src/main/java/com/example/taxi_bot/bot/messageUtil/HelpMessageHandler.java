package com.example.taxi_bot.bot.messageUtil;

import org.springframework.stereotype.Component;

@Component
public class HelpMessageHandler implements MessageHandler {
    @Override
    public MessageType getMessageType() {
        return MessageType.HELP;
    }

    @Override
    public String getAnswer() {
        return "Сам разбирайся";
    }
}
