package com.github.caay2000.wordchain;

import java.io.IOException;
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
    private static final String[] ANY_INPUT = {"anyInput"};

    @Test
    public void firstWordNotInDictionary() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("xyz", "any")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void secondWordNotInDictionary() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("any", "xyz")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void differentLenghtWords() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("any", "word")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromCATtoDOG() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(DEFAULT_DICTIONARY), Arrays.asList(new SystemInput.Pair("cat", "dog")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [CAT, CAG, COG, DOG]", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromUMBRELLAtoDEMONIC() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(DEFAULT_DICTIONARY), Arrays.asList(new SystemInput.Pair("umbrella", "demonic")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromAAtoAB() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("aa", "ab")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AB]", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromAAtoBB() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("aa", "bb")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AB, BB]", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromAAtoCE() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("aa", "ce")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AC, CC, CE]", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromAAtoXX() throws IOException {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), Arrays.asList(new SystemInput.Pair("aa", "xx")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    private String getFilePath(String file) {

        return this.getClass().getClassLoader().getResource(file).getPath();
    }
}
