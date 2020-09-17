package com.example.taxi_bot.bot.handlers.messageUtil;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Getter
public class HelpMessageHandler implements MessageHandler {
    BotState botState = BotState.SHOW_HELP;

    MessageType messageType = MessageType.HELP;

    @Override
    public SendMessage handle(Message message) {
        return new SendMessage(message.getChatId(), "Сам разбирайся.");
    }

}
