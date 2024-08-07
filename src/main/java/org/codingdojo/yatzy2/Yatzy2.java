package org.codingdojo.yatzy2;

import org.codingdojo.YatzyCalculator;
import org.codingdojo.YatzyCategory;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Yatzy2 implements YatzyCalculator {

    @Override
    public List<String> validCategories() {
        return Arrays.stream(YatzyCategory.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @Override
    public int score(List<Integer> dice, String categoryName) {
        YatzyCategory category = YatzyCategory.valueOf(categoryName);

        Map<Integer, Integer> diceFrequencies = calculateFrequencies(dice);

        return switch (category) {
            case CHANCE -> sum(dice);
            case YATZY -> diceFrequencies.containsValue(5) ? 50 : 0;
            case ONES, TWOS, THREES, FOURS, FIVES, SIXES -> scoreSingles(diceFrequencies, category);
            case PAIR -> scoreNOfAKind(diceFrequencies, 2);
            case THREE_OF_A_KIND -> scoreNOfAKind(diceFrequencies, 3);
            case FOUR_OF_A_KIND -> scoreNOfAKind(diceFrequencies, 4);
            case SMALL_STRAIGHT -> isStraight(dice, List.of(1, 2, 3, 4, 5)) ? 15 : 0;
            case LARGE_STRAIGHT -> isStraight(dice, List.of(2, 3, 4, 5, 6)) ? 20 : 0;
            case TWO_PAIRS -> scoreTwoPairs(diceFrequencies);
            case FULL_HOUSE -> scoreFullHouse(diceFrequencies);
        };
    }

    private Map<Integer, Integer> calculateFrequencies(List<Integer> dice) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int die : dice) {
            frequencies.put(die, frequencies.getOrDefault(die, 0) + 1);
        }
        return frequencies;
    }

    private int sum(List<Integer> dice) {
        return dice.stream().mapToInt(Integer::intValue).sum();
    }

    private int scoreSingles(Map<Integer, Integer> frequencies, YatzyCategory category) {
        int dieValue = category.ordinal() + 1; // 0-based index, ONES corresponds to 1, TWOS to 2, etc.
        return frequencies.getOrDefault(dieValue, 0) * dieValue;
    }

    private int scoreNOfAKind(Map<Integer, Integer> frequencies, int n) {
        for (int dieValue = 6; dieValue > 0; dieValue--) {
            if (frequencies.getOrDefault(dieValue, 0) >= n) {
                return dieValue * n;
            }
        }
        return 0;
    }

    private boolean isStraight(List<Integer> dice, List<Integer> straightSequence) {
        Map<Integer, Integer> frequencies = calculateFrequencies(dice);
        for (int value : straightSequence) {
            if (frequencies.getOrDefault(value, 0) != 1) {
                return false;
            }
        }
        return true;
    }

    private int scoreTwoPairs(Map<Integer, Integer> frequencies) {
        List<Integer> pairs = frequencies.entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        return pairs.size() == 2 ? pairs.stream().mapToInt(p -> p * 2).sum() : 0;
    }

    private int scoreFullHouse(Map<Integer, Integer> frequencies) {
        boolean hasThree = frequencies.containsValue(3);
        boolean hasTwo = frequencies.containsValue(2);
        return hasThree && hasTwo ? sum(frequencies.entrySet().stream()
            .map(entry -> entry.getKey() * entry.getValue())
            .collect(Collectors.toList())) : 0;
    }
}
