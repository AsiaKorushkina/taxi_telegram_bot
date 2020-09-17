package com.example.taxi_bot.bot;

import lombok.Getter;

@Getter
public enum BotState {
    SEARCH_TAXI("Поиск такси"),
    ASK_PICKUP,
    ASK_DESTINATION,
    ASK_DATE,
    END_SEARCH,
    SHOW_HELP("/help", "Мотивационная команда."),
    SHOW_HELP_MENU("Помощь"),
    SHOW_MENU("/start", "Начало работы с ботом."),
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
