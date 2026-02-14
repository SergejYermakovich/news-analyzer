package tech.ermakovich.service.registry;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ermakovich.service.handler.command.CommandHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Configuration
public class CommandHandlerRegistry {
    private final List<CommandHandler> commandHandlers;

    @Bean
    public Map<String, CommandHandler> commandHandlerMap() {
        Map<String, CommandHandler> map = new HashMap<>();
        for (CommandHandler commandHandler : commandHandlers) {
            map.put(commandHandler.getCommandMessage(), commandHandler);
        }
        return map;
    }
}
