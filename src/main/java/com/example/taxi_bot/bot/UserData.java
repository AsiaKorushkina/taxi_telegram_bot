package com.example.taxi_bot.bot;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class UserData {
    private Map<Integer, BotState> usersBotStates = new HashMap<>();

    public void setUsersBotStates(Integer usersId, BotState botState) {
        usersBotStates.put(usersId, botState);
    }

    public BotState getUsersCurrentBotState(int userId) {
        BotState botState = usersBotStates.get(userId);

        return botState;
    }
}
