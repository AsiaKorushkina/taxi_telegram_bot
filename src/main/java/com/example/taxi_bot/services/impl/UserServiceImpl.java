package com.example.taxi_bot.services.impl;

import com.example.taxi_bot.model.User;
import com.example.taxi_bot.repo.UserRepo;
import com.example.taxi_bot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(Message message) {
        return User.builder()
                   .telegramId(message.getFrom().getId())
                    .name(message.getFrom().getUserName())
                    .build();
    }

    @Override
    public void saveUser(User user) {
        try {
            userRepo.save(user);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
