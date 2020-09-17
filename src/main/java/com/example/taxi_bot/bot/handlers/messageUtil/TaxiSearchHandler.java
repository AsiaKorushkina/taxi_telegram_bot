package com.example.taxi_bot.bot.handlers.messageUtil;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class TaxiSearchHandler implements MessageHandler {

    @Override
    public SendMessage handle(Message message) {
        return new SendMessage(message.getChatId(), "Откуда?");
    }

    @Override
    public BotState getBotState() {
        return BotState.ASK_PICKUP;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SEARCH;
    }
}
