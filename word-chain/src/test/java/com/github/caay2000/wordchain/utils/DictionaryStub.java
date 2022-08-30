package com.github.caay2000.wordchain.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import com.github.caay2000.wordchain.dictionary.Dictionary;

public class DictionaryStub implements Dictionary {

    private final Set<String> words;

    public DictionaryStub(String... words) {
        this.words = Arrays.stream(words).collect(Collectors.toSet());
    }

    @Override
    public boolean containsWord(String word) {
        throw new RuntimeException("not implemented code in stub");
    }

    @Override
    public Set<String> getWordsOfSize(int size) {
        return words;
    }
}
