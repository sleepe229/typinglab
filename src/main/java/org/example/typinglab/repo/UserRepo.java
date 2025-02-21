package org.example.typinglab.repo;

import org.example.typinglab.entities.User;
import org.example.typinglab.repo.base.BaseRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends BaseRepo<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
