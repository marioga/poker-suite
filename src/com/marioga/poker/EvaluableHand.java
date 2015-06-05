package com.marioga.poker;

/**
 * This interface should be implemented by classes of hands 
 * to which a value can be assigned, that allows for them
 * to be compared to other hands of the same class.
 * @author marioga
 */
public interface EvaluableHand<T> extends Comparable<T> {
    public int[] getValue();

    public int getNumericalValue();

    public String getHandType();

    public void displayValue();

    public void displayNumericalValue();

    public void displayHandType();
}
