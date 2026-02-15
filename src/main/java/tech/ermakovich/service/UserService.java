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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getOrCreate(Long id) {
        return userRepository.findById(id)
                .orElseGet(() -> {
                    log.info("Создание нового пользователя: {}", id);
                    User newUser = User.builder()
                            .telegramId(id)
                            .subscriptions(createDefaultSubscriptions())
                            .budget(createDefaultBudget())
                            .deliverySettings(createDefaultDeliverySettings())
                            .savedIdeas(new ArrayList<>())
                            .build();
                    return userRepository.save(newUser);
                });
    }


    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден: " + id));
    }


    private Subscriptions createDefaultSubscriptions() {
        Set<String> sources = new HashSet<>();
        sources.add("1");

        Subscriptions subs = new Subscriptions();
        subs.setSources(sources);
        subs.setCategories(Set.of("tech", "business"));
        subs.setKeywords(new HashSet<>());
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
