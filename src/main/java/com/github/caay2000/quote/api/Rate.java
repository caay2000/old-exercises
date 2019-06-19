package com.github.caay2000.quote.api;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.QuoteException;

class Rate {

    private final List<Lender> lenders;

    Rate(List<Lender> lenders) {
        this.lenders = lenders.stream()
                .sorted(Comparator.comparing(Lender::getRate))
                .collect(Collectors.toList());
    }

    BigDecimal getRateFor(int amount) {

        int pending = amount;
        BigDecimal rate = BigDecimal.ZERO;

        for (Lender lender : this.lenders) {

            int requested = Math.min(lender.getAvailable(), pending);
            pending = pending - requested;

            rate = rate.add(calculateRateForLender(lender, requested, amount));

            if (pending == 0) {
                return rate;
            }
        }

        throw new QuoteException("Not possible to provide a quote now");
    }

    private BigDecimal calculateRateForLender(Lender lender, int requested, int amount) {
        return multiply(lender.getRate(), divide(requested, amount));
    }

    private BigDecimal multiply(double rate, BigDecimal divide) {
        return BigDecimal.valueOf(rate).multiply(divide, Loan.CONTEXT);
    }

    private BigDecimal divide(int requested, int amount) {
        return new BigDecimal(requested).divide(new BigDecimal(amount), Loan.CONTEXT);
    }
}
