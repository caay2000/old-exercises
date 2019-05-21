package com.github.caay2000.wordchain.utils;

import java.util.List;
import com.github.caay2000.wordchain.io.SystemInput;
import com.github.caay2000.wordchain.io.SystemReader;

public class SystemReaderStub implements SystemReader {

    private final SystemInput systemInput;

    public SystemReaderStub(String dictionaryFile, List<SystemInput.Pair> pairList) {
        systemInput = new SystemInput(dictionaryFile, pairList);
    }

    @Override
    public SystemInput readInput(String file) {
        return systemInput;
    }
}
