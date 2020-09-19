package com.example.taxi_bot.bot.handlers.search_handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Component
public class TaxiSearchRequestData {
    private String pickup;
    private String destination;
    private String data;
    private List<String> favoritePlaces = new ArrayList<>();

    public void setNewValueFavoritePlaces(String s){
        favoritePlaces.add(s);
    }

    public int getLenFavoritePlaces(){
        return favoritePlaces.size();
    }
}
