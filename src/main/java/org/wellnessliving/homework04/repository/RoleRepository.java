package org.wellnessliving.homework04.repository;

import org.wellnessliving.homework04.entity.Role;
import java.util.Optional;

public interface RoleRepository {
    Role create(Role role);

    Optional<Role> findById(Long id);
}
