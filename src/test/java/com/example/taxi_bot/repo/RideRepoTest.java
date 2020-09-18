package com.example.taxi_bot.repo;

import com.example.taxi_bot.model.Ride;
import com.example.taxi_bot.model.RidePrice;
import com.example.taxi_bot.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
class RideRepoTest {
    @Autowired
    private RideRepo rideRepo;


    @BeforeEach
    @Transactional
    @Rollback(false)
    public void setUp() {
        rideRepo.save(Ride.builder().user(User.builder().telegramId(1).name("Vasya").build()).ridePrices(List.of(
                RidePrice.builder().price(5).build(),
                RidePrice.builder().price(6).build())).build());

    }


}