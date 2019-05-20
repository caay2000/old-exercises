package com.github.caay2000.wordchain.application;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import com.github.caay2000.wordchain.application.dictionary.Dictionary;
import com.github.caay2000.wordchain.application.graph.DistanceGraph;

public class WordChain {

    private final Dictionary dictionary;

    public WordChain(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Set<String> solve(String start, String end) {

        if (isValidInput(start, end)) {

            this.dictionary.load();
            if (isValidWord(start) && isValidWord(end)) {

                Set<String> result = solveChain(addToChain(new LinkedHashSet<>(), start), -1, start, end);
                if (result.contains(end)) {
                    return result;
                }
            }
        }
        return new LinkedHashSet<>();
    }

    private Set<String> solveChain(Set<String> chain, int maxSize, String start, String end) {

        Set<String> bestSolution = chain;

        if (maxSize > 0 && chain.size() > maxSize) {
            return chain;
        }

        if (chain.contains(end)) {
            if (maxSize < 0 || chain.size() < maxSize) {
                maxSize = chain.size();
                bestSolution = chain;
            }
        }

        DistanceGraph distanceGraph = new DistanceGraph(this.dictionary, start, end);
        Set<String> allCandidates = distanceGraph.getAllCandidates();

        for (String candidate : allCandidates) {
            if (validCandidate(candidate, chain)) {
                Set<String> newChain = new LinkedHashSet<>(chain);
                newChain.add(candidate);
                Set<String> strings = solveChain(newChain, maxSize, candidate, end);

                if (strings.contains(end)) {
                    if (maxSize < 0 || strings.size() < maxSize) {
                        maxSize = strings.size();
                        bestSolution = strings;
                    }
                }
            }
        }
        return bestSolution;
    }

    private boolean validCandidate(String candidate, Set<String> chain) {
        return !chain.contains(candidate);
    }

    private Set<String> addToChain(Set<String> chain, String word) {
        chain.add(word);
        return chain;
    }

    private boolean isValidInput(String start, String end) {
        return Objects.nonNull(start) &&
                Objects.nonNull(end) &&
                start.length() == end.length();
    }

    private boolean isValidWord(String word) {
        return dictionary.containsWord(word);
    }
}
