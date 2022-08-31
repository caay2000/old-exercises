package com.github.caay2000.quote;

import java.util.List;
import com.github.caay2000.quote.api.Loan;
import com.github.caay2000.quote.api.LoanApi;
import com.github.caay2000.quote.io.SystemWriter;
import com.github.caay2000.quote.lenders.LendersProvider;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.LoanTerms;
import com.github.caay2000.quote.model.QuoteException;

public class QuoteApp {

    private final LendersProvider csvLendersProvider;
    private final SystemWriter consoleSystemWriter;
    private final LoanApi loanApi;

    public QuoteApp(LendersProvider csvLendersProvider, SystemWriter consoleSystemWriter, LoanApi loanApi) {
        this.csvLendersProvider = csvLendersProvider;
        this.consoleSystemWriter = consoleSystemWriter;
        this.loanApi = loanApi;
    }

    public void execute(String[] args) {

        try {
            validateInput(args);

            List<Lender> lenders = this.csvLendersProvider.getLenders(args[0]);
            LoanTerms loanTerms = this.loanApi.calculateLoan(lenders, Integer.parseInt(args[1]));
            this.consoleSystemWriter.write(loanTerms);
        } catch (QuoteException e) {
            this.consoleSystemWriter.write(e.getMessage());
        }
    }

    private void validateInput(String[] args) {
        if (args.length != 2) {
            throw new QuoteException("Two arguments are required, path to csv file (String) and amount (int)");
        }

        if (!args[1].chars().allMatch(Character::isDigit)) {
            throw new QuoteException("Second argument should be a number between %s and %s", Loan.MIN_AMOUNT_REQUESTED, Loan.MAX_AMOUNT_REQUESTED);
        }
    }
}
