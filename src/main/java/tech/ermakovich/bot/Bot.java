package tech.ermakovich.bot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.config.BotConfig;
import tech.ermakovich.service.handler.GlobalUpdateExceptionHandler;
import tech.ermakovich.service.processor.update.CommonUpdateProcessor;

import java.util.List;

@Slf4j
@Service
public class Bot implements LongPollingUpdateConsumer {
    private BotSession botSession;
    private TelegramBotsLongPollingApplication botsApplication;
    private final BotConfig botConfig;
    private final GlobalUpdateExceptionHandler exceptionHandler;
    private final CommonUpdateProcessor commonUpdateProcessor;

    public Bot(BotConfig botConfig,
               GlobalUpdateExceptionHandler exceptionHandler,
               CommonUpdateProcessor commonUpdateProcessor
    ) {
        this.botConfig = botConfig;
        this.exceptionHandler = exceptionHandler;
        this.commonUpdateProcessor = commonUpdateProcessor;
    }

    @Override
    public void consume(List<Update> updates) {
        for (Update update : updates) {
            try {
                onUpdateReceived(update);
            } catch (Exception e) {
                log.error("Error update processing: {}", update.getUpdateId(), e);
            }
        }
    }

    public void onUpdateReceived(Update update) {
        try {
            if (update == null) {
                log.warn("Received null update, skipping");
                return;
            }
            commonUpdateProcessor.process(update);
        } catch (Exception e) {
            exceptionHandler.handleException(update, e);
        }
    }

    @PostConstruct
    public void start() {
        botsApplication = new TelegramBotsLongPollingApplication();
        try {
            botSession = botsApplication.registerBot(botConfig.getBotToken(), this);
            log.info("Bot {} successfully started", botConfig.getBotName());
        } catch (Exception e) {
            log.error("Start bot error", e);
            throw new RuntimeException("Failed to launch the bot", e);
        }
    }

    @PreDestroy
    public void stop() {
        try {
            if (botSession != null && botSession.isRunning()) {
                botSession.stop();
                log.debug("Bot session stopped");
            }
            if (botsApplication != null) {
                botsApplication.close();
                log.debug("Telegram app closed");
            }
            log.info("Bot resources cleared.");
        } catch (Exception e) {
            log.error("Bot resources clearing error", e);
        }
    }

}