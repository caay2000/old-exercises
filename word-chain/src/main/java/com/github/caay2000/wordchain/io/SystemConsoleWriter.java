package com.github.caay2000.wordchain.io;

import java.io.IOException;
import java.io.OutputStream;
import com.github.caay2000.wordchain.WordChainException;

public class SystemConsoleWriter implements SystemWriter {

    private final OutputStream outputStream;

    public SystemConsoleWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(String string) {
        try {
            outputStream.write(string.getBytes());
            outputStream.write(System.getProperty("line.separator").getBytes());
        } catch (IOException ioe) {
            throw new WordChainException("error writing to system console");
        }
    }
}
