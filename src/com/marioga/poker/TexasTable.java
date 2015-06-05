package com.marioga.poker;


/**
 * This class represents a Texas Hold'em table.
 * @author marioga
 */
public class TexasTable {
    public enum TableState {EMPTY, FLOP, TURN, RIVER}

    private Card[] mFlop = new Card[3];
    private Card mTurn = null;
    private Card mRiver = null;
    private TableState mState;

    public TexasTable(Deck d, TableState state) {
        mState = state;
        switch(mState) {
        case EMPTY:
            break;
        case RIVER:
            mRiver = d.drawFromDeck();
        case TURN:
            mTurn = d.drawFromDeck();
        case FLOP:
            for (int i = 0; i <= 2; i++) {
                mFlop[i] = d.drawFromDeck();
            }
        default:
            break;            
        }
    }

    public TexasTable(String s, TableState state) {
        mState = state;
        String[] inputString = s.split(" ");
        switch(mState) {
        case EMPTY:
            break;
        case RIVER:
            mRiver = new Card(inputString[4]);
        case TURN:
            mTurn = new Card(inputString[3]);
        case FLOP:
            for (int i = 0; i <= 2; i++) {
                mFlop[i] = new Card(inputString[i]);
            }
        default:
            break;            
        }
    }

    public void displayCards() {
        String outputString = "";
        switch(mState) {
        case EMPTY:
            System.out.println("The table is empty");
            break;
        case RIVER:
            outputString = mRiver.toString() + " ";
        case TURN:
            outputString = mTurn.toString() + " " + outputString;
        case FLOP:
            for (int i = 2; i >= 0; i--) {
                outputString = mFlop[i].toString() + " " + outputString;
            }
        default:
            break;            
        }
        System.out.println(outputString);
    }

    public TableState getState() {
        return mState;
    }

    public void displayState() {
        System.out.println(mState);
    }

    public Card[] getFlop() {
        return mFlop;
    }

    public Card getTurn() {
        return mTurn;
    }

    public Card getRiver() {
        return mRiver;
    }
}
