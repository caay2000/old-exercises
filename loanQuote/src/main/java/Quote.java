import com.github.caay2000.quote.QuoteApp;
import com.github.caay2000.quote.api.Loan;
import com.github.caay2000.quote.api.LoanApi;
import com.github.caay2000.quote.io.ConsoleSystemWriter;
import com.github.caay2000.quote.io.FileSystemReader;
import com.github.caay2000.quote.io.SystemWriter;
import com.github.caay2000.quote.lenders.CsvLendersProvider;
import com.github.caay2000.quote.lenders.LendersProvider;
public class Quote {

    public static void main(String[] args) {

        LendersProvider lendersProvider = new CsvLendersProvider(new FileSystemReader());
        SystemWriter systemWriter = new ConsoleSystemWriter();
        LoanApi loanApi = new Loan();

        new QuoteApp(lendersProvider, systemWriter, loanApi).execute(args);
    }
}
