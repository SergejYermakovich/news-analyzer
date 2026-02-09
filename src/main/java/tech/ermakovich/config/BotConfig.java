package tech.ermakovich.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class BotConfig {
    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;
}