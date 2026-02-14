package tech.ermakovich.service.processor.update;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.enums.UpdateType;
import tech.ermakovich.utils.UpdateUtils;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommonUpdateProcessor {
    private final Map<UpdateType, UpdateProcessor> updateProcessorMap;
    private final DefaultUpdateProcessor defaultUpdateProcessor;

    public void process(Update update) {
        UpdateType updateType = UpdateUtils.getUpdateType(update);
        UpdateProcessor updateProcessor = updateProcessorMap.getOrDefault(updateType, defaultUpdateProcessor);
        updateProcessor.process(update);

        log.debug("Update processed: {}. type = {}", update.getUpdateId(), updateType);
    }
}
