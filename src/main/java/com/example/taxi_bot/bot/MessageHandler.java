package com.example.taxi_bot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {
    SendMessage handle(Message message);
    BotState getBotState();

    default SendMessage callbackHandle(CallbackQuery callbackQuery){ return null;}
}
