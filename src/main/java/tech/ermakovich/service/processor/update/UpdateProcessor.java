package tech.ermakovich.service.processor.update;

import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.enums.UpdateType;

public interface UpdateProcessor {
    void process(Update update);

    UpdateType getUpdateType();
}
