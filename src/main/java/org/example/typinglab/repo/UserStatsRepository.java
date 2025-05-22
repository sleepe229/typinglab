package org.example.typinglab.repo;

import org.example.typinglab.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatsRepository extends JpaRepository<UserStats, Integer> {
    Optional<UserStats> findByUserId(int userId);
}
