package tech.ermakovich.service.handler.command;

import static tech.ermakovich.utils.BotCommands.MY_PROFILE_BTN;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.entity.User;
import tech.ermakovich.service.MessageSender;
import tech.ermakovich.service.UserService;

import static tech.ermakovich.utils.ResponseConsts.USER_PROFILE;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetProfileHandler implements CommandHandler {
    private final UserService userService;
    private final MessageSender messageSender;

    @Override
    public void handle(long chatId, Update update) {
        User user = userService.getById(chatId);
        messageSender.sendMessage(chatId,
                String.format(
                        USER_PROFILE,
                        user.getSubscriptions().getSources(),
                        user.getBudget().getMax() + " " + user.getBudget().getCurrency(),
                        user.getDeliverySettings().getFrequency(),
                        user.getDeliverySettings().getFormat()
                )
        );
    }

    @Override
    public String getCommandMessage() {
        return MY_PROFILE_BTN;
    }
}
