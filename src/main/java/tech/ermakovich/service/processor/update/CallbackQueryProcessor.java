package tech.ermakovich.service.processor.update;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.enums.UpdateType;

@RequiredArgsConstructor
@Slf4j
@Service
public class CallbackQueryProcessor implements UpdateProcessor {

    @Override
    public void process(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();

        log.info(data);
    }

    @Override
    public UpdateType getUpdateType() {
        return UpdateType.CALLBACK_QUERY;
    }
}
