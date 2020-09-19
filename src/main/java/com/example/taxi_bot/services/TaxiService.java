package com.example.taxi_bot.services;

import com.example.taxi_bot.model.Coordinates;
import com.example.taxi_bot.model.RidePrice;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;

public interface TaxiService {
    List<RidePrice> getRideInfo(Coordinates startPoint, Coordinates endPoint);
}
