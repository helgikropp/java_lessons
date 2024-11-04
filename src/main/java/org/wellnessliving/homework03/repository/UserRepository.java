package org.wellnessliving.homework03.repository;

import org.wellnessliving.homework03.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);

    Optional<User> findById(Long id);
}
