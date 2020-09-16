package com.example.taxi_bot.bot;

import com.example.taxi_bot.bot.messageUtil.MessageDistributor;
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
    MessageDistributor messageDistributor;

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        String botAnswer = messageDistributor.getAnswer(message);
        if (message != null && message.hasText() && message.getText().startsWith("/")){
            sendMsg(message, botAnswer);
        }

    }

    @SneakyThrows
    public synchronized void sendMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), s);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyToMessageId(message.getMessageId());
        execute(sendMessage);

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
