package tech.ermakovich.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseConsts {
    public static final String START_RESPONSE = """
            👋 Привет! Я — бизнес-аналитик. Я читаю новости и превращаю их в готовые бизнес-идеи.
            Хотите попробовать? Я уже настроен по умолчанию, но лучше давайте подберём идеи под вас.
            """;

    public static final String USER_PROFILE = """
            👤 Ваш профиль
            
            📰 Подписки: %s
            💰 Бюджет: %s
            🌍 Гео: %s
            📅 Рассылка: %s
            ⚙️ Формат: %s
            """;
}
