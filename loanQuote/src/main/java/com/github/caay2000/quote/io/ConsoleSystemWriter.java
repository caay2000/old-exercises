package com.github.caay2000.quote.io;

import java.text.DecimalFormat;
import com.github.caay2000.quote.model.LoanTerms;

public class ConsoleSystemWriter implements SystemWriter {

    public static final DecimalFormat RATE_FORMAT = new DecimalFormat("#####.0");
    public static final DecimalFormat QUOTE_FORMAT = new DecimalFormat("#####.00");

    @Override
    public void write(LoanTerms loanTerms) {

        System.out.println(String.format("Requested amount: £%d", loanTerms.getRequestedAmount()));
        System.out.println(String.format("Rate: %s%%", RATE_FORMAT.format(loanTerms.getRate())));
        System.out.println(String.format("Monthly repayment: £%s", QUOTE_FORMAT.format(loanTerms.getMonthlyRepayment())));
        System.out.println(String.format("Total repayment: £%s", QUOTE_FORMAT.format(loanTerms.getTotalRepayment())));
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
