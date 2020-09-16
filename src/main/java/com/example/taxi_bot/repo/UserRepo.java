package com.example.taxi_bot.repo;

import com.example.taxi_bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

}