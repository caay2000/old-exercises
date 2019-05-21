package com.github.caay2000.wordchain.io;

import java.io.IOException;

public interface SystemReader {

    SystemInput readInput(String filename) throws IOException;
}
