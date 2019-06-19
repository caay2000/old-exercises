package com.github.caay2000.quote.utils;

import java.util.List;
import com.github.caay2000.quote.api.LoanApi;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.LoanTerms;

public class LoanApiStub implements LoanApi {

    private final LoanTerms loanTerms;

    public LoanApiStub(LoanTerms loanTerms) {
        this.loanTerms = loanTerms;
    }

    @Override
    public LoanTerms calculateLoan(List<Lender> lenders, int amount) {
        return this.loanTerms;
    }
}
