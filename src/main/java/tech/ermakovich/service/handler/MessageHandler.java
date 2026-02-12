package tech.ermakovich.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageHandler {
    public void handleMessage(Long chatId, String messageText, String username) {
        log.info("Handled message: {} from user {}.", messageText, username);


    }
}
