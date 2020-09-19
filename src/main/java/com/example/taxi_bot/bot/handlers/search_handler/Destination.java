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
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Getter
public class Destination implements MessageHandler {
    private BotState botState = BotState.ASK_DESTINATION;

    @Autowired
    private UserData userData;

    @Value("${reply.for_date}")
    private String replyMessage;

    @Autowired
    private MessageServices messageServices;

    @Autowired
    private GetMyButtonsImpl getMyButtons;

    @Override
    public SendMessage handle(Message message) {
        Integer id = message.getFrom().getId();
        TaxiSearchRequestData taxiSearchRequestData = userData.getTaxiSearchData(id);
        taxiSearchRequestData.setDestination(message.getText());
        //String text = message.getText();
//        if (text.equals("/myhome")){
//            taxiSearchRequestData.setDestination(taxiSearchRequestData.getMyHome());
//        }
//        else{
//            taxiSearchRequestData.setDestination(text);
//        }
        if (userData.getUsersCurrentBotState(id) == botState){
            userData.setUsersBotStates(id, BotState.ASK_DATE);
            userData.setUsersSearchData(id, taxiSearchRequestData);
        }
        return messageServices.getSendMessage(message.getChatId(), replyMessage);
    }

    @Override
    public SendMessage callbackHandle(CallbackQuery callbackQuery) {
        Integer id = callbackQuery.getFrom().getId();
        TaxiSearchRequestData taxiSearchRequestData = userData.getTaxiSearchData(id);
        taxiSearchRequestData.setDestination(callbackQuery.getData());


        if (userData.getUsersCurrentBotState(id) == botState){
            userData.setUsersBotStates(id, BotState.ASK_DATE);
            userData.setUsersSearchData(id, taxiSearchRequestData);
        }



        SendMessage reply = messageServices.getSendMessage(callbackQuery.getMessage().getChatId(), replyMessage);
//        if (taxiSearchRequestData.getLenFavoritePlaces() > 0){
//            reply.setReplyMarkup(getMyButtons.getMyPlaceButtonsMarkup(taxiSearchRequestData.getFavoritePlaces()));
//        }
        //
        return reply;
    }

}
