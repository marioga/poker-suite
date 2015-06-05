package com.marioga.poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.marioga.poker.TexasSuperHand.StrictInequalityBestHand;

public class Evaluator {
    private static BufferedReader reader = null;
    
    public static void evaluateHandFile(String fileName) {
        try {
            String currentLine;
            int[] handTypeCounter = new int[9];
            
            reader = new BufferedReader(new FileReader(fileName));
            
            int numHands = Integer.parseInt(reader.readLine());
            assert(numHands == 1);
            
            while ((currentLine = reader.readLine()) != null) {
                StraightHand myHand = new StraightHand(
                        currentLine.substring(0, 14));
                handTypeCounter[myHand.getValue()[0]]++;
            }
            System.out.println("Types of hands in " + fileName + ":");
            for (int i = 0; i <= 8; i++) {
                System.out.println(Hand.POKER_HAND_TYPE[i] + ": "
                        + handTypeCounter[i]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void evaluateMatchFile(String fileName) {
        try {
            String currentLine;
            int[] stats;

            reader = new BufferedReader(new FileReader(fileName));

            int numHands = Integer.parseInt(reader.readLine());
            assert(numHands > 1);
            
            stats = new int[numHands + 1];

            while ((currentLine = reader.readLine()) != null) {
                StraightHand winnerHand = new StraightHand(
                        currentLine.substring(0, 14));
                int winnerPosition = 1;
                for (int i = 1; i < numHands; i++) {
                    StraightHand hand = new StraightHand(
                            currentLine.substring(15 * i, 15 * i + 14));
                    if (hand.compareTo(winnerHand) > 0) {
                        winnerHand = hand;
                        winnerPosition = i + 1;
                    } else if (hand.compareTo(winnerHand) == 0) {
                        winnerPosition = 0;
                    }
                }
                stats[winnerPosition]++;
            }
            
            System.out.println("There were " +
                    Integer.toString(stats[0]) + " ties.");
            for (int i = 1; i <= numHands; i++) {
                System.out.println("Player " + i + " won " + stats[i]
                        + " times.");
            }  
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void evaluateTexasTable(TexasHand[] texasHands,
            TexasTable texasTable) {
        Map<TexasSuperHand, List<Integer>> superHands = 
                new TreeMap<>(Collections.reverseOrder(
                        new StrictInequalityBestHand()));
        for (int i = 0; i < texasHands.length; i++) {
            TexasSuperHand superHand = new TexasSuperHand(texasHands[i],
                    texasTable);
            if (!superHands.containsKey(superHand)) {
               superHands.put(superHand, new ArrayList<Integer>());
            }
            superHands.get(superHand).add(i + 1);
        }
        
        for (TexasSuperHand t : superHands.keySet()) {
            for (Integer i : superHands.get(t)) {
                System.out.println("Player " + i +" has: "
                        + t.getHandType());
                t.displayBestHand();
            }
        }
    }
}
