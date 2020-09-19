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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class Pickup implements MessageHandler {
    private BotState botState = BotState.ASK_PICKUP;

    @Autowired
    private UserData userData;

    @Value("${reply.for_destination}")
    private String replyMessage;

    @Autowired
    private MessageServices messageServices;

    @Autowired
    private GetMyButtonsImpl getMyButtons;


    @Override
    public SendMessage handle(Message message) {
        Integer id = message.getFrom().getId();
        TaxiSearchRequestData taxiSearchRequestData = userData.getTaxiSearchData(id);
        taxiSearchRequestData.setPickup(message.getText());

        if (userData.getUsersCurrentBotState(id) == botState){
            userData.setUsersBotStates(id, BotState.ASK_DESTINATION);
            userData.setUsersSearchData(id, taxiSearchRequestData);
        }

        SendMessage reply = messageServices.getSendMessage(message.getChatId(), replyMessage);
        if (taxiSearchRequestData.getLenFavoritePlaces() > 0){
            reply.setReplyMarkup(getMyButtons.getMyPlaceButtonsMarkup(taxiSearchRequestData.getFavoritePlaces()));
        }
        //
        return reply;
    }

    @Override
    public SendMessage callbackHandle(CallbackQuery callbackQuery) {
        Integer id = callbackQuery.getFrom().getId();
        TaxiSearchRequestData taxiSearchRequestData = userData.getTaxiSearchData(id);
        taxiSearchRequestData.setPickup(callbackQuery.getData());


        if (userData.getUsersCurrentBotState(id) == botState){
            userData.setUsersBotStates(id, BotState.ASK_DESTINATION);
            userData.setUsersSearchData(id, taxiSearchRequestData);
        }

        SendMessage reply = messageServices.getSendMessage(callbackQuery.getMessage().getChatId(), replyMessage);
        if (taxiSearchRequestData.getLenFavoritePlaces() > 0){
            reply.setReplyMarkup(getMyButtons.getMyPlaceButtonsMarkup(taxiSearchRequestData.getFavoritePlaces()));
        }
        //
        return reply;
    }



}
