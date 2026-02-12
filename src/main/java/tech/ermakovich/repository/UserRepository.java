package tech.ermakovich.repository;

import org.springframework.stereotype.Repository;
import tech.ermakovich.model.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Map<Long, User> USERS = new HashMap<>();

    public Optional<User> findById(Long telegramId) {
        return Optional.of(USERS.get(telegramId));
    }

    public User save(User newUser) {
        return USERS.put(newUser.getTelegramId(), newUser);
    }
}
