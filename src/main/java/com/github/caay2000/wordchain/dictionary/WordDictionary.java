package com.github.caay2000.wordchain.dictionary;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordDictionary implements Dictionary {

    private Set<String> words;

    private WordDictionary() {
        this.words = new HashSet<>();
    }

    public static Dictionary loadFrom(String file) throws IOException {
        WordDictionary dictionary = new WordDictionary();
        try (Scanner sc = new Scanner(new File(file), "UTF-8")) {
            while (sc.hasNextLine()) {
                dictionary.words.add(sc.nextLine());
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }
        return dictionary;
    }
}
