package com.github.caay2000.wordchain;

import java.util.Set;
import com.github.caay2000.wordchain.application.WordChain;
import com.github.caay2000.wordchain.application.dictionary.Dictionary;
import com.github.caay2000.wordchain.application.dictionary.WordDictionary;
import com.github.caay2000.wordchain.io.SystemInput;
import com.github.caay2000.wordchain.io.SystemReader;
import com.github.caay2000.wordchain.io.SystemWriter;

public class WordChainSolver {

    private SystemReader systemReader;
    private SystemWriter systemWriter;

    public static void main(String[] args) {
        // TODO
    }

    public WordChainSolver(SystemReader systemReader, SystemWriter systemWriter) {
        this.systemReader = systemReader;
        this.systemWriter = systemWriter;
    }

    public void execute() {

        SystemInput systemInput = systemReader.readInput();

        Dictionary dictionary = new WordDictionary(systemInput.getDictionaryFile());
        WordChain wordChain = new WordChain(dictionary);

        for (SystemInput.Pair pair : systemInput.getPairs()) {
            Set<String> solution = wordChain.solve(pair.getStart().toUpperCase(), pair.getEnd().toUpperCase());
            printResult(solution);
        }
    }

    private void printResult(Set<String> solution) {
        if (solution.size() > 0) {
            systemWriter.write(String.format("YES %s", solution.toString()));
        } else {
            systemWriter.write("NO");
        }
    }
}
