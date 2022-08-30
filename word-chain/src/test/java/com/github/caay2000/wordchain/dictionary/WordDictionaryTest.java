package com.github.caay2000.wordchain.dictionary;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
public class WordDictionaryTest {

    @Test
    public void contains() {

        Set<String> words = Stream.of("AA").collect(toSet());

        Dictionary dictionary = new WordDictionary(words);

        Assert.assertTrue("should be true", dictionary.containsWord("AA"));
        Assert.assertFalse("should be true", dictionary.containsWord("BB"));
    }

    @Test
    public void noWordsOfSize() {

        Set<String> words = Stream.of("AA", "ABCD").collect(toSet());

        Dictionary dictionary = new WordDictionary(words);

        Assert.assertTrue("should be empty", dictionary.getWordsOfSize(3).isEmpty());
    }

    @Test
    public void getWordsOfSize() {

        Set<String> words = Stream.of("AA", "BB", "CC", "ABC").collect(toSet());

        Dictionary dictionary = new WordDictionary(words);

        Assert.assertEquals("should be 3", 3, dictionary.getWordsOfSize(2).size());
    }
}