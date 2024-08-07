package org.codingdojo.yatzy3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RepeatedCountScorer extends CategoryScorer {
    private final int count;

    public RepeatedCountScorer(int count) {
        this.count = count;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        Map<Integer, Integer> frequencies = calculateFrequencies(dice);
        for (int i = 6; i > 0; i--) {
            if (frequencies.getOrDefault(i, 0) >= count) {
                return i * count;
            }
        }
        return 0;
    }
}
