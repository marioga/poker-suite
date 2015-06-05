package com.marioga.poker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This utility class generates subsets of an array of a given size.
 * @author marioga
 */
public class Subsets {
    public static <Item> ArrayList<ArrayList<Item>> generateSubsets(
            Item[] myArray, int elem) {
        int size = myArray.length;
        ArrayList<ArrayList<Item>> fillingArray = new ArrayList<>();
        
        if (elem == 1) {
            for (int i = 0; i < size; i++) {
                ArrayList<Item> temp = new ArrayList<>();
                temp.add(myArray[i]);
                fillingArray.add(temp);
            }
            return fillingArray;
        }

        for (int i = 0; i <= size - elem; i++) {
            Item[] tempArray = Arrays.copyOfRange(myArray, i + 1, size);
            for (ArrayList<Item> a : generateSubsets(tempArray, elem - 1)) {
                a.add(0, myArray[i]);
                fillingArray.add(a);
            }
        }
        return fillingArray;
    }
}
