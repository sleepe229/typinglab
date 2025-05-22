package org.example.typinglab.dto;

import java.util.List;

public class WordsDTO {
    private List<String> words;

    public WordsDTO(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
