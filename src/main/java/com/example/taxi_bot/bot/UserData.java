package com.example.taxi_bot.bot;

import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class UserData {

    private Map<Integer, BotState> usersBotStates = new HashMap<>();
    private Map<Integer, TaxiSearchRequestData> usersSearchData = new HashMap<>();

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
