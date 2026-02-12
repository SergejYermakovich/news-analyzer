package tech.ermakovich.utils;

import lombok.experimental.UtilityClass;
import tech.ermakovich.model.dto.ai.ChatResponse;
import tech.ermakovich.model.exception.DeepSeekIntegrationException;

@UtilityClass
public class ValidationUtils {
    public static void validateDeepSeekChatResponse(ChatResponse chatResponse) {
        if (chatResponse == null) {
            throw new DeepSeekIntegrationException("Null response from DeepSeek API");
        }

        if (chatResponse.getChoices() == null || chatResponse.getChoices().isEmpty()) {
            throw new DeepSeekIntegrationException("No choices in DeepSeek response");
        }

        var firstChoice = chatResponse.getChoices().get(0);
        if (firstChoice == null || firstChoice.getMessage() == null || firstChoice.getMessage().getContent() == null) {
            throw new DeepSeekIntegrationException("Invalid choice structure in DeepSeek response");
        }
    }
}
