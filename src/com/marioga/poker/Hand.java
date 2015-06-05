package com.marioga.poker;

import java.util.Arrays;
import java.util.Collections;

/**
 * This class represents a generic card hand and is intended for extension.
 * 
 * @author marioga
 */
public class Hand {
    public static final String[] POKER_HAND_TYPE = new String[] { "High Card",
            "One Pair", "Two Pairs", "Three of a Kind", "Straight", "Flush",
            "Full House", "Four of a Kind", "Straight Flush" };

    protected Card[] mCards;
    private int mSize;

    public Hand(Deck d, int n) {
        mSize = n;
        mCards = new Card[n];
        for (int i = 0; i < n; i++) {
            mCards[i] = d.drawFromDeck();
        }
    }

    public Hand(String s, int n) {
        mSize = n;
        mCards = new Card[n];
        String[] inputString = s.split(" ");
        for (int i = 0; i < n; i++) {
            mCards[i] = new Card(inputString[i]);
        }
    }

    public Hand(Card[] c) {
        mCards = c;
        mSize = c.length;
    }

    protected void orderHand() {
        Arrays.sort(mCards, Collections.reverseOrder());
    }

    public Card[] getCards() {
        return mCards;
    }

    public void setCard(int index, Card c) {
        mCards[index] = c;
    }

    public void displayHand() {
        for (int i = 0; i < mSize; i++) {
            if (mCards[i] != null) {
                System.out.print(mCards[i] + " ");
            }
        }
        System.out.println();
    }
}
