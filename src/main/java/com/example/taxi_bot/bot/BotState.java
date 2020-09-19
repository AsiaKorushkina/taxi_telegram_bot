package com.example.taxi_bot.bot;

import com.example.taxi_bot.bot.utils.Emoji;
import lombok.Getter;

@Getter
public enum BotState {
    SEARCH_TAXI("Поиск такси " + Emoji.TAXI),
    ASK_MYPLACES,
    ASK_PICKUP,
    ASK_DESTINATION,
    ASK_DATE,
    END_SEARCH,
    SHOW_HELP("/help", "Мотивационная команда."),
    SHOW_HELP_MENU("Помощь " + Emoji.HELP),
    SHOW_MENU("/start", "Начало работы с ботом."),
    MY_PLACES("/myplaces", "Где я часто бываю?"),

    UNKNOWN_COMMAND;

    private String command;
    private String description;

    BotState(){}
    BotState(String command) {
        this.command = command;
    }
    BotState(String command, String description){
        this.command = command;
        this.description = description;
    }
}
