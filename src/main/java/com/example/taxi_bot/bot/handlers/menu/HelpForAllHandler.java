package com.example.taxi_bot.bot.handlers.menu;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.UserData;
import com.example.taxi_bot.services.MessageServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

//Список команд, которые поддерживаются ботом

@Component
@Getter
public class HelpForAllHandler implements MessageHandler {

    @Autowired
    private UserData userData;

    private BotState botState = BotState.SHOW_HELP_MENU;
    @Autowired
    private List<MessageHandler> messageHandlerList;

    @Value("${reply.for_help}")
    private String replyMessage;

    @Autowired
    private MessageServices messageServices;


    @Override
    public SendMessage handle(Message message) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println();
        stringBuilder.append(replyMessage);
        for (MessageHandler messageHandler: messageHandlerList){
            String command = messageHandler.getBotState().getCommand();
            if  (command != null && command.startsWith("/")){
                stringBuilder.append(command + ": " + messageHandler.getBotState().getDescription() + "\n");

            }
        }
        userData.setUsersBotStates(message.getFrom().getId(), null);
        return messageServices.getSendMessage(message.getChatId(),stringBuilder.toString());
    }


}
