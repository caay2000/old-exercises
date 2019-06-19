package com.github.caay2000.quote.utils;

import java.util.List;
import com.github.caay2000.quote.io.SystemReader;

public class SystemReaderStub implements SystemReader {

    private List<String> lines;

    public SystemReaderStub(List<String> liens) {
        this.lines = liens;
    }

    @Override
    public List<String> getLines(String csv) {
        return lines;
    }
}
