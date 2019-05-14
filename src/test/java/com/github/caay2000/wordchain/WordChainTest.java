package com.github.caay2000.wordchain;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class WordChainTest {

    private SystemReaderMock systemReader;
    private SystemWriterSpy systemWriter;

    private WordChain testee;

    @Before
    public void setUp() {
        systemReader = new SystemReaderMock();
        systemWriter = new SystemWriterSpy();

        testee = new WordChain(systemReader, systemWriter);
    }

    @Test
    public void notSameSize() throws IOException, URISyntaxException {
        systemReader.setExpectations(getFileContent("notSameSize.txt"));

        testee.execute("basicInput.txt");

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void basicInput() throws IOException, URISyntaxException {
        systemReader.setExpectations(getFileContent("basicInput.txt"));

        testee.execute("basicInput.txt");

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [cat, cot, cog, dog]", systemWriter.getWrites().get(0));
    }

    private String getFileContent(String file) throws IOException, URISyntaxException {

        return new String(Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource(file).toURI())));
    }
}
