package com.example.taxi_bot.bot;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Autowired
    TelegramFacade telegramFacade;


    @SneakyThrows
    public void onUpdateReceived(Update update) {
        SendMessage botAnswer = telegramFacade.handleUpdate(update);
        BotState botState = telegramFacade.getBotState();
        Message message = update.getMessage();
        if (botAnswer != null){
            botAnswer.enableMarkdown(true);
            if (botState == BotState.SHOW_HELP || botState == BotState.UNKNOWN_COMMAND || botState == BotState.SHOW_MENU){
                botAnswer.setReplyToMessageId(message.getMessageId());
            }
            execute(botAnswer);
        }

    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }


}
