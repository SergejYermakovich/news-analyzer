package tech.ermakovich.utils;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class BotCommands {
    public static final String START = "/start";
    public static final String GENERATE_IDEA_BTN = "🚀 Сгенерировать идею";
    public static final String MY_SOURCES_BTN = "📰 Мои источники";
    public static final String BUDGET_BTN = "💰 Бюджет";
    public static final String MY_PROFILE_BTN = "⚙️ Профиль";
    public static final String FAVOURITES_BTN = "📌 Избранное";
    public static final String TRENDS_BTN = "📊 Тренды недели";

    public static final Set<String> MAIN_MENU_BUTTONS = Set.of(
            GENERATE_IDEA_BTN,
            MY_SOURCES_BTN,
            BUDGET_BTN,
            MY_PROFILE_BTN,
            FAVOURITES_BTN,
            TRENDS_BTN
    );
}
