package com.example.taxi_bot.bot.handlers.messageUtil;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.UserData;
import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import com.example.taxi_bot.services.MessageServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Map;

@Component
@Getter
public class InputMyPlacesHandler implements MessageHandler {
    private final BotState botState = BotState.ASK_MYPLACES;

    @Autowired
    private UserData userData;

    @Value("${reply.for_inputhome}")
    private String replyMessage;

    @Autowired
    private MessageServices messageServices;

    @Override
    public SendMessage handle(Message message) {
        Integer id = message.getFrom().getId();
        TaxiSearchRequestData taxiSearchRequestData = userData.getTaxiSearchData(id);

        if (userData.getUsersCurrentBotState(id) == botState){
            taxiSearchRequestData.setNewValueFavoritePlaces(message.getText());
            userData.setUsersSearchData(id, taxiSearchRequestData);
            userData.setUsersBotStates(id, null);
        }
        return messageServices.getSendMessage(message.getChatId(), replyMessage);
    }
}
