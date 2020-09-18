package com.example.taxi_bot.repo;

import com.example.taxi_bot.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepo extends JpaRepository<Ride, Integer> {

    Ride findFirstByOrderByRidePricesAsc();
}
