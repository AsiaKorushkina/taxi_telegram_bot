package com.example.taxi_bot.services.impl;

import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import com.example.taxi_bot.model.Coordinates;
import com.example.taxi_bot.model.Ride;
import com.example.taxi_bot.model.RidePrice;
import com.example.taxi_bot.services.GeoPositionService;
import com.example.taxi_bot.services.RideService;
import com.example.taxi_bot.services.TaxiService;
import com.example.taxi_bot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaxiRideFacade {

    @Autowired
    private List<TaxiService> taxiServices;

    @Autowired
    private GeoPositionService geoPositionService;

    @Autowired
    private RideService rideService;

    @Autowired
    private UserService userService;

    public String getRideInfo(TaxiSearchRequestData data, Message message) {
        Coordinates startPoint = geoPositionService.getCoordinates(data.getPickup());
        Coordinates endPoint = geoPositionService.getCoordinates(data.getDestination());
        Map<String, List<RidePrice>> ridePricesMap = aggregateRidePrice(startPoint, endPoint);
        List<RidePrice> ridePrices = new ArrayList<>();
        ridePricesMap.values().forEach(ridePrices::addAll);
        Ride ride = rideService.createRide(data, message, ridePrices);
        userService.saveUser(userService.createUser(message));
        rideService.save(ride);
        return ridePricesMapToString(ridePricesMap);
    }


    private Map<String, List<RidePrice>> aggregateRidePrice(Coordinates startPoint, Coordinates endPoint){
        Map<String, List<RidePrice>> ridePricesMap = new HashMap<>();
        for (TaxiService taxiService : taxiServices) {
            List<RidePrice> ridePrices = taxiService.getRideInfo(startPoint, endPoint);
            if(!ridePrices.isEmpty() && ridePrices.get(0).getAggregator() != null) {
                ridePricesMap.put(ridePrices.get(0).getAggregator().name(), ridePrices);
            }
        }
        return ridePricesMap;
    }

    private String ridePricesMapToString(Map<String, List<RidePrice>> ridePriceMap) {
        StringBuilder res = new StringBuilder();
        for (String agr : ridePriceMap.keySet()) {
            List<RidePrice> ridePrices = ridePriceMap.get(agr);
            res.append("\n").append(agr).append("\n");
            for (RidePrice ridePrice : ridePrices) {
                res.append(ridePrice.getClassTaxi()).append(" ").append(ridePrice.getPrice()).append("\n");
            }
        }
        return String.valueOf(res);
    }


}