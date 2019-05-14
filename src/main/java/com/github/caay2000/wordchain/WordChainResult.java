package com.github.caay2000.wordchain;

import java.util.List;
public class WordChainResult {

    private boolean chained;
    private List<String> chain;

    public WordChainResult(boolean chained, List<String> chain) {
        this.chained = chained;
        this.chain = chain;
    }

    public boolean isChained() {
        return chained;
    }

    public List<String> getChain() {
        return chain;
    }
}
