package tech.ermakovich.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeyboardService {

    public ReplyKeyboardMarkup createMainKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(new KeyboardRow(KeyboardButton.builder().text("🚀 Сгенерировать идею").build()));
        keyboard.add(
                new KeyboardRow(
                        KeyboardButton.builder().text("📰 Мои источники").build(),
                        KeyboardButton.builder().text("💰 Бюджет").build()
                )
        );
        keyboard.add(
                new KeyboardRow(
                        KeyboardButton.builder().text("⚙️ Профиль").build(),
                        KeyboardButton.builder().text("📌 Избранное").build()
                ));
        keyboard.add(new KeyboardRow(KeyboardButton.builder().text("📊 Тренды недели").build()));

        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboard)
                .resizeKeyboard(true)        // подстраивать под размер экрана
                .oneTimeKeyboard(false)      // не исчезать после нажатия
                .selective(false)
                .build();
    }
}
