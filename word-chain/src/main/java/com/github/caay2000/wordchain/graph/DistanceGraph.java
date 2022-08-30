package com.github.caay2000.wordchain.graph;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import com.github.caay2000.wordchain.dictionary.Dictionary;

public class DistanceGraph implements Graph {

    private final Map<Integer, Set<String>> distanceMap;

    public DistanceGraph(Dictionary dictionary, String start, String end) {
        this.distanceMap = new LinkedHashMap<>();

        Set<String> nextCandidates = findNextCandidates(dictionary, start);
        createDistanceMap(nextCandidates, end);
    }

    @Override
    public Set<String> getAllCandidates() {

        return distanceMap.keySet().stream()
                .sorted()
                .map(distanceMap::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Set<String> findNextCandidates(Dictionary wordDictionary, String word) {

        return wordDictionary.getWordsOfSize(word.length()).stream()
                .filter(e -> getDistanceOf(word, e) == 1)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void createDistanceMap(Set<String> nextCandidates, String end) {
        for (String nextCandidate : nextCandidates) {
            int distance = getDistanceOf(nextCandidate, end);
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

    private int getDistanceOf(String start, String end) {
        int changes = 0;

        for (int i = 0, charArrayLength = start.toCharArray().length; i < charArrayLength; i++) {
            if (start.toCharArray()[i] != end.toCharArray()[i]) {
                changes++;
            }
        }
        return changes;
    }
}
