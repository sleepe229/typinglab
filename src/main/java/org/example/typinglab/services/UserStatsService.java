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
        return userStatsRepository.findByUserId(userId); // Use findByUserId to align with user_id column
    }

    @Transactional
    public UserStatsDTO updateUserStats(int userId, UserStatsDTO statsDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        UserStats stats = userStatsRepository.findByUserId(userId)
                .orElseGet(() -> {
                    UserStats newStats = new UserStats();
                    newStats.setUser(user);
                    return newStats;
                });

        int additionalTrainings = statsDTO.getCompletedTrainings() != null ? statsDTO.getCompletedTrainings() : 0;
        int totalTrainings = stats.getCompletedTrainings() + additionalTrainings;

        if (additionalTrainings > 0) {
            if (statsDTO.getAverageTrainingSpeed() != null) {
                stats.setAverageTrainingSpeed(
                        (stats.getAverageTrainingSpeed() * stats.getCompletedTrainings()
                                + statsDTO.getAverageTrainingSpeed() * additionalTrainings)
                                / totalTrainings);
            }

            if (statsDTO.getAverageTypingSpeed() != null) {
                stats.setAverageTypingSpeed(
                        (stats.getAverageTypingSpeed() * stats.getCompletedTrainings()
                                + statsDTO.getAverageTypingSpeed() * additionalTrainings)
                                / totalTrainings);
            }

            if (statsDTO.getMissclickPercentage() != null) {
                stats.setMissclickPercentage(
                        (stats.getMissclickPercentage() * stats.getCompletedTrainings()
                                + statsDTO.getMissclickPercentage() * additionalTrainings)
                                / totalTrainings);
            }

            stats.setCompletedTrainings(totalTrainings);
        }

        if (statsDTO.getMaxTypingSpeed() != null) {
            stats.setMaxTypingSpeed(Math.max(stats.getMaxTypingSpeed(), statsDTO.getMaxTypingSpeed()));
        }

        if (statsDTO.getTotalCharactersTyped() != null) {
            stats.setTotalCharactersTyped(
                    stats.getTotalCharactersTyped() + statsDTO.getTotalCharactersTyped());
        }

        UserStats saved = userStatsRepository.save(stats);

        return new UserStatsDTO(
                saved.getUser().getId(),
                saved.getAverageTrainingSpeed(),
                saved.getAverageTypingSpeed(),
                saved.getCompletedTrainings(),
                saved.getMaxTypingSpeed(),
                saved.getTotalCharactersTyped(),
                saved.getMissclickPercentage()
        );
    }
}