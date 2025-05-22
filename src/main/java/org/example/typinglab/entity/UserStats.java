package org.example.typinglab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_stats")
public class UserStats {

 @Id
@Column(name = "user_id")
private int userId;

@OneToOne
@MapsId
@JoinColumn(name = "user_id")
private User user;
    @Column(name = "average_training_speed")
    private double averageTrainingSpeed;

    @Column(name = "average_typing_speed")
    private double averageTypingSpeed;

    @Column(name = "completed_trainings")
    private int completedTrainings;

    @Column(name = "max_typing_speed")
    private double maxTypingSpeed;

    @Column(name = "total_miss_click")
    private int totalMissClick;

    @Column(name = "total_characters_typed")
    private int totalCharactersTyped;

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public double getAverageTrainingSpeed() { return averageTrainingSpeed; }
    public void setAverageTrainingSpeed(double averageTrainingSpeed) { this.averageTrainingSpeed = averageTrainingSpeed; }
    public double getAverageTypingSpeed() { return averageTypingSpeed; }
    public void setAverageTypingSpeed(double averageTypingSpeed) { this.averageTypingSpeed = averageTypingSpeed; }
    public int getCompletedTrainings() { return completedTrainings; }
    public void setCompletedTrainings(int completedTrainings) { this.completedTrainings = completedTrainings; }
    public double getMaxTypingSpeed() { return maxTypingSpeed; }
    public void setMaxTypingSpeed(double maxTypingSpeed) { this.maxTypingSpeed = maxTypingSpeed; }
    public int getTotalMissClick() { return totalMissClick; }
    public void setTotalMissClick(int totalMissClick) { this.totalMissClick = totalMissClick; }
    public int getTotalCharactersTyped() { return totalCharactersTyped; }
    public void setTotalCharactersTyped(int totalCharactersTyped) { this.totalCharactersTyped = totalCharactersTyped; }
}