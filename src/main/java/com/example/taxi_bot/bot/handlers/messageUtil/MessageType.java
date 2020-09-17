package com.example.taxi_bot.bot.handlers.messageUtil;

import lombok.Getter;

@Getter
public enum MessageType {
    HELPFORALL("Помощь"),
    SEARCH("Поиск такси"),
    HELP("/help", "Мотивационная команда."),
    START("/start", "Начало работы с ботом."),
    UNKNOWN("");

    private String command;
    private String description;


    MessageType(String command) {
        this.command = command;
    }

    MessageType(String command, String description ){
        this.command = command;
        this.description = description;
    }
}
