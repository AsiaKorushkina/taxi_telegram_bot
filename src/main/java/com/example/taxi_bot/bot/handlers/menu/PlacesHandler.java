package com.example.taxi_bot.bot.handlers.menu;


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

@Component
@Getter
public class PlacesHandler implements MessageHandler {

    @Autowired
    private UserData userData;
    private final BotState botState = BotState.SHOW_MY_PLACES;
    @Autowired
    private List<MessageHandler> messageHandlerList;

    @Value("${reply.for_list_places}")
    private String replyMessage;

    @Autowired
    private MessageServices messageServices;


    @Override
    public SendMessage handle(Message message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(replyMessage);
        Integer id = message.getFrom().getId();
        TaxiSearchRequestData taxiSearchData = userData.getTaxiSearchData(id);
        if (taxiSearchData.getLenFavoritePlaces() == 0){
            stringBuilder.append("Ах да, у тебя же их нет!");
        }
        else{
            List<String> favoritePlaces = taxiSearchData.getFavoritePlaces();
            for (String places: favoritePlaces){
                stringBuilder.append(places + "\n");
            }
        }
        userData.setUsersBotStates(id, null);
        return messageServices.getSendMessage(message.getChatId(),stringBuilder.toString());
    }


}
