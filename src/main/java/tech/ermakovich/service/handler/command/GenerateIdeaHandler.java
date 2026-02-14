package tech.ermakovich.service.handler.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.entity.User;
import tech.ermakovich.service.AnswerGenerator;
import tech.ermakovich.service.MessageSender;
import tech.ermakovich.service.UserService;

import static tech.ermakovich.utils.BotCommands.GENERATE_IDEA_BTN;

@Slf4j
@RequiredArgsConstructor
@Service
public class GenerateIdeaHandler implements CommandHandler {
    private final AnswerGenerator answerGenerator;
    private final MessageSender messageSender;
    private final UserService userService;

    @Override
    public void handle(long chatId, Update update) {
        User user = userService.getOrCreate(chatId);

        String message = answerGenerator.generate(chatId, "IT", 1);
        messageSender.sendMessage(chatId, message);
    }

    @Override
    public String getCommandMessage() {
        return GENERATE_IDEA_BTN;
    }
}
