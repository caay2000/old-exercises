import java.io.IOException;
import com.github.caay2000.wordchain.io.SystemConsoleWriter;
import com.github.caay2000.wordchain.io.SystemFileReader;
public class WordChainSolver {

    public static void main(String[] args) throws IOException {

        com.github.caay2000.wordchain.WordChainSolver wordChainSolver = new com.github.caay2000.wordchain.WordChainSolver(
                new SystemFileReader(),
                new SystemConsoleWriter(System.out));

        wordChainSolver.execute(args);
    }
}
