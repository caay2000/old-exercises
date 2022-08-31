package com.github.caay2000.quote.api;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.LoanTerms;

public class Loan implements LoanApi {

    public static final MathContext CONTEXT = MathContext.DECIMAL128;


    static final int MONTHS_IN_YEAR = 12;
    static final int TOTAL_LOAN_MONTHS = 36;

    public static final int MIN_AMOUNT_REQUESTED = 1000;
    public static final int MAX_AMOUNT_REQUESTED = 15000;
    static final int MULTIPLE_OF_AMOUNT_REQUESTED = 100;

    private final LoanRequestValidator validator;

    public Loan() {

        this.validator = new LoanRequestValidator();
    }

    @Override
    public LoanTerms calculateLoan(List<Lender> lenders, int amount) {

        validator.validate(amount);

        BigDecimal interest = new Rate(lenders).getRateFor(amount);

        return new Quote().getLoanTerms(amount, interest);
    }
}
