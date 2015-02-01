package com.marioga.poker;

/**
 * This class represents a Texas Hold'em hand. 
 * @author marioga
 */
public class TexasHand extends Hand {

    public TexasHand(Deck d) {
        super(d, 2);
    }

    public TexasHand(String s) {
        super(s, 2);
    }

    public TexasHand(Card[] c) {
        super(c);
    }
}
