package com.github.caay2000.quote.model;

import static com.github.caay2000.quote.api.Loan.CONTEXT;

import java.math.BigDecimal;
public class LoanTerms {

    private final int requestedAmount;
    private final BigDecimal rate;
    private final BigDecimal monthlyRepayment;
    private final BigDecimal totalRepayment;

    public LoanTerms(int requestedAmount, BigDecimal rate, BigDecimal monthlyRepayment, BigDecimal totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.rate = rate.multiply(new BigDecimal(100), CONTEXT);
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public int getRequestedAmount() {
        return this.requestedAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }
}
