package com.github.caay2000.wordchain;

import java.io.IOException;
import java.util.Set;
import com.github.caay2000.wordchain.dictionary.Dictionary;
import com.github.caay2000.wordchain.dictionary.WordDictionary;
import com.github.caay2000.wordchain.io.SystemInput;
import com.github.caay2000.wordchain.io.SystemReader;
import com.github.caay2000.wordchain.io.SystemWriter;

public class WordChainSolver {

    private final SystemReader systemReader;
    private final SystemWriter systemWriter;

    public WordChainSolver(SystemReader systemReader, SystemWriter systemWriter) {
        this.systemReader = systemReader;
        this.systemWriter = systemWriter;
    }

    public void execute(String[] input) throws IOException {

        validateInput(input);

        SystemInput systemInput = systemReader.readInput(input[0]);
        Dictionary dictionary = new WordDictionary(systemInput.getDictionaryFileContent());
        WordChain wordChain = new WordChain(dictionary);

        for (SystemInput.Pair pair : systemInput.getPairs()) {
            Set<String> solution = wordChain.solve(pair.getStart().toUpperCase(), pair.getEnd().toUpperCase());
            printResult(solution);
        }
    }

    private void validateInput(String[] input) throws IOException {
        if (input.length != 1 || "-h".equals(input[0]) || "-help".equals(input[0])) {
            systemWriter.write("WORD CHAIN SOLVER for HEXAD");
            systemWriter.write("how to run the program:");
            systemWriter.write("java -cp wordChainSolver [path_to_your_file]");
            systemWriter.write("");
            systemWriter.write("format of your file:");
            systemWriter.write("path_to_your_dictionary_file");
            systemWriter.write("firstWord secondWord");
            systemWriter.write("");
            systemWriter.write("You can add as many pairs of words as you want");
            systemWriter.write("");
            systemWriter.write("Albert Casanovas(caay2000@gmail.com)");
            System.exit(0);
        }
    }

    private void printResult(Set<String> solution) throws IOException {
        if (!solution.isEmpty()) {
            systemWriter.write(String.format("YES %s", solution.toString()));
        } else {
            systemWriter.write("NO");
        }
    }
}
