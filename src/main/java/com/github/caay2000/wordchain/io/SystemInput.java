package com.github.caay2000.wordchain.io;

import java.io.File;
import java.util.List;
public class SystemInput {

    private static final String DEFAULT_CHARSET = "UTF-8";


    private final File dictionaryFile;
    private final List<Pair> pairs;

    public SystemInput(String dictionaryFile, List<Pair> pairs) {
        this.dictionaryFile = new File(dictionaryFile);
        this.pairs = pairs;
    }

    public File getDictionaryFile() {
        return dictionaryFile;
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
