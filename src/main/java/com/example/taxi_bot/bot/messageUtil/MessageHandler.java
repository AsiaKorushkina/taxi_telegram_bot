package com.example.taxi_bot.bot.messageUtil;

public interface MessageHandler {
    MessageType getMessageType();
    String getAnswer();
}
