package tech.ermakovich.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import tech.ermakovich.model.entity.Source;
import tech.ermakovich.model.entity.User;
import tech.ermakovich.service.SourceService;
import tech.ermakovich.service.UserService;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import static tech.ermakovich.utils.callback.CallbackKeys.TOGGLE_SOURCE;

@RequiredArgsConstructor
@Component
public class SourceKeyboardFactory {
    private final SourceService sourceService;
    private final UserService userService;

    private static final String DELIMITER =  ":";

    public InlineKeyboardMarkup createSourcesKeyboard(
            long chatId,
            boolean showDoneButton   // для онбординга
    ) {
        List<Source> allSources = sourceService.getAll();
        User user = userService.getOrCreate(chatId);
        Set<String> subscribedSourceIds = user.getSubscriptions().getSources();


        List<InlineKeyboardRow> rows = new ArrayList<>();

        for (Source source : allSources) {
            boolean isOn = subscribedSourceIds.contains(source.getId());
            String buttonText = (isOn ? "✅ " : "⬜ ") + source.getName();

            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(buttonText)
                    .callbackData(TOGGLE_SOURCE + DELIMITER + source.getId() + DELIMITER + isOn)
                    .build();

            rows.add(new InlineKeyboardRow(button));
        }

        InlineKeyboardRow navRow = new InlineKeyboardRow();
        navRow.add(InlineKeyboardButton.builder()
                .text("🔙 Назад")
                .callbackData("BACK_TO_MAIN")
                .build());

        // "Готово" (для онбординга)
        if (showDoneButton) {
            navRow.add(InlineKeyboardButton.builder()
                    .text("✅ Готово")
                    .callbackData("ONBOARDING_NEXT")
                    .build());
        }

        rows.add(navRow);
        return new InlineKeyboardMarkup(rows);
    }
}
