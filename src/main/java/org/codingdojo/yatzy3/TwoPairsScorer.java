package org.codingdojo.yatzy3;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TwoPairsScorer extends CategoryScorer {
    @Override
    public int calculateScore(List<Integer> dice) {
        Map<Integer, Integer> frequencies = calculateFrequencies(dice);
        List<Integer> pairs = frequencies.entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        return pairs.size() == 2 ? pairs.stream().mapToInt(p -> p * 2).sum() : 0;
    }
}
