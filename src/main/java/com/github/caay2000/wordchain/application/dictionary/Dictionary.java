package com.github.caay2000.wordchain.application.dictionary;

import java.util.Set;

public interface Dictionary {

    void load();

    boolean containsWord(String word);

    Set<String> getWordsOfSize(int size);
}