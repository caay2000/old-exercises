package com.github.caay2000.quote.utils;

import java.util.ArrayList;
import java.util.List;
import com.github.caay2000.quote.io.SystemWriter;
import com.github.caay2000.quote.model.LoanTerms;

public class SystemWriterSpy implements SystemWriter {

    private final List<Object> writes;

    public SystemWriterSpy() {
        writes = new ArrayList<>();
    }

    @Override
    public void write(LoanTerms loanTerms) {
        writes.add(loanTerms);
    }

    @Override
    public void write(String message) {
        writes.add(message);
    }

    public boolean verify(Object message) {
        return writes.contains(message);
    }

    public boolean verify(String message) {
        return writes.contains(message);
    }
}
