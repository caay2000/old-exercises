package com.github.caay2000.quote.api;

import com.github.caay2000.quote.model.QuoteException;

class LoanRequestValidator {

    void validate(int amount) {

        if (amount < Loan.MIN_AMOUNT_REQUESTED) {
            throw new QuoteException("Loan request should be greater than %d", Loan.MIN_AMOUNT_REQUESTED);
        }

        if (amount > Loan.MAX_AMOUNT_REQUESTED) {
            throw new QuoteException("Loan request should be lesser than %d", Loan.MAX_AMOUNT_REQUESTED);
        }

        if (amount % Loan.MULTIPLE_OF_AMOUNT_REQUESTED != 0) {
            throw new QuoteException("Loan request should be multiple of %d", Loan.MULTIPLE_OF_AMOUNT_REQUESTED);
        }
    }
}
