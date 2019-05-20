package com.github.caay2000.wordchain.io;

public interface SystemReader {

    SystemInput readInput();
    /*
    String inputFile = systemReader.readInput(file);

        String[] lines = inputFile.split(System.getProperty("line.separator"));

        WordChainApplication application = new WordChainApplication(new WordDictionary(lines[0]));

        for (int i = 1, linesLength = lines.length; i < linesLength; i++) {
            String[] line = lines[i].split(" ");
            WordChainResult wordChainResult = application.checkChain(line[0], line[1]);
            if (wordChainResult.isChained()) {
                systemWriter.write(String.format("YES %s", wordChainResult.getChain().toString()));
            } else {
                systemWriter.write("NO");
            }
        }
     */
}
