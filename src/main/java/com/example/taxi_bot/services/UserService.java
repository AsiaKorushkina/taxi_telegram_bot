package com.example.taxi_bot.services;

import com.example.taxi_bot.model.User;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserService {

    User createUser(Message message);

    void saveUser(User user);

}
