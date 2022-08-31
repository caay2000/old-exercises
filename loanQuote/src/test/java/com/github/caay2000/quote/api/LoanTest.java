package com.github.caay2000.quote.api;

import static com.github.caay2000.quote.io.ConsoleSystemWriter.QUOTE_FORMAT;
import static com.github.caay2000.quote.io.ConsoleSystemWriter.RATE_FORMAT;
import static com.github.caay2000.quote.utils.LenderBuilder.aLender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.LoanTerms;
import com.github.caay2000.quote.model.QuoteException;

public class LoanTest {

    private List<Lender> anyLenders = new ArrayList<>();
    private List<Lender> marketCSV = Arrays.asList(
            aLender(0.075d, 640),
            aLender(0.069d, 480),
            aLender(0.071d, 520),
            aLender(0.104d, 170),
            aLender(0.081d, 320),
            aLender(0.074d, 140),
            aLender(0.071d, 60));

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private LoanApi testee;

    @Before
    public void setUp() {
        testee = new Loan();
    }

    @Test
    public void minRequest() {

        expectedException.expect(QuoteException.class);
        expectedException.expectMessage("Loan request should be greater than 1000");

        testee.calculateLoan(anyLenders, 999);
    }

    @Test
    public void maxRequest() {

        expectedException.expect(QuoteException.class);
        expectedException.expectMessage("Loan request should be lesser than 15000");

        testee.calculateLoan(anyLenders, 150001);
    }

    @Test
    public void notMultipleOf100() {

        expectedException.expect(QuoteException.class);
        expectedException.expectMessage("Loan request should be multiple of 100");

        testee.calculateLoan(anyLenders, 5555);
    }

    @Test
    public void notEnoughBorrowers() {

        expectedException.expect(QuoteException.class);
        expectedException.expectMessage("Not possible to provide a quote now");

        List<Lender> lenders = Arrays.asList(aLender().withAvailable(100));

        testee.calculateLoan(lenders, 1000);
    }

    @Test
    public void offerMatch() {

        List<Lender> lenders = Arrays.asList(aLender(0.08d, 10000));

        LoanTerms loanTerms = testee.calculateLoan(lenders, 10000);

        Assert.assertEquals(10000, loanTerms.getRequestedAmount());
        Assert.assertEquals("8.0", RATE_FORMAT.format(loanTerms.getRate()));
        Assert.assertEquals("312.08", QUOTE_FORMAT.format(loanTerms.getMonthlyRepayment()));
        Assert.assertEquals("11234.79", QUOTE_FORMAT.format(loanTerms.getTotalRepayment()));
    }

    @Test
    public void givenExample() {

        LoanTerms loanTerms = testee.calculateLoan(marketCSV, 1000);

        Assert.assertEquals(1000, loanTerms.getRequestedAmount());
        Assert.assertEquals("7.0", RATE_FORMAT.format(loanTerms.getRate()));
        Assert.assertEquals("30.78", QUOTE_FORMAT.format(loanTerms.getMonthlyRepayment()));
        Assert.assertEquals("1108.10", QUOTE_FORMAT.format(loanTerms.getTotalRepayment()));
    }
}