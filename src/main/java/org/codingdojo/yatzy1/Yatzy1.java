package org.codingdojo.yatzy1;


public class Yatzy1 {

    public static int chance(int... dice) {
        int total = 0;
        for (int die : dice) {
            total += die;
        }
        return total;
    }

    public static int yatzy(int... dice) {
        int[] counts = countDice(dice);
        for (int count : counts) {
            if (count == 5) {
                return 50;
            }
        }
        return 0;
    }

    public static int ones(int... dice) {
        return scoreSingles(dice, 1);
    }

    public static int twos(int... dice) {
        return scoreSingles(dice, 2);
    }

    public static int threes(int... dice) {
        return scoreSingles(dice, 3);
    }

    public int fours() {
        return scoreSingles(this.dice, 4);
    }

    public int fives() {
        return scoreSingles(this.dice, 5);
    }

    public int sixes() {
        return scoreSingles(this.dice, 6);
    }

    public int scorePair(int... dice) {
        int[] counts = countDice(dice);
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= 2) {
                return (i + 1) * 2;
            }
        }
        return 0;
    }

    public static int twoPair(int... dice) {
        int[] counts = countDice(dice);
        int n = 0;
        int score = 0;
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= 2) {
                n++;
                score += (i + 1);
            }
        }
        if (n == 2) {
            return score * 2;
        } else {
            return 0;
        }
    }

    public static int fourOfAKind(int... dice) {
        int[] counts = countDice(dice);
        for (int i = 0; i < 6; i++) {
            if (counts[i] == 4) {
                return (i + 1) * 4;
            }
        }
        return 0;
    }

    public static int threeOfAKind(int... dice) {
        int[] counts = countDice(dice);
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= 3) {
                return (i + 1) * 3;
            }
        }
        return 0;
    }

    public static int smallStraight(int... dice) {
        int[] counts = countDice(dice);
        for (int i = 0; i < 5; i++) {
            if (counts[i] != 1) {
                return 0;
            }
        }
        return 15;
    }

    public static int largeStraight(int... dice) {
        int[] counts = countDice(dice);
        for (int i = 1; i < 6; i++) {
            if (counts[i] != 1) {
                return 0;
            }
        }
        return 20;
    }

    public static int fullHouse(int... dice) {
        int[] counts = countDice(dice);
        boolean hasTwo = false;
        boolean hasThree = false;
        int pairValue = 0;
        int tripletValue = 0;
        
        for (int i = 0; i < 6; i++) {
            if (counts[i] == 2) {
                hasTwo = true;
                pairValue = i + 1;
            } else if (counts[i] == 3) {
                hasThree = true;
                tripletValue = i + 1;
            }
        }
        
        if (hasTwo && hasThree) {
            return pairValue * 2 + tripletValue * 3;
        } else {
            return 0;
        }
    }

    private static int[] countDice(int... dice) {
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }
        return counts;
    }

    private static int scoreSingles(int[] dice, int value) {
        int sum = 0;
        for (int die : dice) {
            if (die == value) {
                sum += die;
            }
        }
        return sum;
    }

    private int[] dice;

    public Yatzy1() {
    }

    public Yatzy1(int... dice) {
        this.dice = dice;
    }
}
