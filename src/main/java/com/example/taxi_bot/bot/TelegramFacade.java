package com.example.taxi_bot.bot;

import com.example.taxi_bot.services.MainMenuServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component

public class TelegramFacade {
    @Getter
    BotState botState;

    @Autowired
    private BotStateContext botStateContext;

    @Autowired
    private MainMenuServices mainMenuServices;

    @Autowired
    private List<MessageHandler> messageHandlerList;


    @Autowired
    private UserData userData;

    public SendMessage handleUpdate(Update update) {
        SendMessage botAnswer = null;

        if (update.hasMessage()){
            botAnswer = handleInputMessage(update.getMessage());
        }
        return botAnswer;
    }


    private SendMessage handleInputMessage(Message message) {
        botState = userData.getUsersCurrentBotState(message.getFrom().getId());


        for (MessageHandler messageHandler : messageHandlerList) {
            if (message.getText().equals(messageHandler.getBotState().getCommand())) {
                botState = messageHandler.getBotState();
                break;
            }
        }
        if (message.getText().startsWith("/")){
            botState = botState == null? BotState.UNKNOWN_COMMAND: botState;
        }
        if (botState != null){
            userData.setUsersBotStates(message.getFrom().getId(), botState);

            return botStateContext.processInputMessage(botState, message);
        }

        return null;
    }

}
