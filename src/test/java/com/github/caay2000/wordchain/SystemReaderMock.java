package com.github.caay2000.wordchain;

import com.github.caay2000.wordchain.io.SystemReader;

public class SystemReaderMock implements SystemReader {

    private String mockValue;
    private boolean called = false;

    public void setExpectations(String value) {
        this.mockValue = value;
    }

    @Override
    public String readInput(String file) {
        called = true;
        return mockValue;
    }

    public boolean verify() {
        return called;
    }
}
