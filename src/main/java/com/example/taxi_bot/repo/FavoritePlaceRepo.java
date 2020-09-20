package com.example.taxi_bot.repo;

import com.example.taxi_bot.model.FavoritePlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePlaceRepo extends JpaRepository<FavoritePlace, Integer> {
}
