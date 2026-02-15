package tech.ermakovich.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import tech.ermakovich.config.BotConfig;
import tech.ermakovich.service.factory.SourceKeyboardFactory;

@Slf4j
@Service
public class MessageSender {
    private final TelegramClient telegramClient;
    private final SourceKeyboardFactory sourceKeyboardFactory;

    protected MessageSender(BotConfig botConfig,
                            SourceKeyboardFactory sourceKeyboardFactory) {
        this.telegramClient = new OkHttpTelegramClient(botConfig.getBotToken());
        this.sourceKeyboardFactory = sourceKeyboardFactory;
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
            log.error("Failed to send message for chatId: {}.", chatId, e);
        }
    }

    public void updateKeyboard(long chatId, int messageId) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup();
        edit.setChatId(chatId);
        edit.setMessageId(messageId);
        edit.setReplyMarkup(sourceKeyboardFactory.createSourcesKeyboard(chatId, false));
        try {
            telegramClient.execute(edit);
        } catch (TelegramApiException e) {
            log.error("Failed to update keyboard for chatId: {}.", chatId, e);
        }
    }

}
