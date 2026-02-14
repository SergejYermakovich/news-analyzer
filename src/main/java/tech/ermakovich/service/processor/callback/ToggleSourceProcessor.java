package tech.ermakovich.service.processor.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import tech.ermakovich.model.entity.User;
import tech.ermakovich.service.MessageSender;
import tech.ermakovich.service.UserService;


import static tech.ermakovich.utils.callback.CallbackKeys.TOGGLE_SOURCE;

@Slf4j
@RequiredArgsConstructor
@Service
public class ToggleSourceProcessor implements CallbackDataProcessor {

    private final UserService userService;
    private final MessageSender messageSender;

    @Override
    public void process(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.getFrom().getId();
        User user = userService.getById(chatId);

        String data = callbackQuery.getData();
        ToggleUpdate toggleUpdate = parseData(data);

        updateSource(user, toggleUpdate);

        int messageId = callbackQuery.getMessage().getMessageId();
        messageSender.updateKeyboard(chatId, messageId);
    }

    private void updateSource(User user, ToggleUpdate toggleUpdate) {
        log.info("Source updating for user with id: {}", user.getTelegramId());

        String sourceId = toggleUpdate.sourceId();
        if (toggleUpdate.isOn()) {
            user.getSubscriptions().getSources().remove(sourceId);
        } else {
            user.getSubscriptions().getSources().add(sourceId);
        }

        log.info("Source updated for user with id: {}", user.getTelegramId());
    }


    @Override
    public String callbackData() {
        return TOGGLE_SOURCE;
    }

    private ToggleUpdate parseData(String data) {
        String[] split = data.split(":");
        return ToggleUpdate.builder()
                .sourceId(split[1])
                .isOn(Boolean.valueOf(split[2]))
                .build();
    }

}
