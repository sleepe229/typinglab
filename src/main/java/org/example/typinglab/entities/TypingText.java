package org.example.typinglab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "text")
public class TypingText extends BaseEntity {
    private String content;
    private String difficulty;
    private String language;
    @JsonIgnore
    @OneToMany(mappedBy = "text", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TypingTest> tests = new ArrayList<>();


    protected TypingText() {}

    public TypingText(String content, String difficulty, String language, List<TypingTest> tests) {
        this.content = content;
        this.difficulty = difficulty;
        this.language = language;
        this.tests = tests;
    }

    public List<TypingTest> getTests() {
        return tests;
    }

    public String getContent() {
        return content;
    }

    @Column(name = "difficulty", nullable = false)
    public String getDifficulty() {
        return difficulty;
    }

    @Column(name = "language", nullable = false)
    public String getLanguage() {
        return language;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTests(List<TypingTest> tests) {
        this.tests = tests;
    }
}
