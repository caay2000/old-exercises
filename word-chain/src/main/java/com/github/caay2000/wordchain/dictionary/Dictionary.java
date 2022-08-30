package com.github.caay2000.wordchain.dictionary;

import java.util.Set;

public interface Dictionary {

    boolean containsWord(String word);

    Set<String> getWordsOfSize(int size);
}