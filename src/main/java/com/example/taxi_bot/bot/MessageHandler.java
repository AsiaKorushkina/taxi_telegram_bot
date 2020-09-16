package com.example.taxi_bot.bot;

import com.example.taxi_bot.bot.handlers.messageUtil.MessageType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {
    SendMessage handle(Message message);
    BotState getBotState();
    MessageType getMessageType();
}
