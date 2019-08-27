package com.github.caay2000.metropolis.console;

public interface Console {

    void write(String string);

    void writeln(String string);

    ConsoleOperation read();
}
