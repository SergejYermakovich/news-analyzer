package tech.ermakovich.service.processor.callback;

import lombok.Builder;

@Builder
public record ToggleUpdate(
        String sourceId,
        Boolean isOn
) {
}
