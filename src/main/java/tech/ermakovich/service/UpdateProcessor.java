package tech.ermakovich.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
public class UpdateProcessor {

    public void process(Update update){
        log.info("Update processed: {}", update.getUpdateId());
    }
}
