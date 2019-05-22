package com.github.caay2000.wordchain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import com.github.caay2000.wordchain.dictionary.Dictionary;
import com.github.caay2000.wordchain.graph.DistanceGraph;
import com.github.caay2000.wordchain.graph.Graph;

class WordChain {

    private static final LinkedHashSet<String> EMPTY_SET = new LinkedHashSet<>();

    private final Dictionary dictionary;

    public WordChain(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Set<String> solve(String start, String end) {

        if (isValidInput(start, end)) {

            Set<String> initialChain = setOf(EMPTY_SET, start);
            Set<String> finalChain = solveChainRecursive(initialChain, EMPTY_SET, start, end);
            if (finalChain.contains(end)) {
                return finalChain;
            }
        }
        return EMPTY_SET;
    }

    private Set<String> solveChainRecursive(Set<String> currentChain, Set<String> bestSolution, String start, String end) {

        if (isSolved(currentChain, end) || bestSolutionIsBetterThanActualChain(currentChain, bestSolution)) {
            return currentChain;
        }

        Set<String> allCandidates = getSortedCandidates(currentChain, start, end);

        Set<String> currentBestSolution = new LinkedHashSet<>(bestSolution);
        for (String candidate : allCandidates) {
            Set<String> newChain = solveChainRecursive(setOf(currentChain, candidate), currentBestSolution, candidate, end);

            if (isSolved(newChain, end)) {
                currentBestSolution = updateBestSolution(newChain, currentBestSolution);
            }
        }
        return currentBestSolution;
    }

    private boolean bestSolutionIsBetterThanActualChain(Set<String> currentChain, Set<String> bestSolution) {
        return !bestSolution.equals(EMPTY_SET) && bestSolution.size() - 1 <= currentChain.size();
    }

    private Set<String> updateBestSolution(Set<String> chain, Set<String> bestSolution) {
        if (bestSolution.equals(EMPTY_SET) || chain.size() < bestSolution.size()) {
            bestSolution = new LinkedHashSet<>(chain);
        }
        return bestSolution;
    }

    private boolean isSolved(Set<String> chain, String end) {
        return chain.contains(end);
    }

    private Set<String> getSortedCandidates(Set<String> chain, String start, String end) {
        Graph distanceGraph = new DistanceGraph(this.dictionary, start, end);
        Set<String> allCandidates = distanceGraph.getAllCandidates();
        allCandidates.removeAll(chain);
        return allCandidates;
    }

    private boolean isValidInput(String start, String end) {
        return Objects.nonNull(start) && isValidWord(start) &&
                Objects.nonNull(end) && isValidWord(end) &&
                start.length() == end.length();
    }

    private boolean isValidWord(String word) {
        return dictionary.containsWord(word);
    }

    private Set<String> setOf(Set<String> originalSet, String value) {
        LinkedHashSet<String> set = new LinkedHashSet<>(originalSet);
        set.add(value);
        return set;
    }
}
