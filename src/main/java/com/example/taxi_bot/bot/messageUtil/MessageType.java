package com.example.taxi_bot.bot.messageUtil;

import lombok.Getter;
import org.springframework.stereotype.Component;

public enum MessageType {
    HELP("/help"),
    UNKNOWN_COMMAND("");

    @Getter
    private String value;

    MessageType(String value) {
        this.value = value;
    }
}
