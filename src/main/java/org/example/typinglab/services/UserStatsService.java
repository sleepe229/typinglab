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
        return userStatsRepository.findById(userId);
    }


    @Transactional
    public UserStatsDTO updateUserStats(int userId, UserStatsDTO statsDTO) {
        // Validate user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        // Validate input
        if (statsDTO.getAverageTrainingSpeed() != null && statsDTO.getAverageTrainingSpeed() < 0) {
            throw new RuntimeException("Average training speed cannot be negative");
        }
        if (statsDTO.getAverageTypingSpeed() != null && statsDTO.getAverageTypingSpeed() < 0) {
            throw new RuntimeException("Average typing speed cannot be negative");
        }
        if (statsDTO.getCompletedTrainings() != null && statsDTO.getCompletedTrainings() < 0) {
            throw new RuntimeException("Completed trainings cannot be negative");
        }
        if (statsDTO.getMaxTypingSpeed() != null && statsDTO.getMaxTypingSpeed() < 0) {
            throw new RuntimeException("Max typing speed cannot be negative");
        }
        if (statsDTO.getTotalCharactersTyped() != null && statsDTO.getTotalCharactersTyped() < 0) {
            throw new RuntimeException("Total characters typed cannot be negative");
        }
        if (statsDTO.getMissclickPercentage() != null && (statsDTO.getMissclickPercentage() < 0 || statsDTO.getMissclickPercentage() > 100)) {
            throw new RuntimeException("Missclick percentage must be between 0 and 100");
        }

        // Retrieve or create UserStats
        UserStats stats = userStatsRepository.findById(userId)
                .orElse(new UserStats());

        stats.setUser(user);
        stats.setAverageTrainingSpeed(0.0);
        stats.setAverageTypingSpeed(0.0);
        stats.setCompletedTrainings(0);
        stats.setMaxTypingSpeed(0.0);
        stats.setTotalCharactersTyped(0);
        stats.setMissclickPercentage(0.0);


        // Update fields with calculations
        if (statsDTO.getAverageTrainingSpeed() != null) {
            int currentTrainings = stats.getCompletedTrainings();
            double currentAvg = stats.getAverageTrainingSpeed() != null ? stats.getAverageTrainingSpeed() : 0.0;
            stats.setAverageTrainingSpeed(
                    (currentAvg * currentTrainings + statsDTO.getAverageTrainingSpeed()) / (currentTrainings + 1)
            );
        }
        if (statsDTO.getAverageTypingSpeed() != null) {
            int currentTrainings = stats.getCompletedTrainings();
            double currentAvg = stats.getAverageTypingSpeed() != null ? stats.getAverageTypingSpeed() : 0.0;
            stats.setAverageTypingSpeed(
                    (currentAvg * currentTrainings + statsDTO.getAverageTypingSpeed()) / (currentTrainings + 1)
            );
        }
        if (statsDTO.getCompletedTrainings() != null) {
            stats.setCompletedTrainings(stats.getCompletedTrainings() + statsDTO.getCompletedTrainings());
        }
        if (statsDTO.getMaxTypingSpeed() != null) {
            double currentMax = stats.getMaxTypingSpeed() != null ? stats.getMaxTypingSpeed() : 0.0;
            stats.setMaxTypingSpeed(Math.max(currentMax, statsDTO.getMaxTypingSpeed()));
        }
        if (statsDTO.getTotalCharactersTyped() != null) {
            stats.setTotalCharactersTyped(
                    (stats.getTotalCharactersTyped() != null ? stats.getTotalCharactersTyped() : 0) + statsDTO.getTotalCharactersTyped()
            );
        }
        if (statsDTO.getMissclickPercentage() != null) {
            int currentTrainings = stats.getCompletedTrainings();
            double currentMissclick = stats.getMissclickPercentage() != null ? stats.getMissclickPercentage() : 0.0;
            stats.setMissclickPercentage(
                    (currentMissclick * currentTrainings + statsDTO.getMissclickPercentage()) / (currentTrainings + 1)
            );
        }

        // Save and return DTO
        UserStats savedStats = userStatsRepository.save(stats);
        return new UserStatsDTO(
                savedStats.getUserId(),
                savedStats.getAverageTrainingSpeed(),
                savedStats.getAverageTypingSpeed(),
                savedStats.getCompletedTrainings(),
                savedStats.getMaxTypingSpeed(),
                savedStats.getTotalCharactersTyped(),
                savedStats.getMissclickPercentage()
        );
    }
}
