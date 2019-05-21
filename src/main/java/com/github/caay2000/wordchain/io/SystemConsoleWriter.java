package com.github.caay2000.wordchain.io;

import java.io.IOException;
import java.io.OutputStream;

public class SystemConsoleWriter implements SystemWriter {

    private final OutputStream outputStream;

    public SystemConsoleWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(String string) throws IOException {

        outputStream.write(string.getBytes());
        outputStream.write(System.getProperty("line.separator").getBytes());
    }
}
