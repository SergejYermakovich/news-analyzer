package tech.ermakovich.service.registry;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ermakovich.service.processor.callback.CallbackDataProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Configuration
public class CallbackDataProcessorRegistry {
    private final List<CallbackDataProcessor> callbackDataProcessors;

    @Bean
    public Map<String, CallbackDataProcessor> callbackDataProcessorMap() {
        Map<String, CallbackDataProcessor> map = new HashMap<>();
        for (CallbackDataProcessor callbackDataProcessor : callbackDataProcessors) {
            map.put(callbackDataProcessor.callbackData(), callbackDataProcessor);
        }
        return map;
    }
}
