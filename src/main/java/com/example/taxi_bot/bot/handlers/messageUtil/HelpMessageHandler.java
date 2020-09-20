package com.example.taxi_bot.bot.handlers.messageUtil;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.UserData;
import com.example.taxi_bot.services.impl.MessageServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Getter
public class HelpMessageHandler implements MessageHandler {
    private final BotState botState = BotState.SHOW_HELP;

    @Autowired
    private UserData userData;

    @Value("${reply.for_motivation}")
    private String replyMessage;

    @Autowired
    private MessageServices messageServices;

    @Override
    public SendMessage handle(Message message) {
        userData.setUsersBotStates(message.getFrom().getId(), BotState.DEFAULT);
        return messageServices.getSendMessage(message.getChatId(), replyMessage);
    }

}
