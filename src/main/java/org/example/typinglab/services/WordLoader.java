package org.example.typinglab.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WordLoader {

    private final Map<Integer, List<String>> wordMap = new HashMap<>();

    @Value("${word.files.level1:words/words_3_5.txt}")
    private String level1File;

    @Value("${word.files.level2:words/words_5_6.txt}")
    private String level2File;

    @Value("${word.files.level3:words/words_7_9.txt}")
    private String level3File;

    @Value("${word.files.level4:words/words_9_15.txt}")
    private String level4File;

    @PostConstruct
    public void init() {
        loadWords(1, level1File);
        loadWords(2, level2File);
        loadWords(3, level3File);
        loadWords(4, level4File);
    }

    private void loadWords(int level, String filePath) {
        List<String> words = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            assert is != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String trimmed = line.trim();
                    if (!trimmed.isEmpty()) {
                        words.add(trimmed);
                    }
                }
                wordMap.put(level, words);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load words from file: " + filePath, e);
        }
    }

    public Map<Integer, List<String>> getWordMap() {
        return wordMap;
    }
}