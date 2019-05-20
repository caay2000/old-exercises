package com.github.caay2000.wordchain;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import com.github.caay2000.wordchain.io.SystemInput;
import com.github.caay2000.wordchain.io.SystemReader;
import com.github.caay2000.wordchain.utils.SystemReaderStub;
import com.github.caay2000.wordchain.utils.SystemWriterSpy;

public class WordChainSolverTest {

    private static final String DEFAULT_DICTIONARY = "dictionaries/wordsDictionary.txt";
    private static final String SIMPLE_DICTIONARY = "dictionaries/simpleDictionary.txt";

    @Test
    public void firstWordNotInDictionary() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("xyz", "any")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute();

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void secondWordNotInDictionary() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("any", "xyz")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute();

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void differentLenghtWords() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("any", "word")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute();

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromAAtoAB() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("aa", "ab")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute();

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AB]", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromAAtoBB() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("aa", "bb")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute();

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AB, BB]", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromAAtoCE() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("aa", "ce")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute();

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AC, CC, CE]", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromAAtoXX() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("aa", "xx")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute();

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }


    //
    //@Test
    //public void notSameSize() throws IOException, URISyntaxException {
    //    systemReader.setExpectations(getFileContent("notSameSize.txt"));
    //
    //    testee.execute("basicInput.txt");
    //
    //    Assert.assertEquals(1, systemWriter.getWrites().size());
    //    Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    //}
    //
    //@Test
    //public void basicInput() throws IOException, URISyntaxException {
    //    systemReader.setExpectations(getFileContent("basicInput.txt"));
    //
    //    testee.execute("basicInput.txt");
    //
    //    Assert.assertEquals(1, systemWriter.getWrites().size());
    //    Assert.assertEquals("YES [cat, cot, cog, dog]", systemWriter.getWrites().get(0));
    //}
    //
    //@Test
    //public void breakTrend() throws IOException, URISyntaxException {
    //    systemReader.setExpectations(getFileContent("increaseDiffExample.txt"));
    //
    //    testee.execute("increaseDiffExample.txt");
    //
    //    for (String write : systemWriter.getWrites()) {
    //        System.out.println(write);
    //    }
    //
    //
    //
    //    //Assert.assertEquals(1, systemWriter.getWrites().size());
    //    //Assert.assertEquals("YES [cat, cot, cog, dog]", systemWriter.getWrites().get(0));
    //}
    //
    //

    private String getFilePath(String file) {

        return this.getClass().getClassLoader().getResource(file).getPath();
    }
}
