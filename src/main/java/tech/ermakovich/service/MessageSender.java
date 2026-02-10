package tech.ermakovich.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import tech.ermakovich.config.BotConfig;

@Slf4j
@Service
public class MessageSender {
    private final TelegramClient telegramClient;

    protected MessageSender(BotConfig botConfig) {
        this.telegramClient = new OkHttpTelegramClient(botConfig.getBotToken());
    }

    public void sendMessage(long chatId, String text) {
        sendMessage(chatId, text, null);
    }

    public void sendMessage(long chatId, String text, ReplyKeyboard keyboard) {
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        if (keyboard != null) {
            message.setReplyMarkup(keyboard);
        }
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            log.error("Failed to send message to chatId: {}.", chatId, e);
        }
    }

}
