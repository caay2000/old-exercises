package com.github.caay2000.quote;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.LoanTerms;
import com.github.caay2000.quote.utils.LenderBuilder;
import com.github.caay2000.quote.utils.LendersProviderStub;
import com.github.caay2000.quote.utils.LoanApiStub;
import com.github.caay2000.quote.utils.SystemWriterSpy;

public class QuoteAppTest {

    private static final Lender ANY_LENDER = LenderBuilder.aLender().withAvailable(100);
    private static final int ANY_INT = 100;
    private static final BigDecimal ANY_BIG_DECIMAL = BigDecimal.ONE;

    @Test
    public void noParams() {

        SystemWriterSpy systemWriter = new SystemWriterSpy();
        QuoteApp testee = new QuoteApp(null, systemWriter, null);

        testee.execute(new String[0]);

        Assert.assertTrue(systemWriter.verify("Two arguments are required, path to csv file (String) and amount (int)"));
    }

    @Test
    public void noNumericAmount() {
        SystemWriterSpy systemWriter = new SystemWriterSpy();
        QuoteApp testee = new QuoteApp(null, systemWriter, null);

        testee.execute(new String[] {"file", "amount"});

        Assert.assertTrue(systemWriter.verify("Second argument should be a number between 1000 and 15000"));
    }

    @Test
    public void loanPrinted() {
        LendersProviderStub lendersProvider = new LendersProviderStub(ANY_LENDER);
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        LoanTerms expectedLoanTerms = new LoanTerms(ANY_INT, ANY_BIG_DECIMAL, ANY_BIG_DECIMAL, ANY_BIG_DECIMAL);
        LoanApiStub loanApi = new LoanApiStub(expectedLoanTerms);

        QuoteApp testee = new QuoteApp(lendersProvider, systemWriter, loanApi);

        testee.execute(new String[] {"file", "1000"});

        Assert.assertTrue(systemWriter.verify(expectedLoanTerms));
    }
}
