package com.example.taxi_bot.bot.handlers.search_handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetMyButtonsImpl implements GetMyButtons {
    @Override
    public InlineKeyboardMarkup getMyPlaceButtonsMarkup(List<String> favoritePlaces) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> buttons = new ArrayList<>();

        for (String place: favoritePlaces){
            buttons.add(new InlineKeyboardButton().setText(place));
        }

        for (InlineKeyboardButton button: buttons){
            button.setCallbackData(button.getText());
        }


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(buttons);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;

    }
}
