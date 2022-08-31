package com.github.caay2000.quote.lenders;

import java.util.List;
import java.util.stream.Collectors;
import com.github.caay2000.quote.io.SystemReader;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.QuoteException;

public class CsvLendersProvider implements LendersProvider {

    private final SystemReader systemReader;

    public CsvLendersProvider(SystemReader systemReader) {

        this.systemReader = systemReader;
    }

    @Override
    public List<Lender> getLenders(String csvFile) {

        List<String> lines = systemReader.getLines(csvFile);
        if (lines.size() < 2) {
            throw new QuoteException(String.format("File %s is incorrect", csvFile));
        }

        try {
            return lines.stream()
                    .skip(1)
                    .map(CsvLendersProvider::parseLender)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new QuoteException(String.format("File %s is incorrect", csvFile));
        }
    }

    private static Lender parseLender(String line) {

        String[] split = line.split(",");

        String name = split[0];
        double rate = Double.parseDouble(split[1]);
        int available = Integer.parseInt(split[2]);
        return new Lender(name, rate, available);
    }
}
