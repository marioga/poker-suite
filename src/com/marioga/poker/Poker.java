package com.marioga.poker;

/**
 * 
 * @author marioga
 */
public class Poker {

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
//        int numPlayers = 2;
//        int numMatches = 100000;
//        String fileName = "match_" + numMatches + "_" + numPlayers + ".txt";
//        RandomGenerator.generateMatchFile(fileName, numMatches, numPlayers);
//        Evaluator.evaluateMatchFile(fileName); 
        
        int numHands = 100000;
        String fileName = "hands_" + numHands + ".txt";
        RandomGenerator.generateHandFile(fileName, numHands);
        Evaluator.evaluateHandFile(fileName);

//        Deck d = new Deck();
//        d.shuffle();
//        int numPlayers = 3;
//        TexasHand[] texasHands = new TexasHand[numPlayers];
//        for (int i = 0; i < numPlayers; i++) {
//            texasHands[i] = new TexasHand(d);
//            System.out.println("Player "+ (i + 1) + ":");
//            texasHands[i].displayHand();
//        }
//        
//        TexasTable texasTable = new TexasTable(d, TableState.RIVER);
//        
//        System.out.println("Table:");
//        texasTable.displayCards();
//        
//        Evaluator.evaluateTexasTable(texasHands, texasTable);
        
        
//        TexasHand hand1 = new TexasHand("8H 2H");
//        TexasHand hand2 = new TexasHand("8D 5D");
//        TexasTable texasTable = 
//                new TexasTable("AH KC QC JS 2C", TableState.RIVER);
//        Evaluator.evaluateTexasTable(new TexasHand[]{ hand1, hand2 },
//                texasTable);
    }
}
