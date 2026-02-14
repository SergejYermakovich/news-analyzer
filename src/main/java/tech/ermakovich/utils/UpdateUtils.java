package tech.ermakovich.utils;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.enums.UpdateType;

@UtilityClass
public class UpdateUtils {
    public UpdateType getUpdateType(Update update) {
        if (isSuccessfulPayment(update)) {
            return UpdateType.SUCCESSFUL_PAYMENT;
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            return UpdateType.MESSAGE;
        } else if (update.hasCallbackQuery()) {
            return UpdateType.CALLBACK_QUERY;
        } else if (update.hasPreCheckoutQuery()) {
            return UpdateType.PRE_CHECKOUT_QUERY;
        } else if (update.hasMessage() && update.getMessage().hasVoice()) {
            return UpdateType.VOICE_MESSAGE;
        } else {
            return UpdateType.DEFAULT;
        }
    }

    public static boolean isSuccessfulPayment(Update update) {
        return update.hasMessage() && update.getMessage().hasSuccessfulPayment();
    }
}
