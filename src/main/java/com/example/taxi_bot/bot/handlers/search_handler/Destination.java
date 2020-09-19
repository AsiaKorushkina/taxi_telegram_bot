package com.example.taxi_bot.bot.handlers.search_handler;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.UserData;
import com.example.taxi_bot.model.RidePrice;
import com.example.taxi_bot.services.MessageServices;
import com.example.taxi_bot.services.TaxiRideFacade;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Component
@Getter
public class Destination implements MessageHandler {

    private BotState botState = BotState.ASK_DESTINATION;

    @Autowired
    private UserData userData;

    @Value("${reply.for_end}")
    private String replyMessage;

    @Autowired
    private MessageServices messageServices;

    @Autowired
    private TaxiRideFacade taxiRideFacade;

    @Override
    public SendMessage handle(Message message) {
        Integer id = message.getFrom().getId();
        TaxiSearchRequestData taxiSearchRequestData = userData.getTaxiSearchData(id);
        taxiSearchRequestData.setDestination(message.getText());
        if (userData.getUsersCurrentBotState(id) == botState){
            userData.setUsersBotStates(id, BotState.END_SEARCH);
            userData.setUsersSearchData(id, taxiSearchRequestData);
        }
        List<RidePrice> ridePrices = taxiRideFacade.aggregateRidePrice(taxiSearchRequestData);
        return messageServices.getSendMessage(message.getChatId(), ridePrices.toString());
    }
}
