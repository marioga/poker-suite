package com.marioga.poker;

import java.util.Arrays;
import java.util.Comparator;

import com.marioga.poker.Card.Suit;

/**
 * This class represents a straight poker hand of 5 cards.
 * @author marioga
 */
public class StraightHand extends Hand implements EvaluableHand<StraightHand> {

    private int[] mValue;
    private String mHandType;
    private int mNumericalValue;
    
    public StraightHand(Deck d) {
        super(d, 5);
        initialize();
    }

    public StraightHand(String s) {
        super(s, 5);
        initialize();
    }

    public StraightHand(Card[] c) {
        super(c);
        initialize();
    }
    
    private void initialize() {
        orderHand(); // Needed to compute values correctly
        mValue = computeValue();
        mNumericalValue = computeNumericalValue();
        mHandType = computeHandType();
    }

    /**
     * Here we compute the "poker value" of a hand. Each hand can be mapped
     * uniquely to a sequence of 6 integers. The first integer is between 0
     * and 8, and determines the hand type: 0 - "High Card",
     * 1 - "One Pair", 2 - "Two Pairs", 3 - "Three of a Kind", 
     * 4 - "Straight", 5 - "Flush", 6 - "Full House", 
     * 7 - "Four of a Kind", 8 - "Straight Flush". 
     * The remaining 5 integers allow us to rank different hands of the
     * same type. 
     * @return integer array representing value of the hand
     */
    public final int[] computeValue() {
        int[] rankValues = new int[5]; 
        Suit[] suits = new Suit[5];
        for (int i = 0; i < 5; i++) {
            suits[i] = mCards[i].getSuit();
            rankValues[i] = mCards[i].getValue();
        }

        boolean sameSuit = 
                suits[0] == suits[1] && suits[1] == suits[2] &&
                suits[2] == suits[3] && suits[3] == suits[4];
        boolean isStraight = 
                rankValues[0] == rankValues[1] + 1 && 
                rankValues[1] == rankValues[2] + 1 &&
                rankValues[2] == rankValues[3] + 1 &&
                rankValues[3] == rankValues[4] + 1;

        if (isStraight) {
            if (sameSuit) { // Straight Flush
                return new int[] { 8, rankValues[0], 0, 0, 0, 0 };
            } else { // Straight
                return new int[] { 4, rankValues[0], 0, 0, 0, 0 };
            }
        }
        if (sameSuit) { // Flush
            return new int[] { 5, rankValues[0], rankValues[1], 
                    rankValues[2], rankValues[3], rankValues[4] };
        }
        if (rankValues[0] == rankValues[3]) { // Four of a kind
            return new int[] { 7, rankValues[0], rankValues[4], 0, 0, 0 };
        }
        if (rankValues[1] == rankValues[4]) { // Four of a kind
            return new int[] { 7, rankValues[1], rankValues[0], 0, 0, 0 };
        }

        if (rankValues[0] == rankValues[2]) { // Three of a kind or Full House
            if (rankValues[3] == rankValues[4]) { // Full house
                return new int[] { 6, rankValues[0], rankValues[3], 0, 0, 0 };
            } else { // Three of a kind
                return new int[] { 3, rankValues[0], rankValues[3],
                        rankValues[4], 0, 0 };
            }
        }

        if (rankValues[2] == rankValues[4]) {// Three of a kind or Full House
            if (rankValues[0] == rankValues[1]) {// Full house
                return new int[] { 6, rankValues[2], rankValues[0], 0, 0, 0 };
            } else { // Three of a kind
                return new int[] { 3, rankValues[2], rankValues[0],
                        rankValues[1], 0, 0 };
            }
        }

        if (rankValues[1] == rankValues[3]) { // Three of a kind
            return new int[] { 3, rankValues[1], rankValues[0],
                    rankValues[4], 0, 0 };
        }

        boolean[] pairs = new boolean[] { rankValues[0] == rankValues[1],
                rankValues[1] == rankValues[2], rankValues[2] == rankValues[3],
                rankValues[3] == rankValues[4] };

        if (pairs[0]) {
            if (pairs[2]) { // Two pairs
                return new int[] { 2, rankValues[0], rankValues[2],
                        rankValues[4], 0, 0 };
            } else if (pairs[3]) { // Two pairs
                return new int[] { 2, rankValues[0], rankValues[3],
                        rankValues[2], 0, 0 };
            } else { // One pair
                return new int[] { 1, rankValues[0], rankValues[2],
                        rankValues[3], rankValues[4],
                        0 };
            }
        }
        if (pairs[1]) {
            if (pairs[3]) { // Two pairs
                return new int[] { 2, rankValues[1], 
                        rankValues[3], rankValues[0], 0, 0 };
            } else { // One pair
                return new int[] { 1, rankValues[1], rankValues[0],
                        rankValues[3], rankValues[4], 0 };
            }
        }
        if (pairs[2]) {// One pair
            return new int[] { 1, rankValues[2], rankValues[0],
                    rankValues[1], rankValues[4], 0 };
        }
        if (pairs[3]) {// One pair
            return new int[] { 1, rankValues[3], rankValues[0],
                    rankValues[1], rankValues[2], 0 };
        }
        // Otherwise, return highest card
        return new int[] { 0, rankValues[0], rankValues[1],
                rankValues[2], rankValues[3], rankValues[4] };
    }

    /**
     * Computes a hash value of a hand's value using base 13. A hand
     * beats another if and only if its hash value is strictly greater
     * than the other's. 
     * @return numerical value of hand
     */
    
    public final int computeNumericalValue() {
        int numericalValue = 0;
        for (int i = 0; i <= 5; i++) {
            numericalValue = 13 * numericalValue + mValue[i];
        }
        return numericalValue;
    }

    
    public final String computeHandType() {
        return POKER_HAND_TYPE[mValue[0]];
    }

    @Override
    public int[] getValue() {
        return mValue;
    }

    @Override
    public int getNumericalValue() {
        return mNumericalValue;
    }

    @Override
    public String getHandType() {
        return mHandType;
    }

    @Override
    public void displayValue() {
        System.out.println(Arrays.toString(mValue));
    }

    @Override
    public void displayNumericalValue() {
        System.out.println(mNumericalValue);
    }

    @Override
    public void displayHandType() {
        System.out.println(mHandType);
    }

    @Override
    public int compareTo(StraightHand that) {
        if (this.getNumericalValue() > that.getNumericalValue()) {
            return 1;
        } else if (this.getNumericalValue() < that.getNumericalValue()) {
            return -1;
        } else {
            return 0;
        }
    }
    
    static class StrictInequality implements Comparator<StraightHand> {
        @Override
        public int compare(StraightHand o1, StraightHand o2) {
            if (o1.compareTo(o2) != 0) {
                return o1.compareTo(o2);
            } else {
                for (int i = 0; i < 5; i++) {
                    if (o1.getCards()[i].compareTo(o2.getCards()[i])
                            < 0) {
                        return -1;
                    } else if (o1.getCards()[i].compareTo(o2.getCards()[i])
                            > 0) {
                        return 1;
                    }
                }
            }
            return 0;
        }
    }
}
