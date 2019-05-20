package com.github.caay2000.wordchain.application.dictionary;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class WordDictionary implements Dictionary {

    private final File dictionaryFile;

    protected final Map<Integer, Set<String>> words;
    private boolean loaded = false;

    public WordDictionary(File dictionaryFile) {
        this.words = new LinkedHashMap<>();
        this.dictionaryFile = dictionaryFile;
    }

    @Override
    public void load() {
        if (!loaded) {
            loadDictionaryFromFile();
        }
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

    private void loadDictionaryFromFile() {
        try (Scanner sc = new Scanner(dictionaryFile)) {
            while (sc.hasNextLine()) {
                addWord(sc.nextLine());
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            throw new DictionaryException(e);
        }
        this.loaded = true;
    }
}
