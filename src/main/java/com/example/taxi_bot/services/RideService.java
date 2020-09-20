package com.example.taxi_bot.services;

import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import com.example.taxi_bot.model.Ride;
import com.example.taxi_bot.model.RidePrice;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface RideService {
    Ride createRide(TaxiSearchRequestData data, Message message, List<RidePrice> ridePrices);

    void save(Ride ride);
}
