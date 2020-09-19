package com.example.taxi_bot.services;

import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import com.example.taxi_bot.model.Coordinates;
import com.example.taxi_bot.model.Ride;
import com.example.taxi_bot.model.RidePrice;
import com.example.taxi_bot.model.User;
import com.example.taxi_bot.repo.RideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaxiRideFacade {

    @Autowired
    private List<TaxiService> taxiServices;

    @Autowired
    private GeoPositionService geoPositionService;

    @Autowired
    private RideRepo rideRepo;

    public String getRideInfo(TaxiSearchRequestData data, Message message) {
        Coordinates startPoint = geoPositionService.getCoordinates(data.getPickup());
        Coordinates endPoint = geoPositionService.getCoordinates(data.getDestination());
        List<RidePrice> ridePrices = aggregateRidePrice(startPoint, endPoint);
        Ride ride = Ride.builder()
                .pickupPoint(data.getPickup())
                .endPoint(data.getDestination())
                .dateDepart(LocalDate.now())
                .ridePrices(ridePrices)
                .user(getUser(message))
                .build();
        rideRepo.save(ride);
        return ride.toString();
    }


    private List<RidePrice> aggregateRidePrice(Coordinates startPoint, Coordinates endPoint){
        List<RidePrice> ridePrices = new ArrayList<>();
        for (TaxiService taxiService : taxiServices) {
            ridePrices.addAll(taxiService.getRideInfo(startPoint, endPoint));
        }
        return ridePrices;
    }

    private User getUser(Message message) {
        return User.builder()
                .telegramId(message.getFrom().getId())
                .name(message.getFrom().getUserName())
                .build();
    }

}
