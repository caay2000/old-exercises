package com.github.caay2000.quote.io;

import com.github.caay2000.quote.model.LoanTerms;
public interface SystemWriter {

    void write(LoanTerms loanTerms);

    void write(String message);
}

