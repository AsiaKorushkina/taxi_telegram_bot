package com.example.taxi_bot.bot.handlers.messageUtil;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.UserData;
import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import com.example.taxi_bot.model.FavoritePlace;
import com.example.taxi_bot.model.User;
import com.example.taxi_bot.repo.FavoritePlaceRepo;
import com.example.taxi_bot.repo.UserRepo;
import com.example.taxi_bot.services.FavoritePlaceService;
import com.example.taxi_bot.services.UserService;
import com.example.taxi_bot.services.impl.MessageServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

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

    @Autowired
    private FavoritePlaceService favoritePlaceService;

    @Autowired
    private UserService userService;

    @Override
    public SendMessage handle(Message message) {
        Integer id = message.getFrom().getId();
        TaxiSearchRequestData taxiSearchRequestData = userData.getTaxiSearchData(id);

        if (userData.getUsersCurrentBotState(id) == botState) {
            User user = userService.createUser(message);
            FavoritePlace favoritePlace = FavoritePlace.builder()
                    .address(message.getText())
                    .userId(user.getTelegramId())
                    .build();
            favoritePlaceService.save(favoritePlace);
            userService.saveUser(user);
            taxiSearchRequestData.setNewValueFavoritePlaces(message.getText());
            userData.setUsersSearchData(id, taxiSearchRequestData);
            userData.setUsersBotStates(id, BotState.DEFAULT);
        }
        return messageServices.getSendMessage(message.getChatId(), replyMessage);
    }
}
