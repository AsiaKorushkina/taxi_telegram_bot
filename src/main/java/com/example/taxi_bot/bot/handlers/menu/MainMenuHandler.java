package com.example.taxi_bot.bot.handlers.menu;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.UserData;
import com.example.taxi_bot.services.MainMenuServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Getter
public class MainMenuHandler implements MessageHandler {

    @Autowired
    private UserData userData;

    private BotState botState = BotState.SHOW_MENU;

    @Value("${reply.welcome}")
    private String replyMessage;

    @Autowired
    private MainMenuServices mainMenuServices;


    @Override
    public SendMessage handle(Message message) {
        userData.setUsersBotStates(message.getFrom().getId(), null);
        return mainMenuServices.getMainMenuMessage(message.getChatId(), replyMessage);
    }


}
