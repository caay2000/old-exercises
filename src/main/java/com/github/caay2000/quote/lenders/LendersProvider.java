package com.github.caay2000.quote.lenders;

import java.util.List;
import com.github.caay2000.quote.model.Lender;

public interface LendersProvider {

    List<Lender> getLenders(String csvFile);
}
