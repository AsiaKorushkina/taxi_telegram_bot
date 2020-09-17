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
public class Pickup implements MessageHandler {
    private BotState botState = BotState.ASK_PICKUP;

    @Autowired
    private UserData userData;

    @Value("${reply.for_destination}")
    private String replyMessage;

    @Override
    public SendMessage handle(Message message) {
        Integer id = message.getFrom().getId();
        System.out.println(message.getText());
        if (userData.getUsersCurrentBotState(id) == botState){
            userData.setUsersBotStates(id, BotState.ASK_DESTINATION);
        }
        return new SendMessage(message.getChatId(), replyMessage);
    }

}
