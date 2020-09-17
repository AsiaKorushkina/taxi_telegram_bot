package com.example.taxi_bot.services;

import com.example.taxi_bot.model.Coordinates;

public interface GeoPositionService {
    Coordinates getCoordinates(String address);
}
