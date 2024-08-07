package org.codingdojo.yatzy3;


import java.util.List;
import java.util.Map;

public class FullHouseScorer extends CategoryScorer {
    @Override
    public int calculateScore(List<Integer> dice) {
        Map<Integer, Integer> frequencies = calculateFrequencies(dice);
        boolean hasThree = frequencies.containsValue(3);
        boolean hasTwo = frequencies.containsValue(2);
        return hasThree && hasTwo ? sum(dice) : 0;
    }
}
