package tech.ermakovich.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.ermakovich.model.entity.Budget;
import tech.ermakovich.model.entity.DeliverySettings;
import tech.ermakovich.model.entity.Subscriptions;
import tech.ermakovich.model.entity.User;
import tech.ermakovich.model.enums.Currency;
import tech.ermakovich.model.exception.UserNotFoundException;
import tech.ermakovich.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getOrCreate(Long telegramId) {
        return userRepository.findById(telegramId)
                .orElseGet(() -> {
                    log.info("Создание нового пользователя: {}", telegramId);
                    User newUser = User.builder()
                            .telegramId(telegramId)
                            .subscriptions(createDefaultSubscriptions())
                            .budget(createDefaultBudget())
                            .deliverySettings(createDefaultDeliverySettings())
                            .savedIdeas(new ArrayList<>())
                            .build();
                    return userRepository.save(newUser);
                });
    }


    public User getById(Long telegramId) {
        return userRepository.findById(telegramId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден: " + telegramId));
    }


    private Subscriptions createDefaultSubscriptions() {
        Subscriptions subs = new Subscriptions();
        subs.setSources(List.of("1"));
        subs.setCategories(List.of("tech", "business"));
        subs.setKeywords(new ArrayList<>());
        return subs;
    }

    private Budget createDefaultBudget() {
        Budget budget = new Budget();
        budget.setMin(0);
        budget.setMax(1_000_000);
        budget.setCurrency(Currency.BYN);
        return budget;
    }

    private DeliverySettings createDefaultDeliverySettings() {
        DeliverySettings ds = new DeliverySettings();
        ds.setFormat("short");
        ds.setFrequency("instant");
        ds.setTime("09:00");
        return ds;
    }
}
