package tech.ermakovich.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class User {
    private Long telegramId;
    private List<String> savedIdeas;
    private Budget budget;
    private Subscriptions subscriptions;
    private DeliverySettings deliverySettings;
}
