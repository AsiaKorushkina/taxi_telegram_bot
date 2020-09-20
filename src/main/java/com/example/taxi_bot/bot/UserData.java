package com.example.taxi_bot.bot;

import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import com.example.taxi_bot.model.FavoritePlace;
import com.example.taxi_bot.repo.FavoritePlaceRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class UserData {

    private Map<Integer, BotState> usersBotStates = new HashMap<>();
    private Map<Integer, TaxiSearchRequestData> usersSearchData = new HashMap<>();

    @Autowired
    private FavoritePlaceRepo favoritePlaceRepo;

    @PostConstruct
    public void getUserFavoritePlaces(){
        try {
            List<FavoritePlace> all = favoritePlaceRepo.findAll();
            for (FavoritePlace favoritePlace : all) {
                if (usersSearchData.get(favoritePlace.getUserId()) == null) {
                    TaxiSearchRequestData taxiSearchRequestData = new TaxiSearchRequestData();
                    List<String> favoritePlaces = new ArrayList<>();
                    favoritePlaces.add(favoritePlace.getAddress());
                    taxiSearchRequestData.setFavoritePlaces(favoritePlaces);
                    usersSearchData.put(favoritePlace.getUserId(), taxiSearchRequestData);
                } else {
                    TaxiSearchRequestData taxiSearchRequestData = usersSearchData.get(favoritePlace.getUserId());
                    taxiSearchRequestData.getFavoritePlaces().add(favoritePlace.getAddress());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setUsersBotStates(Integer usersId, BotState botState) {
        usersBotStates.put(usersId, botState);
    }

    public void  setUsersSearchData(Integer usersId, TaxiSearchRequestData taxiSearchRequestData){
        usersSearchData.put(usersId, taxiSearchRequestData);
    }

    public BotState getUsersCurrentBotState(int userId) {
        BotState botState = usersBotStates.getOrDefault(userId, BotState.DEFAULT);

        return botState;
    }

    public TaxiSearchRequestData getTaxiSearchData(Integer usersId){
        TaxiSearchRequestData taxiSearchRequestData = usersSearchData.get(usersId);
        if (taxiSearchRequestData == null){
            taxiSearchRequestData = new TaxiSearchRequestData();
        }
        return  taxiSearchRequestData;
    }
}
