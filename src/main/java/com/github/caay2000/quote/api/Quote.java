package com.github.caay2000.quote.api;

import static com.github.caay2000.quote.api.Loan.CONTEXT;
import static com.github.caay2000.quote.api.Loan.MONTHS_IN_YEAR;
import static com.github.caay2000.quote.api.Loan.TOTAL_LOAN_MONTHS;
import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import com.github.caay2000.quote.model.LoanTerms;

class Quote {

    LoanTerms getLoanTerms(int amount, BigDecimal interest) {

        BigDecimal monthlyInterestRate = calculateMonthlyInterestRate(interest);
        BigDecimal quote = calculateQuote(new BigDecimal(amount), monthlyInterestRate);

        return new LoanTerms(amount, interest, quote, calculateTotalQuote(quote));
    }

    private BigDecimal calculateMonthlyInterestRate(BigDecimal interest) {
        return BigDecimal.valueOf(Math.pow(BigDecimal.ONE.add(interest).doubleValue(), BigDecimal.ONE.divide(new BigDecimal(MONTHS_IN_YEAR), CONTEXT).doubleValue()) - 1);
    }

    private BigDecimal calculateQuote(BigDecimal amount, BigDecimal interest) {

        BigDecimal dividend = interest.multiply(amount, CONTEXT);
        BigDecimal divisor = ONE.subtract(ONE.divide((ONE.add(interest)).pow(TOTAL_LOAN_MONTHS, CONTEXT), CONTEXT));

        return dividend.divide(divisor, CONTEXT);
    }

    private BigDecimal calculateTotalQuote(BigDecimal quote) {
        return quote.multiply(new BigDecimal(TOTAL_LOAN_MONTHS), CONTEXT);
    }
}
