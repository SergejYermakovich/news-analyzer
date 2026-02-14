package tech.ermakovich.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static tech.ermakovich.utils.BotCommands.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeyboardService {

    public ReplyKeyboardMarkup createMainKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(new KeyboardRow(KeyboardButton.builder().text(GENERATE_IDEA_BTN).build()));
        keyboard.add(
                new KeyboardRow(
                        KeyboardButton.builder().text(MY_SOURCES_BTN).build(),
                        KeyboardButton.builder().text(BUDGET_BTN).build()
                )
        );
        keyboard.add(
                new KeyboardRow(
                        KeyboardButton.builder().text(MY_PROFILE_BTN).build(),
                        KeyboardButton.builder().text(FAVOURITES_BTN).build()
                ));
        keyboard.add(new KeyboardRow(KeyboardButton.builder().text(TRENDS_BTN).build()));

        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboard)
                .resizeKeyboard(true)        // подстраивать под размер экрана
                .oneTimeKeyboard(false)      // не исчезать после нажатия
                .selective(false)
                .build();
    }
}
