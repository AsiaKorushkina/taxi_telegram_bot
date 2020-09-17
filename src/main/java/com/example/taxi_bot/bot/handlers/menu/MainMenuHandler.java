package com.example.taxi_bot.bot.handlers.menu;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.handlers.messageUtil.MessageType;
import com.example.taxi_bot.services.MainMenuServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Getter
public class MainMenuHandler implements MessageHandler {
    private BotState botState = BotState.SHOW_MENU;
    private MessageType messageType = MessageType.START;

    @Autowired
    private MainMenuServices mainMenuServices;


    @Override
    public SendMessage handle(Message message) {
        return mainMenuServices.getMainMenuMessage(message.getChatId(), "Воспользуйтесь главным меню" );
    }


}
