package org.example.typinglab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class WordService {

    private static final int WORDS_TO_RETURN = 150;

    @Autowired
    private WordLoader wordLoader;

    public List<String> getRandomWords(int level) {
        Map<Integer, List<String>> wordMap = wordLoader.getWordMap();
        List<String> words = wordMap.get(level);
        if (words == null || words.isEmpty()) {
            throw new RuntimeException("No words found for level: " + level);
        }
        if (words.size() < WORDS_TO_RETURN) {
            throw new RuntimeException("Not enough words for level " + level + ": found " + words.size() + ", need " + WORDS_TO_RETURN);
        }

        List<String> shuffledWords = new ArrayList<>(words);
        Collections.shuffle(shuffledWords);
        return shuffledWords.subList(0, WORDS_TO_RETURN);
    }
}
