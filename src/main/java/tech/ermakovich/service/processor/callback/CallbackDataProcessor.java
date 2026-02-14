package tech.ermakovich.service.processor.callback;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackDataProcessor {

    void process(CallbackQuery callbackQuery);
    String callbackData();
}
