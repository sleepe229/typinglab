package org.example.typinglab.repo;

import org.example.typinglab.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatsRepository extends JpaRepository<UserStats, String> {
}
