package org.codingdojo.yatzy3;

import org.codingdojo.YatzyCategory;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CategoryScorer {
    public static CategoryScorer createInstance(String categoryName) {
        YatzyCategory category = YatzyCategory.valueOf(categoryName);
        return switch (category) {
            case CHANCE -> new ChanceScorer();
            case YATZY -> new YatzyPointsScorer();
            case ONES -> new NumberScorer(1);
            case TWOS -> new NumberScorer(2);
            case THREES -> new NumberScorer(3);
            case FOURS -> new NumberScorer(4);
            case FIVES -> new NumberScorer(5);
            case SIXES -> new NumberScorer(6);
            case PAIR -> new RepeatedCountScorer(2);
            case THREE_OF_A_KIND -> new RepeatedCountScorer(3);
            case FOUR_OF_A_KIND -> new RepeatedCountScorer(4);
            case SMALL_STRAIGHT -> new StraightScorer(List.of(1, 2, 3, 4, 5));
            case LARGE_STRAIGHT -> new StraightScorer(List.of(2, 3, 4, 5, 6));
            case TWO_PAIRS -> new TwoPairsScorer();
            case FULL_HOUSE -> new FullHouseScorer();
        };
    }

    public abstract int calculateScore(List<Integer> dice);

    protected Map<Integer, Integer> calculateFrequencies(List<Integer> dice) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int die : dice) {
            frequencies.put(die, frequencies.getOrDefault(die, 0) + 1);
        }
        return frequencies;
    }

    protected int sum(List<Integer> dice) {
        return dice.stream().mapToInt(Integer::intValue).sum();
    }
}
