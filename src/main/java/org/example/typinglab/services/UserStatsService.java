package org.example.typinglab.services;

import org.example.typinglab.dto.UserStatsDTO;
import org.example.typinglab.entity.User;
import org.example.typinglab.entity.UserStats;
import org.example.typinglab.repo.UserRepository;
import org.example.typinglab.repo.UserStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserStatsService {

    @Autowired
    private UserStatsRepository userStatsRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Optional<UserStats> findById(int userId) {
        return userStatsRepository.findByUserId(userId);
    }

    @Transactional
    public UserStatsDTO updateUserStats(int userId, UserStatsDTO statsDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        UserStats stats = userStatsRepository.findByUserId(userId)
                .orElseGet(() -> {
                    UserStats newStats = new UserStats();
                    newStats.setUser(user);
                    newStats.setTotalCharactersTyped(0);
                    newStats.setTotalMissClick(0);
                    newStats.setCompletedTrainings(0);
                    newStats.setAverageTypingSpeed(0.0);
                    newStats.setMaxTypingSpeed(0.0);
                    return newStats;
                });

        int totalTrainings = stats.getCompletedTrainings() + 1;
        stats.setCompletedTrainings(totalTrainings);

        int newCharactersTyped = statsDTO.getTotalCharactersTyped() != null ? statsDTO.getTotalCharactersTyped() : 0;
        stats.setTotalCharactersTyped(stats.getTotalCharactersTyped() + newCharactersTyped);

        int newMissClicks = statsDTO.getTotalMissClick() != null ? statsDTO.getTotalMissClick() : 0;
        stats.setTotalMissClick(stats.getTotalMissClick() + newMissClicks);

        if (newCharactersTyped > 0) {
            stats.setAverageTypingSpeed(
                    ((stats.getAverageTypingSpeed() * (totalTrainings - 1)) + (double) newCharactersTyped) / totalTrainings
            );
            stats.setMaxTypingSpeed(Math.max(stats.getMaxTypingSpeed(), (double) newCharactersTyped));
        }

        UserStats saved = userStatsRepository.save(stats);

        double missClickPercentage = saved.getTotalCharactersTyped() > 0
                ? (double) saved.getTotalMissClick() / saved.getTotalCharactersTyped() * 100
                : 0.0;

        return new UserStatsDTO(
                saved.getUser().getId(),
                0.0,
                saved.getAverageTypingSpeed(),
                saved.getCompletedTrainings(),
                saved.getMaxTypingSpeed(),
                saved.getTotalCharactersTyped(),
                saved.getTotalMissClick(),
                missClickPercentage
        );
    }
}