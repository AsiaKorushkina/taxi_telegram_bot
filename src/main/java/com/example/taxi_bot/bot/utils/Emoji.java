package com.example.taxi_bot.bot.utils;


import com.vdurmont.emoji.EmojiParser;
import lombok.Setter;


public enum Emoji {

    PRICE(EmojiParser.parseToUnicode(":dollar:")),
    MENU(EmojiParser.parseToUnicode(":point_down:")),
    HELP(EmojiParser.parseToUnicode(":information_source:")),
    TAXI(EmojiParser.parseToUnicode(":taxi:"));


    private final String name;

    Emoji(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }


}
