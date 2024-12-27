package com.jessbarbosa.apps.pawkit_core_service.repository;

import com.jessbarbosa.apps.pawkit_core_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
