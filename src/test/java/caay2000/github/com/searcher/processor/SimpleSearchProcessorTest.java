package caay2000.github.com.searcher.processor;

import caay2000.github.com.searcher.model.Document;
import caay2000.github.com.searcher.model.DocumentLibrary;
import caay2000.github.com.searcher.model.SearchResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class SimpleSearchProcessorTest {

    @Test
    public void emptyLibrary() {

        DocumentLibrary library = new DocumentLibrary();
        SimpleSearchProcessor testee = new SimpleSearchProcessor();

        SearchResult result = testee.search(new HashSet<>(Arrays.asList("something")), library);

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void noWordFound() {

        DocumentLibrary library = new DocumentLibrary();
        library.addDocument(new Document("filename", new HashSet<>(Arrays.asList("something"))));
        SimpleSearchProcessor testee = new SimpleSearchProcessor();

        SearchResult result = testee.search(new HashSet<>(Arrays.asList("wrong")), library);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(new Integer(0), getResultItemByFilename(result, "filename").getValue());
    }


    @Test
    public void oneWordLibrary() {

        DocumentLibrary library = new DocumentLibrary();
        library.addDocument(new Document("filename", new HashSet<>(Arrays.asList("something"))));
        SimpleSearchProcessor testee = new SimpleSearchProcessor();

        SearchResult result = testee.search(new HashSet<>(Arrays.asList("something")), library);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(new Integer(100), getResultItemByFilename(result, "filename").getValue());
    }

    @Test
    public void twoWordLibrary() {

        DocumentLibrary library = new DocumentLibrary();
        library.addDocument(new Document("filename", new HashSet<>(Arrays.asList("something"))));
        SimpleSearchProcessor testee = new SimpleSearchProcessor();

        SearchResult result = testee.search(new HashSet<>(Arrays.asList("something", "another")), library);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(new Integer(50), getResultItemByFilename(result, "filename").getValue());
    }

    @Test
    public void multipleFilesWithMultipleWords() {

        DocumentLibrary library = new DocumentLibrary();
        library.addDocument(new Document("something", new HashSet<>(Arrays.asList("something", "nothing"))));
        library.addDocument(new Document("another", new HashSet<>(Arrays.asList("something", "another"))));
        library.addDocument(new Document("nothing", new HashSet<>(Arrays.asList("nothing", "found"))));
        SimpleSearchProcessor testee = new SimpleSearchProcessor();

        SearchResult result = testee.search(new HashSet<>(Arrays.asList("something", "another")), library);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals(new Integer(50), getResultItemByFilename(result, "something").getValue());
        Assert.assertEquals(new Integer(100), getResultItemByFilename(result, "another").getValue());
        Assert.assertEquals(new Integer(0), getResultItemByFilename(result, "nothing").getValue());
    }

    private SearchResult.ResultItem getResultItemByFilename(SearchResult result, String filename) {
        return result.getResult().stream()
                .filter(e -> e.getFilename().equals(filename))
                .findFirst().orElseThrow(() -> new RuntimeException("not found"));
    }

}