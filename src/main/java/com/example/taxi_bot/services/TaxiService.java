package com.example.taxi_bot.services;

import com.example.taxi_bot.model.Coordinates;
import org.springframework.data.util.Pair;

import java.util.Map;

public interface TaxiService {
    Map<String,Integer> getPrices(Pair<Coordinates, Coordinates> coordinates);
}
