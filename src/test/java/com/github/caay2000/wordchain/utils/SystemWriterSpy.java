package com.github.caay2000.wordchain.utils;

import java.util.ArrayList;
import java.util.List;
import com.github.caay2000.wordchain.io.SystemWriter;
public class SystemWriterSpy implements SystemWriter {
    private List<String> writes = new ArrayList<>();

    @Override
    public void write(String value) {
        writes.add(value);
    }

    public List<String> getWrites() {
        return writes;
    }
}
