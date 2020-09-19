package com.example.taxi_bot.services;

import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import com.example.taxi_bot.model.Coordinates;
import com.example.taxi_bot.model.RidePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaxiRideFacade {

    @Autowired
    private List<TaxiService> taxiServices;

    @Autowired
    private GeoPositionService geoPositionService;

    public List<RidePrice> aggregateRidePrice(TaxiSearchRequestData data){
        Coordinates startPoint = geoPositionService.getCoordinates(data.getPickup());
        Coordinates endPoint = geoPositionService.getCoordinates(data.getDestination());
        List<RidePrice> ridePrices = new ArrayList<>();
        for (TaxiService taxiService : taxiServices) {
            ridePrices.addAll(taxiService.getRideInfo(startPoint, endPoint));
        }
        return ridePrices;
    }

}
