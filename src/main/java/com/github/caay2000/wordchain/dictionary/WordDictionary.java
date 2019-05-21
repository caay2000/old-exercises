package com.github.caay2000.wordchain.dictionary;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class WordDictionary implements Dictionary {

    private final Map<Integer, Set<String>> words;

    public WordDictionary(Set<String> dictionaryContent) {

        this.words = new LinkedHashMap<>();
        dictionaryContent.forEach(this::addWord);
    }

    @Override
    public boolean containsWord(String word) {
        return Objects.nonNull(word) &&
                !word.isEmpty() &&
                getWordsOfSize(word.length()).contains(word.toUpperCase());
    }

    @Override
    public Set<String> getWordsOfSize(int size) {
        Set<String> itemsOfSize = this.words.get(size);
        if (Objects.isNull(itemsOfSize)) {
            itemsOfSize = new LinkedHashSet<>();
            this.words.put(size, itemsOfSize);
        }
        return itemsOfSize;
    }

    private void addWord(String word) {
        Set<String> wordsOfSize = getWordsOfSize(word.length());
        wordsOfSize.add(word.toUpperCase());
        this.words.put(word.length(), wordsOfSize);
    }
}
