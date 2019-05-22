package com.github.caay2000.wordchain.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import com.github.caay2000.wordchain.WordChainException;

public class SystemFileReader implements SystemReader {

    @Override
    public SystemInput readInput(String filename) {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

            String dictionaryFile = br.readLine();
            List<SystemInput.Pair> pairList = br.lines().
                    map(this::createPair)
                    .collect(Collectors.toList());

            return new SystemInput(dictionaryFile, pairList);
        } catch (IOException | RuntimeException e) {
            throw new WordChainException(String.format("error reading file %s", filename));
        }
    }

    private SystemInput.Pair createPair(String input) {
        String[] split = input.split(" ");
        if (split.length != 2) {
            throw new WordChainException("incorrect parsing of pairs");
        }
        return new SystemInput.Pair(split[0], split[1]);
    }
}
