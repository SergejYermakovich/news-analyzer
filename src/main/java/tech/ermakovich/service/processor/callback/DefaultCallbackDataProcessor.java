package tech.ermakovich.service.processor.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Slf4j
@Service
public class DefaultCallbackDataProcessor implements CallbackDataProcessor {
    @Override
    public void process(CallbackQuery callbackQuery) {
        log.info("DefaultCallbackDataProcessor.....");
    }

    @Override
    public String callbackData() {
        return null;
    }
}
