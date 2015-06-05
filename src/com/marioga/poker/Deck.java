package com.marioga.poker;

import java.util.Collections;
import java.util.ArrayList;

import com.marioga.poker.Card.Rank;
import com.marioga.poker.Card.Suit;

/**
 * This class represents a deck of 52 playing cards.
 *
 * @author marioga
 */

public class Deck {
    private ArrayList<Card> mCards;
    
    /**
     * Initializes an ordered deck of 52 cards.
     */
    public Deck() {
        mCards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                mCards.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(mCards);
    }

    /**
     * Returns the card on top of the deck.
     * @return first element of mCards
     */
    public Card drawFromDeck() {
        return mCards.remove(0);
    }

    /**
     * Returns the size of the deck.
     * @return size of the deck
     */
    public int getSize() {
        return mCards.size();
    }
}
