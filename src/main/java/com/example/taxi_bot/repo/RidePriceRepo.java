package com.example.taxi_bot.repo;

import com.example.taxi_bot.model.RidePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RidePriceRepo extends JpaRepository<RidePrice, Integer> {
}
