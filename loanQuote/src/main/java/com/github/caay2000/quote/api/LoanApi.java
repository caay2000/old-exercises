package com.github.caay2000.quote.api;

import java.util.List;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.LoanTerms;

public interface LoanApi {

    LoanTerms calculateLoan(List<Lender> lenders, int amount);
}
