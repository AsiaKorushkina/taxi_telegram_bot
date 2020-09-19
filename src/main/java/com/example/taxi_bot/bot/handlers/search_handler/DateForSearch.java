package com.example.taxi_bot.bot.handlers.search_handler;

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

@Component
@Getter
public class DateForSearch implements MessageHandler {
    private final BotState botState = BotState.ASK_DATE;

    @Autowired
    private UserData userData;

    @Value("${reply.for_end}")
    private String replyMessage;

    @Autowired
    private MessageServices messageServices;

    @Override
    public SendMessage handle(Message message) {
        Integer id = message.getFrom().getId();
        TaxiSearchRequestData taxiSearchRequestData = userData.getTaxiSearchData(id);
        taxiSearchRequestData.setData(message.getText());
        if (userData.getUsersCurrentBotState(id) == botState){
            userData.setUsersBotStates(id, null);
            userData.setUsersSearchData(id, taxiSearchRequestData);
        }
        return messageServices.getSendMessage(message.getChatId(), replyMessage);
    }
}
