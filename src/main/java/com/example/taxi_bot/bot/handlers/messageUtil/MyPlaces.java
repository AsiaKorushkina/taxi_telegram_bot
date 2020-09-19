package com.example.taxi_bot.bot.handlers.messageUtil;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.UserData;
import com.example.taxi_bot.services.MessageServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.persistence.Column;

@Component
@Getter
public class MyPlaces implements MessageHandler {
    private BotState botState = BotState.MY_PLACES;

    @Autowired
    private UserData userData;

    @Value("${reply.for_places}")
    private String replyMessage;


    @Autowired
    private MessageServices messageServices;

    @Override
    public SendMessage handle(Message message) {
        userData.setUsersBotStates(message.getFrom().getId(), BotState.ASK_MYPLACES);
        return messageServices.getSendMessage(message.getChatId(), replyMessage);
    }

}
