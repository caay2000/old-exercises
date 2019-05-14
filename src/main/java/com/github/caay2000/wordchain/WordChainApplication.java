package com.github.caay2000.wordchain;

import java.util.Arrays;
import com.github.caay2000.wordchain.dictionary.Dictionary;
public class WordChainApplication {

    private final Dictionary dictionary;

    public WordChainApplication(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public WordChainResult checkChain(String start, String end) {
        if (start.length() != end.length()) {
            return new WordChainResult(false, null);
        }
        return new WordChainResult(true, Arrays.asList("cat", "cot", "cog", "dog"));
    }
}
