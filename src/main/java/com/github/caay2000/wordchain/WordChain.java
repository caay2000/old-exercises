package com.github.caay2000.wordchain;

import java.io.IOException;
import com.github.caay2000.wordchain.dictionary.WordDictionary;
import com.github.caay2000.wordchain.io.SystemReader;
import com.github.caay2000.wordchain.io.SystemWriter;

public class WordChain {

    private SystemReader systemReader;
    private SystemWriter systemWriter;

    public static void main(String[] args) {
        // TODO
    }

    public WordChain(SystemReader systemReader, SystemWriter systemWriter) {
        this.systemReader = systemReader;
        this.systemWriter = systemWriter;
    }

    public void execute(String file) throws IOException {

        String inputFile = systemReader.readInput(file);

        String[] lines = inputFile.split(System.getProperty("line.separator"));

        WordChainApplication application = new WordChainApplication(WordDictionary.loadFrom(lines[0]));

        for (int i = 1, linesLength = lines.length; i < linesLength; i++) {
            String[] line = lines[i].split(" ");
            WordChainResult wordChainResult = application.checkChain(line[0], line[1]);
            if (wordChainResult.isChained()) {
                systemWriter.write(String.format("YES %s", wordChainResult.getChain().toString()));
            } else {
                systemWriter.write("NO");
            }
        }
    }
}
