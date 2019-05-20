//package com.github.caay2000.wordchain.other;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.Assert;
//import org.junit.Test;
//import com.github.caay2000.wordchain.SystemReaderImpl;
//import com.github.caay2000.wordchain.application.dictionary.Dictionary;
//
//public class SystemReaderImplTest {
//
//    @Test(expected = FileNotFoundException.class)
//    public void fileNotFound() throws IOException {
//
//        SystemReaderImpl.loadDictionary("fileNotFound", null);
//    }
//
//    @Test
//    public void emptyFile() throws IOException {
//        SystemReaderImpl.loadDictionary(getFile("dictionaries/emptyDictionary.txt"), null);
//    }
//
//    @Test
//    public void correctFile() throws IOException {
//
//        DictionarySpy dictionary = new DictionarySpy();
//
//        SystemReaderImpl.loadDictionary(getFile("dictionaries/twoWordsDictionary.txt"), dictionary);
//
//        Assert.assertEquals(2, dictionary.getWords().size());
//        Assert.assertEquals("HOLA", dictionary.getWords().get(0));
//        Assert.assertEquals("ADIOS", dictionary.getWords().get(1));
//    }
//
//    private String getFile(String file) {
//        return this.getClass().getClassLoader().getResource(file).getFile();
//    }
//
//    private static class DictionarySpy implements Dictionary {
//
//        private List<String> words = new ArrayList<>();
//
//        @Override
//        public void addItem(String word) {
//            words.add(word);
//        }
//
//        public List<String> getWords() {
//            return this.words;
//        }
//    }
//}
