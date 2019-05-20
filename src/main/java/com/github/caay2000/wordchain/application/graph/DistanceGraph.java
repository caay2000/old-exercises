package com.github.caay2000.wordchain.application.graph;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import com.github.caay2000.wordchain.application.dictionary.Dictionary;

public class DistanceGraph implements Graph {

    private final Map<Integer, Set<String>> distanceMap;

    public DistanceGraph(Dictionary wordDictionary, String start, String end) {
        this.distanceMap = new LinkedHashMap<>();

        Set<String> nextCandidates = findNextCandidates(wordDictionary, start);
        createDistanceMap(nextCandidates, end);
    }

    @Override
    public Set<String> getAllCandidates() {

        return distanceMap.keySet().stream()
                .sorted()
                .map(distanceMap::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private Set<String> findNextCandidates(Dictionary wordDictionary, String word) {

        return wordDictionary.getWordsOfSize(word.length()).stream()
                .filter(e -> countDifferentChars(word, e) == 1)
                .collect(Collectors.toSet());
    }

    private void createDistanceMap(Set<String> nextCandidates, String end) {
        for (String nextCandidate : nextCandidates) {
            int distance = countDifferentChars(nextCandidate, end);
            Set<String> wordsOfDistance = getGraphForDistance(distance);
            wordsOfDistance.add(nextCandidate);
        }
    }

    private Set<String> getGraphForDistance(int distance) {
        Set<String> itemsOfSize = this.distanceMap.get(distance);
        if (Objects.isNull(itemsOfSize)) {
            itemsOfSize = new LinkedHashSet<>();
            this.distanceMap.put(distance, itemsOfSize);
        }
        return itemsOfSize;
    }

    private int countDifferentChars(String word, String candidate) {

        int changes = 0;

        for (int i = 0, charArrayLength = word.toCharArray().length; i < charArrayLength; i++) {
            if (Character.toLowerCase(word.toCharArray()[i]) != Character.toLowerCase(candidate.toCharArray()[i])) {
                changes++;
            }
        }
        return changes;
    }
}
