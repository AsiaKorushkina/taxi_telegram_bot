package com.example.taxi_bot.bot.handlers.search_handler;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.UserData;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Getter
public class TaxiSearchHandler implements MessageHandler {

    @Autowired
    private UserData userData;

    @Value("${reply.for_pickup}")
    String replyMessage;

    private BotState botState = BotState.SEARCH_TAXI;


    @Override
    public SendMessage handle(Message message) {
        Integer id = message.getFrom().getId();
        if (userData.getUsersCurrentBotState(id) == botState){
            userData.setUsersBotStates(id, BotState.ASK_PICKUP);
        }
        return new SendMessage(message.getChatId(), replyMessage);
    }

}
