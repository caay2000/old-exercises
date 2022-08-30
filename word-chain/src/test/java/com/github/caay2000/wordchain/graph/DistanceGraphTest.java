package com.github.caay2000.wordchain.graph;

import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import com.github.caay2000.wordchain.dictionary.Dictionary;
import com.github.caay2000.wordchain.utils.DictionaryStub;

public class DistanceGraphTest {

    @Test
    public void emptyDictionary() {

        Dictionary dictionary = new DictionaryStub();

        DistanceGraph sut = new DistanceGraph(dictionary, "", "");

        Assert.assertTrue("candidates should be empty", sut.getAllCandidates().isEmpty());
    }

    @Test
    public void oneWordWithSolutionDictionary() {

        Dictionary dictionary = new DictionaryStub("aa");

        DistanceGraph sut = new DistanceGraph(dictionary, "aa", "aa");

        Assert.assertTrue("candidates should be empty", sut.getAllCandidates().isEmpty());
    }

    @Test
    public void oneDistanceDictionary() {

        DictionaryStub dictionary = new DictionaryStub("aa", "ab", "ac");

        DistanceGraph sut = new DistanceGraph(dictionary, "aa", "ad");

        Set<String> candidates = sut.getAllCandidates();
        Assert.assertEquals("should be 2 words in the candidates, sorted correctly, ab and ac", 2, candidates.size());
        Assert.assertEquals("ab", candidates.toArray(new String[candidates.size()])[0]);
        Assert.assertEquals("ac", candidates.toArray(new String[candidates.size()])[1]);
    }

    @Test
    public void multipleDistancesDictionary() {

        Dictionary dictionary = new DictionaryStub("aaa", "aab", "abb", "bbb");

        DistanceGraph sut = new DistanceGraph(dictionary, "aaa", "bbb");

        Set<String> candidates = sut.getAllCandidates();
        Assert.assertEquals("should be 1 word in the candidates, aab", 1, candidates.size());
        Assert.assertEquals("aab", candidates.toArray(new String[candidates.size()])[0]);
    }
}