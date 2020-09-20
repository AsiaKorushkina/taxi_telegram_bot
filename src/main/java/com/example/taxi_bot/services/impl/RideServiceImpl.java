package com.example.taxi_bot.services.impl;

import com.example.taxi_bot.bot.handlers.search_handler.TaxiSearchRequestData;
import com.example.taxi_bot.model.Ride;
import com.example.taxi_bot.model.RidePrice;
import com.example.taxi_bot.repo.RideRepo;
import com.example.taxi_bot.services.RideService;
import com.example.taxi_bot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;
import java.util.List;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private UserService userService;

    @Autowired
    private RideRepo rideRepo;

    @Override
    public Ride createRide(TaxiSearchRequestData data, Message message, List<RidePrice> ridePrices) {
        Ride ride = Ride.builder()
                .pickupPoint(data.getPickup())
                .endPoint(data.getDestination())
                .dateDepart(LocalDate.now())
                .ridePrices(ridePrices)
                .userId(userService.createUser(message).getTelegramId())
                .build();

        return ride;
    }

    @Override
    public void save(Ride ride) {
        rideRepo.save(ride);
    }
}
