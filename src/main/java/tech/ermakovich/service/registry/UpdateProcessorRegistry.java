package tech.ermakovich.service.registry;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tech.ermakovich.model.enums.UpdateType;
import tech.ermakovich.service.processor.update.UpdateProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class UpdateProcessorRegistry {

    private final List<UpdateProcessor> updateProcessors;

    @Bean
    public Map<UpdateType, UpdateProcessor> updateProcessorMap() {
        Map<UpdateType, UpdateProcessor> map = new HashMap<>();
        for (UpdateProcessor updateProcessor : updateProcessors) {
            map.put(updateProcessor.getUpdateType(), updateProcessor);
        }
        return map;
    }
}
