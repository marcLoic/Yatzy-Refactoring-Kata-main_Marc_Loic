package org.codingdojo.yatzy3;


import java.util.List;
import java.util.Map;

public class StraightScorer extends CategoryScorer {
    private final List<Integer> straightSequence;

    public StraightScorer(List<Integer> straightSequence) {
        this.straightSequence = straightSequence;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        Map<Integer, Integer> frequencies = calculateFrequencies(dice);
        for (int value : straightSequence) {
            if (frequencies.getOrDefault(value, 0) != 1) {
                return 0;
            }
        }
        return sum(dice);
    }
}
