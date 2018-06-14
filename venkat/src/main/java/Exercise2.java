import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Exercise2 {
    public static void main(String[] args) {
        try {
            String searchWord = "localhost";
            String path = "C:\\Users\\acasanovas\\IdeaProjects\\jbcn2018\\venkat\\src\\main\\resources\\exercise2.txt";

            long count = 0;
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(path));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(searchWord))
                    count++;
            }

            System.out.printf("The word %s occured %d times\n", searchWord, count);

            System.out.println("Functional:");

            long countFunctional = Files.readAllLines(Paths.get(path)).stream()
                    .filter(i -> i.equals(searchWord))
                    .count();

            System.out.printf("The lines with the word %s occured %d times\n", searchWord, countFunctional);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
