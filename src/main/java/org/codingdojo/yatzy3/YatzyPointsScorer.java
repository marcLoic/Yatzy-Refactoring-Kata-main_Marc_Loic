package org.codingdojo.yatzy3;


import java.util.List;

public class YatzyPointsScorer extends CategoryScorer {
    @Override
    public int calculateScore(List<Integer> dice) {
        return calculateFrequencies(dice).containsValue(5) ? 50 : 0;
    }
}
