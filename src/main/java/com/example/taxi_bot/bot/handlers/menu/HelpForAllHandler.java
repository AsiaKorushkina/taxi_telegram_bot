package com.example.taxi_bot.bot.handlers.menu;

import com.example.taxi_bot.bot.BotState;
import com.example.taxi_bot.bot.MessageHandler;
import com.example.taxi_bot.bot.handlers.messageUtil.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

//Список команд, которые поддерживаются ботом

@Component
public class HelpForAllHandler implements MessageHandler {

    @Autowired
    private List<MessageHandler> messageHandlerList;

    @Override
    public SendMessage handle(Message message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Команды для общения с ботом:\n");
        for (MessageHandler messageHandler: messageHandlerList){
            if  (messageHandler.getMessageType().getDescription() != null){
                stringBuilder.append(messageHandler.getMessageType().getCommand() + ": " + messageHandler.getMessageType().getDescription() + "\n");


            }
        }
        return new SendMessage(message.getChatId(),stringBuilder.toString());
    }

    @Override
    public BotState getBotState() {
        return BotState.SHOW_HELP_MENU;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.HELPFORALL;
    }
}
