package com.github.caay2000.wordchain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
    public void firstWordNotInDictionary() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), asList(new SystemInput.Pair("xyz", "any")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void secondWordNotInDictionary() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), asList(new SystemInput.Pair("any", "xyz")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void differentLengthWords() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), asList(new SystemInput.Pair("any", "word")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromCATtoDOG() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(DEFAULT_DICTIONARY), asList(new SystemInput.Pair("cat", "dog")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [CAT, CAG, COG, DOG]", systemWriter.getWrites().get(0));
    }

    @Test
    public void fromUMBRELLAtoDEMONIC() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(DEFAULT_DICTIONARY), asList(new SystemInput.Pair("umbrella", "demonic")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    @Test
    public void onlyOneLetterChange() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), asList(new SystemInput.Pair("aa", "ab")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AB]", systemWriter.getWrites().get(0));
    }

    @Test
    public void twoLetterChanges() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), asList(new SystemInput.Pair("aa", "bb")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AB, BB]", systemWriter.getWrites().get(0));
    }

    @Test
    public void searchBetterSolutionThanFirstFound() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), asList(new SystemInput.Pair("aa", "ce")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("YES [AA, AC, CC, CE]", systemWriter.getWrites().get(0));
    }

    @Test
    public void noPossiblePath() {

        SystemReader systemReader = new SystemReaderStub(getFilePath(SIMPLE_DICTIONARY), asList(new SystemInput.Pair("aa", "xx")));
        SystemWriterSpy systemWriter = new SystemWriterSpy();

        WordChainSolver testee = new WordChainSolver(systemReader, systemWriter);
        testee.execute(ANY_INPUT);

        Assert.assertEquals(1, systemWriter.getWrites().size());
        Assert.assertEquals("NO", systemWriter.getWrites().get(0));
    }

    private List<SystemInput.Pair> asList(SystemInput.Pair... pair) {
        return Arrays.stream(pair).collect(Collectors.toList());
    }

    private String getFilePath(String file) {

        return this.getClass().getClassLoader().getResource(file).getPath();
    }
}
