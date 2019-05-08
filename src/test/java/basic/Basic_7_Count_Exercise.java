package basic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.Test;
public class Basic_7_Count_Exercise {

    @Test
    public void simple_loop() throws Exception {

        List<String> lines = getFileLines();

        int count = 0;
        for (String line : lines) {
            if (line.equals("localhost")) {
                count++;
            }
        }

        System.out.println(count);
    }

    private List<String> getFileLines() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("Basic_7_Count_Exercise.txt").getFile());
        return Files.readAllLines(file.toPath());
    }

    @Test
    public void streams() throws Exception {

        long count = getFileLines().stream()
                .filter(i -> i.equals("localhost"))
                .count();

        System.out.println(count);
    }
}
