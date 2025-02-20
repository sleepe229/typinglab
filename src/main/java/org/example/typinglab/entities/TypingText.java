package org.example.typinglab.entities;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TypingText extends BaseEntity {
    private String content;
    private String difficulty;
    private String language;

    @OneToMany(mappedBy = "text", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TypingTest> tests;

    protected TypingText() {}

    public TypingText(String content, String difficulty, String language, List<TypingTest> tests) {
        this.content = content;
        this.difficulty = difficulty;
        this.language = language;
        this.tests = tests;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<TypingTest> getTests() {
        return tests;
    }

    public void setTests(List<TypingTest> tests) {
        this.tests = tests;
    }
}
