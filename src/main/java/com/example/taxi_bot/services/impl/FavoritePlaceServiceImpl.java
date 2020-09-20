package com.example.taxi_bot.services.impl;

import com.example.taxi_bot.model.FavoritePlace;
import com.example.taxi_bot.repo.FavoritePlaceRepo;
import com.example.taxi_bot.services.FavoritePlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritePlaceServiceImpl implements FavoritePlaceService {

    @Autowired
    private FavoritePlaceRepo favoritePlaceRepo;

    @Override
    public void save(FavoritePlace favoritePlace) {
        try {
            favoritePlaceRepo.save(favoritePlace);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
