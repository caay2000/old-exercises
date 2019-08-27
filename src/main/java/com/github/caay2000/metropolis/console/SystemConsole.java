package com.github.caay2000.metropolis.console;


import com.github.caay2000.metropolis.exception.MetropolisException;

import java.io.*;

public class SystemConsole implements Console {

    private final OutputStream outputStream;
    private final BufferedReader bufferedReader;

    public SystemConsole(OutputStream outputStream, InputStream inputStream) {
        this.outputStream = outputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void write(String string) {
        try {
            outputStream.write(string.getBytes());
        } catch (IOException ioe) {
            throw new MetropolisException("error writing to system console");
        }
    }

    @Override
    public void writeln(String string) {
        try {
            this.write(string);
            outputStream.write(System.getProperty("line.separator").getBytes());
        } catch (IOException ioe) {
            throw new MetropolisException("error writing to system console");
        }
    }

    @Override
    public ConsoleOperation read() {
        try {
            String line = bufferedReader.readLine();
            return parseConsoleOperation(line);
        } catch (IOException e) {
            throw new MetropolisException("error writing to system console");
        }
    }

    private ConsoleOperation parseConsoleOperation(String line) {
        if (line.startsWith("start")) {
            return ConsoleOperation.aStartOperation(line.replace("start ", ""));
        }
        if (line.startsWith("stop")) {
            return ConsoleOperation.aStopOperation();
        }
        if (line.startsWith("restart")) {
            return ConsoleOperation.aRestartOperation();
        }
        if (line.startsWith("report")) {
            return ConsoleOperation.aReportOperation();
        }
        if (line.startsWith(":quit")) {
            return ConsoleOperation.aQuitOperation();
        }
        return null;
    }
}
