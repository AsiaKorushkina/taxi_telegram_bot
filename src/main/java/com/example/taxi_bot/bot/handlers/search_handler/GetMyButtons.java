package com.example.taxi_bot.bot.handlers.search_handler;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public interface GetMyButtons {
    InlineKeyboardMarkup getMyPlaceButtonsMarkup(List<String> favoritePlaces);
}
