package com.github.caay2000.quote.utils;

import java.util.Arrays;
import java.util.List;
import com.github.caay2000.quote.lenders.LendersProvider;
import com.github.caay2000.quote.model.Lender;

public class LendersProviderStub implements LendersProvider {

    private final List<Lender> lenders;

    public LendersProviderStub(Lender... lenders) {
        this.lenders = Arrays.asList(lenders);
    }

    @Override
    public List<Lender> getLenders(String csvFile) {
        return lenders;
    }
}
