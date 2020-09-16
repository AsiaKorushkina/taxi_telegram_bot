package com.example.taxi_bot.repo;

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


@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;


    @BeforeEach
    @Transactional
    @Rollback(false)
    public void setUp() {
        userRepo.save(User.builder().name("Vasya").build());
        userRepo.save(User.builder().name("Lena").build());
        userRepo.save(User.builder().name("Vasya").build());

    }

    @Test
    @Transactional
    public void onlyVasyaPerson() {
        List<User> people = userRepo.findByName("Vasya");
        Assert.assertEquals(2,people.size());
    }
}