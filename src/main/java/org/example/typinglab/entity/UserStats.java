package org.example.typinglab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_stats")
public class UserStats {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "average_training_speed")
    private Double averageTrainingSpeed;

    @Column(name = "average_typing_speed")
    private Double averageTypingSpeed;

    @Column(name = "completed_trainings")
    private Integer completedTrainings;

    @Column(name = "max_typing_speed")
    private Double maxTypingSpeed;

    @Column(name = "total_characters_typed")
    private Integer totalCharactersTyped;

    @Column(name = "missclick_percentage")
    private Double missclickPercentage;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public UserStats() {}

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
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
