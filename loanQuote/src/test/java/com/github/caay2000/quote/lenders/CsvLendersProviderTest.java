package com.github.caay2000.quote.lenders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.github.caay2000.quote.model.Lender;
import com.github.caay2000.quote.model.QuoteException;
import com.github.caay2000.quote.utils.SystemReaderStub;

public class CsvLendersProviderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void emptyFile() {

        expectedException.expect(QuoteException.class);
        expectedException.expectMessage("empty.csv is incorrect");

        SystemReaderStub systemReader = new SystemReaderStub(new ArrayList<>());
        LendersProvider testee = new CsvLendersProvider(systemReader);

        testee.getLenders("empty.csv");
    }

    @Test
    public void malformedFile() {

        expectedException.expect(QuoteException.class);
        expectedException.expectMessage("malformed.csv is incorrect");

        SystemReaderStub systemReader = new SystemReaderStub(
                Arrays.asList(
                        "Lender,Rate,Available",
                        "incorrect"));
        LendersProvider testee = new CsvLendersProvider(systemReader);
        testee.getLenders("malformed.csv");
    }

    @Test
    public void oneLineFile() {

        SystemReaderStub systemReader = new SystemReaderStub(
                Arrays.asList(
                        "Lender,Rate,Available",
                        "Bob,0.075,640"));
        LendersProvider testee = new CsvLendersProvider(systemReader);
        List<Lender> lenders = testee.getLenders("oneLine.csv");

        Assert.assertEquals(1, lenders.size());
        Assert.assertEquals("Bob", lenders.get(0).getName());
        Assert.assertEquals(0.075d, lenders.get(0).getRate(), 0d);
        Assert.assertEquals(640, lenders.get(0).getAvailable());
    }

    @Test
    public void multipleLinesFile() {

        SystemReaderStub systemReader = new SystemReaderStub(
                Arrays.asList(
                        "Lender,Rate,Available",
                        "Bob,0.075,640",
                        "Jane,0.069,480",
                        "Fred,0.071,520",
                        "Mary,0.104,170",
                        "John,0.081,320",
                        "Dave,0.074,140",
                        "Angela,0.071,60"));
        LendersProvider testee = new CsvLendersProvider(systemReader);
        List<Lender> lenders = testee.getLenders("market.csv");

        Assert.assertEquals(7, lenders.size());
    }
}
