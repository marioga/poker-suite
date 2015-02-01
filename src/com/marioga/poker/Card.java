package com.marioga.poker;

/**
 * An immutable class representing a playing card.
 *
 * @author marioga
 */

public class Card implements Comparable<Card>{
    public enum Suit {
        HEARTS('H', "Hearts", 0), SPADES('S', "Spades", 1),
        DIAMONDS('D', "Diamonds", 2), CLUBS('C', "Clubs", 3);

        private char mShortRepr;
        private String mLongRepr;
        private int mSortingIndex;

        Suit(char shortRepr, String longRepr, int sortingIdx) {
            this.mShortRepr = shortRepr;
            this.mLongRepr = longRepr;
            this.mSortingIndex = sortingIdx;
        }

        public char getShortRepr() {
            return mShortRepr;
        }

        public String getLongRepr() {
            return mLongRepr;
        }
        
        public int getSortingIndex() {
            return mSortingIndex;
        }

        public static Suit getSuitFromChar(char c) {
            for (Suit s : Suit.values()) {
                if (c == s.getShortRepr()) {
                    return s;
                }
            }
            return null;
        }
    }

    public enum Rank {
        TWO(2, '2', "Two"), THREE(3, '3', "Three"), FOUR(4, '4', "Four"),
        FIVE(5, '5', "Five"), SIX(6, '6', "Six"), SEVEN(7, '7', "Seven"),
        EIGHT(8, '8', "Eight"), NINE(9, '9', "Nine"), TEN(10, 'T', "Ten"),
        JACK(11, 'J', "Jack"), QUEEN(12, 'Q', "Queen"), KING(13, 'K', "King"),
        ACE(14, 'A', "Ace");

        private int mValue;
        private char mShortRepr;
        private String mLongRepr;

        Rank(int value, char shortRepr, String longRepr) {
            this.mValue = value;
            this.mShortRepr = shortRepr;
            this.mLongRepr = longRepr;
        }

        public int getValue() {
            return mValue;
        }

        public char getShortRepr() {
            return mShortRepr;
        }

        public String getLongRepr() {
            return mLongRepr;
        }

        public static Rank getRankFromChar(char c) {
            for (Rank r : Rank.values()) {
                if (c == r.getShortRepr()) {
                    return r;
                }
            }
            return null;
        }
    }
    
    /**
     * Here we store the rank and suit of the cards
     */
    private final Rank mRank;
    private final Suit mSuit;

    /**
     * Initializes a card of the given rank and suit.
     *
     * @param rank
     * @param suit
     */

    public Card(Rank rank, Suit suit) {
        this.mRank = rank;
        this.mSuit = suit;
    }

    /**
     * Initializes a card specified by a two character string, e.g., "3H".
     *
     * @param repr
     */

    public Card(String repr) {
        this(Rank.getRankFromChar(repr.charAt(0)), Suit.getSuitFromChar(repr
                .charAt(1)));

        // char rankChar = repr.charAt(0), suitChar = repr.charAt(1);
        //
        // switch (rankChar) {
        // case '2':
        // this.mRank = Rank.TWO;
        // break;
        // case '3':
        // this.mRank = Rank.THREE;
        // break;
        // case '4':
        // this.mRank = Rank.FOUR;
        // break;
        // case '5':
        // this.mRank = Rank.FIVE;
        // break;
        // case '6':
        // this.mRank = Rank.SIX;
        // break;
        // case '7':
        // this.mRank = Rank.SEVEN;
        // break;
        // case '8':
        // this.mRank = Rank.EIGHT;
        // break;
        // case '9':
        // this.mRank = Rank.NINE;
        // break;
        // case 'T':
        // this.mRank = Rank.TEN;
        // break;
        // case 'J':
        // this.mRank = Rank.JACK;
        // break;
        // case 'Q':
        // this.mRank = Rank.QUEEN;
        // break;
        // case 'K':
        // this.mRank = Rank.KING;
        // break;
        // case 'A':
        // this.mRank = Rank.ACE;
        // break;
        // default:
        // this.mRank = null;
        // }
        // switch (suitChar) {
        // case 'H':
        // this.mSuit = Suit.HEARTS;
        // break;
        // case 'S':
        // this.mSuit = Suit.SPADES;
        // break;
        // case 'D':
        // this.mSuit = Suit.DIAMONDS;
        // break;
        // case 'C':
        // this.mSuit = Suit.CLUBS;
        // break;
        // default:
        // this.mSuit = null;
        // }
    }

    @Override
    public String toString() {
        return Character.toString(mRank.getShortRepr())
                + Character.toString(mSuit.getShortRepr());
    }
    
    public String toLongString() {
        return mRank.getLongRepr() + " of " + mSuit.getLongRepr();
    }

    public Rank getRank() {
        return mRank;
    }

    public Suit getSuit() {
        return mSuit;
    }

    public int getValue() {
        return mRank.getValue();
    }

    @Override
    public int compareTo(Card o) {
        if (this.getValue() < o.getValue()) {
            return -1;
        } else if (this.getValue() > o.getValue()) {
            return 1;
        } else if (this.getSuit().getSortingIndex() < 
                o.getSuit().getSortingIndex()) {
            return -1;
        } else if (this.getSuit().getSortingIndex() > 
                o.getSuit().getSortingIndex()) {
            return 1;
        }
        return 0;
    }
}
