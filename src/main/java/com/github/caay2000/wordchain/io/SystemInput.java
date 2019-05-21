package com.github.caay2000.wordchain.io;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import com.github.caay2000.wordchain.WordChainException;

public class SystemInput {

    private final File dictionaryFile;
    private final List<Pair> pairs;

    public SystemInput(String dictionaryFile, List<Pair> pairs) {
        this.dictionaryFile = new File(dictionaryFile);
        this.pairs = pairs;
    }

    public Set<String> getDictionaryFileContent() {

        Set<String> content = new LinkedHashSet<>();
        try (Scanner sc = new Scanner(dictionaryFile)) {
            while (sc.hasNextLine()) {
                content.add(sc.nextLine());
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            throw new WordChainException(String.format("ERROR loading dictionary: %s", e.getMessage()), e);
        }
        return content;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public static class Pair {

        private final String start;
        private final String end;

        public Pair(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }
    }
}
