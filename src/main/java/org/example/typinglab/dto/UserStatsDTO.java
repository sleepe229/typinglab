package org.example.typinglab.dto;

public class UserStatsDTO {
    private int userId;
    private Double averageTrainingSpeed;
    private Double averageTypingSpeed;
    private Integer completedTrainings;
    private Double maxTypingSpeed;
    private Integer totalCharactersTyped;
    private Double missclickPercentage;

    public UserStatsDTO(int userId, Double averageTrainingSpeed, Double averageTypingSpeed, Integer completedTrainings,
                        Double maxTypingSpeed, Integer totalCharactersTyped, Double missclickPercentage) {
        this.userId = userId;
        this.averageTrainingSpeed = averageTrainingSpeed;
        this.averageTypingSpeed = averageTypingSpeed;
        this.completedTrainings = completedTrainings;
        this.maxTypingSpeed = maxTypingSpeed;
        this.totalCharactersTyped = totalCharactersTyped;
        this.missclickPercentage = missclickPercentage;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Double getAverageTrainingSpeed() { return averageTrainingSpeed; }
    public void setAverageTrainingSpeed(Double averageTrainingSpeed) { this.averageTrainingSpeed = averageTrainingSpeed; }
    public Double getAverageTypingSpeed() { return averageTypingSpeed; }
    public void setAverageTypingSpeed(Double averageTypingSpeed) { this.averageTypingSpeed = averageTypingSpeed; }
    public Integer getCompletedTrainings() { return completedTrainings; }
    public void setCompletedTrainings(Integer completedTrainings) { this.completedTrainings = completedTrainings; }
    public Double getMaxTypingSpeed() { return maxTypingSpeed; }
    public void setMaxTypingSpeed(Double maxTypingSpeed) { this.maxTypingSpeed = maxTypingSpeed; }
    public Integer getTotalCharactersTyped() { return totalCharactersTyped; }
    public void setTotalCharactersTyped(Integer totalCharactersTyped) { this.totalCharactersTyped = totalCharactersTyped; }
    public Double getMissclickPercentage() { return missclickPercentage; }
    public void setMissclickPercentage(Double missclickPercentage) { this.missclickPercentage = missclickPercentage; }
}
