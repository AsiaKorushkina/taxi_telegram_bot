package com.example.taxi_bot.services.db_services;

import com.example.taxi_bot.model.Ride;
import com.example.taxi_bot.model.User;
import com.example.taxi_bot.repo.UserRepo;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Faker faker;



    @Transactional
    public void save10User() {
        List<User> people = Stream.iterate(1, i -> ++i)
                .limit(10)
                .map(this::createRandomUser)
                .collect(Collectors.toList());

        for (User person : people) {
            userRepo.save(person);
        }

        for (User person : people) {
            person.setName(person.getName().toUpperCase());
        }

    }

    public User createRandomUser(int i) {
        return User.builder().telegramId(i).name(faker.funnyName().name()).rides(
                List.of(Ride.builder().dateDepart(LocalDate.parse("12.12.12")).build(),
                Ride.builder().dateDepart(LocalDate.parse("12.12.12")).build())).build();
    }
}
