package com.marioga.poker;

/**
 * 
 * This class generates a file with random poker hands
 */

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

import com.marioga.poker.Card.Rank;
import com.marioga.poker.Card.Suit;

public class RandomGenerator {
    private static final Random RANDOM = new Random();

    private static void generateFile(String fileName, int numberOfHands,
            int handsPerLine) {
        BufferedWriter writer = null;
        String line;
        String temp;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"));
            writer.write(handsPerLine + System.getProperty("line.separator"));
            for (int i = 0; i < numberOfHands; i++) {
                line = "";
                for (int j = 0; j < handsPerLine; j++) {
                    for (int k = 0; k < 5; k++) {
                        do {
                            char rank = Rank.values()[RANDOM.nextInt(13)]
                                    .getShortRepr();
                            char suit = Suit.values()[RANDOM.nextInt(4)]
                                    .getShortRepr();
                            temp = Character.toString(rank)
                                    + Character.toString(suit);
                        } while (line.contains(temp));
                        if (j < handsPerLine - 1 || k < 4) {
                            line += temp + " ";
                        } else {
                            line += temp;
                        }
                    }
                }
                writer.write(line + System.getProperty("line.separator"));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void generateHandFile(String fileName, int numberOfHands) {
        generateFile(fileName, numberOfHands, 1);
    }

    public static void generateMatchFile(String fileName, int numberOfHands) {
        generateFile(fileName, numberOfHands, 2);
    }

    public static void generateMatchFile(String fileName, int numberOfHands,
            int numberOfPlayers) {
        generateFile(fileName, numberOfHands, numberOfPlayers);
    }
}
