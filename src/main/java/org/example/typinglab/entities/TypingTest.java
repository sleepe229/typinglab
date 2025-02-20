package org.example.typinglab.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class TypingTest extends BaseEntity{
    private User user;
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

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public TypingText getText() {
        return text;
    }

    public void setText(TypingText text) {
        this.text = text;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
