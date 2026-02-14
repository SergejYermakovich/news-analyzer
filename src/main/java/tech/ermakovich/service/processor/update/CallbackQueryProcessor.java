package tech.ermakovich.service.processor.update;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.enums.UpdateType;
import tech.ermakovich.service.processor.callback.CallbackDataProcessor;
import tech.ermakovich.service.processor.callback.DefaultCallbackDataProcessor;
import tech.ermakovich.utils.callback.CallbackUtils;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class CallbackQueryProcessor implements UpdateProcessor {
    private final Map<String, CallbackDataProcessor> callbackDataProcessorMap;
    private final DefaultCallbackDataProcessor defaultCallbackDataProcessor;

    @Override
    public void process(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();

        String callbackQueryDataKey = CallbackUtils.generateCallbackQueryDataKey(callbackQuery.getData());

        CallbackDataProcessor callbackDataProcessor =
                callbackDataProcessorMap.getOrDefault(callbackQueryDataKey, defaultCallbackDataProcessor);
        callbackDataProcessor.process(callbackQuery);
    }

    @Override
    public UpdateType getUpdateType() {
        return UpdateType.CALLBACK_QUERY;
    }
}
