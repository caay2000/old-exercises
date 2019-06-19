package com.github.caay2000.quote.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import com.github.caay2000.quote.model.QuoteException;

public class FileSystemReader implements SystemReader {

    @Override
    public List<String> getLines(String csv) {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(csv))) {

            return br.lines().collect(Collectors.toList());
        } catch (IOException | RuntimeException e) {
            throw new QuoteException(String.format("error reading file %s", csv));
        }
    }
}
