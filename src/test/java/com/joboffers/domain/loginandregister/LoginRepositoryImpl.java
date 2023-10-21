package com.joboffers.domain.loginandregister;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LoginRepositoryImpl implements LoginRepository {

    Map<String, User> database = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(database.get(username));
    }

    @Override
    public User save(User user) {
        UUID userId = UUID.randomUUID();
        User userToSave = new User(userId.toString(), user.username(), user.password());
        database.put(userToSave.username(), userToSave);
        return userToSave;
    }
}
