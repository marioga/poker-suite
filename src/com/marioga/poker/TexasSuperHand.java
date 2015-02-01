package com.marioga.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.marioga.poker.TexasTable.TableState;

/**
 * This class represents a Texas Hold'em hand formed
 * with the player's two cards, plus up to five cards
 * from the table.
 * @author marioga
 */
public class TexasSuperHand extends Hand implements
        EvaluableHand<TexasSuperHand> {
    private TableState mState;
    
    private StraightHand mBestHand = null; // Cards in the best hand

    public TexasSuperHand(TexasHand texasHand, TexasTable texasTable) {
        super(new Card[7]);
        mState = texasTable.getState();
        setCard(0, texasHand.getCards()[0]);
        setCard(1, texasHand.getCards()[1]);
        switch(mState) {
        case EMPTY:
            break;
        case RIVER:
            setCard(6, texasTable.getRiver());
        case TURN:
            setCard(5, texasTable.getTurn());
        case FLOP:
            for (int i = 0; i <= 2; i++) {
                setCard(i + 2, texasTable.getFlop()[i]);
            }
        default:
            break;            
        }
        initialize();
    }

    public void initialize() {
        mBestHand = computeBestHand();
    }
    
    public final StraightHand computeBestHand() {
        if (mState == TableState.EMPTY) {
            return null;
        }
        // We now generate subsets of five cards
        ArrayList<ArrayList<Card>> subsets = Subsets.generateSubsets(
                Arrays.copyOfRange(getCards(), 0, getSize()), 5);
        
        ArrayList<StraightHand> hands = new ArrayList<>();
        for (ArrayList<Card> s : subsets) {
            Card[] cards = new Card[5];
            for (int i = 0; i < 5; i++) {
                cards[i] = s.get(i);
            }
            hands.add(new StraightHand(cards));
        }
        
        return Collections.max(hands);
    }
    
    public int getSize() {
        switch(mState) {
        case EMPTY:
            return 2;
        case FLOP:
            return 5;
        case TURN:
            return 6;
        case RIVER:
            return 7;
        default:
            return -1;
        }
    }

    @Override
    public int[] getValue() {
        if (mBestHand != null) {
            return mBestHand.getValue();
        }
        return null;
    }

    @Override
    public int getNumericalValue() {
        if (mBestHand != null) {
            return mBestHand.getNumericalValue();
        }
        return -1;
    }

    @Override
    public String getHandType() {
        if (mBestHand != null) {
            return mBestHand.getHandType();
        }
        return "";
    }
    
    public void displayBestHand() {
        if (mBestHand != null) {
            mBestHand.displayHand();
        }
    }

    @Override
    public void displayValue() {
        System.out.println(Arrays.toString(getValue()));
    }

    @Override
    public void displayNumericalValue() {
        System.out.println(getNumericalValue());
    }

    @Override
    public void displayHandType() {
        System.out.println(getHandType());
    }

    @Override
    public int compareTo(TexasSuperHand that) {
        if (this.getNumericalValue() > that.getNumericalValue()) {
            return 1;
        } else if (this.getNumericalValue() < that.getNumericalValue()) {
            return -1;
        } else {
            return 0;
        }
    }
    
    static class StrictInequalityBestHand implements Comparator<TexasSuperHand> {
        @Override
        public int compare(TexasSuperHand o1, TexasSuperHand o2) {
            if (o1.compareTo(o2) != 0) {
                return o1.compareTo(o2);
            } else {
                for (int i = 0; i < 5; i++) {
                    if (o1.mBestHand.getCards()[i].compareTo(
                            o2.mBestHand.getCards()[i]) < 0) {
                        return -1;
                    } else if (o1.mBestHand.getCards()[i].compareTo(
                            o2.mBestHand.getCards()[i]) > 0) {
                        return 1;
                    }
                }
            }
            return 0;
        }
    }
}
