package org.codingdojo.yatzy3;


import java.util.List;

public class NumberScorer extends CategoryScorer {
    private final int number;

    public NumberScorer(int number) {
        this.number = number;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        return calculateFrequencies(dice).getOrDefault(number, 0) * number;
    }
}
