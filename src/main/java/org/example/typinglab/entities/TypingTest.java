package org.example.typinglab.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "typing_test")
public class TypingTest extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "text_id")
    private TypingText text;
    private int speed;
    private double accuracy;
    private int errors;
    private double score;

    protected TypingTest() {}

    public TypingTest(User user, TypingText text, int speed, double accuracy, int errors, double score) {
        this.user = user;
        this.text = text;
        this.speed = speed;
        this.accuracy = accuracy;
        this.errors = errors;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public TypingText getText() {
        return text;
    }

    @Column(name = "speed")
    public int getSpeed() {
        return speed;
    }

    @Column(name = "accuracy")
    public double getAccuracy() {
        return accuracy;
    }

    @Column(name = "errors")
    public int getErrors() {
        return errors;
    }

    @Column(name = "score")
    public double getScore() {
        return score;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setText(TypingText text) {
        this.text = text;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
