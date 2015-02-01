package com.marioga.poker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marioga.poker.Card.Rank;
import com.marioga.poker.Card.Suit;
import com.marioga.poker.TexasTable.TableState;

public class AllTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    private final String LINE_SEPARATOR = 
            System.getProperty("line.separator");

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    
    @Test
    public void testCard() {
        Card c1 = new Card("3H");
        assertTrue(c1.getRank() == Rank.THREE);
        assertTrue(c1.getSuit() == Suit.HEARTS);
        
        Card c2 = new Card(Rank.ACE, Suit.CLUBS);
        assertEquals(c2.toLongString(), "Ace of Clubs");
        
        Card c3 = new Card(Rank.ACE, Suit.DIAMONDS);
        
        assertTrue(c3.compareTo(c2) < 0);
        assertFalse(c2.compareTo(c3) < 0);
    }
    
    @Test
    public void testDeck() {
        Deck d = new Deck();
        d.shuffle();
        assertTrue(d.getSize() == 52);
        d.drawFromDeck();
        d.drawFromDeck();
        assertTrue(d.getSize() == 50);
    }
    
    @Test
    public void testHandClasses() {
        Deck d = new Deck();
        d.shuffle();
        Hand h1 = new Hand(d, 5);
        h1.orderHand();
        for (int i = 0; i < 4; i++) {
            assertTrue(h1.getCards()[i].compareTo(h1.getCards()[i + 1]) > 0);
        }
        
        Hand h2 = new Hand("3S 8D 3H", 3);
        h2.displayHand();
        assertEquals("3S 8D 3H " + LINE_SEPARATOR, outContent.toString());
        outContent.reset();
        h2.orderHand();
        h2.displayHand();
        assertEquals("8D 3S 3H " + LINE_SEPARATOR, outContent.toString());
        outContent.reset();
        
        StraightHand sh1 = new StraightHand("3D 4D 8D AD TD");
        sh1.displayHandType();
        assertEquals("Flush" + LINE_SEPARATOR,
                outContent.toString());
        outContent.reset();
        
        StraightHand sh2 = new StraightHand("5H 3H 8D 3D 4H");
        sh2.displayHandType();
        assertEquals("One Pair" + LINE_SEPARATOR,
                outContent.toString());
        outContent.reset();
        
        assertTrue(sh1.compareTo(sh2) > 0);
        
        StraightHand sh3 = new StraightHand("5C 3C 3D 8H 4S");
        assertTrue(sh2.compareTo(sh3) == 0);
    }
    
    @Test
    public void testTexasHoldem() {
        TexasHand th1 = new TexasHand("2H 6H");
        TexasHand th2 = new TexasHand("7D AH");
        TexasTable tt1 = new TexasTable("AS 3H 9H AD TH",
                TableState.RIVER);
        TexasSuperHand tsh1 = new TexasSuperHand(th1, tt1);
        tsh1.displayBestHand();
        assertEquals("TH 9H 6H 3H 2H " + LINE_SEPARATOR, 
                outContent.toString());
        outContent.reset();
        assertEquals(tsh1.getHandType(), "Flush");
        TexasSuperHand tsh2 = new TexasSuperHand(th2, tt1);
        tsh2.displayBestHand();
        assertEquals("AD AS AH TH 9H " + LINE_SEPARATOR, 
                outContent.toString());
        outContent.reset();
        assertEquals(tsh2.getHandType(), "Three of a Kind");
        
        TexasHand th3 = new TexasHand("2H 3D");
        TexasHand th4 = new TexasHand("3S 4H");
        TexasTable tt2 = new TexasTable("AS 7D 9H AD TH",
                TableState.RIVER);
        TexasSuperHand tsh3 = new TexasSuperHand(th3, tt2);
        tsh3.displayBestHand();
        assertEquals("AD AS TH 9H 7D " + LINE_SEPARATOR, 
                outContent.toString());
        outContent.reset();
        assertEquals(tsh3.getHandType(), "One Pair");
        TexasSuperHand tsh4 = new TexasSuperHand(th4, tt2);
        tsh4.displayBestHand();
        assertEquals("AD AS TH 9H 7D " + LINE_SEPARATOR, 
                outContent.toString());
        outContent.reset();
        assertTrue(tsh3.compareTo(tsh4) == 0);
        TexasSuperHand.StrictInequalityBestHand customComp = 
                new TexasSuperHand.StrictInequalityBestHand();
        assertTrue(customComp.compare(tsh3, tsh4) == 0);
        
        TexasHand th5 = new TexasHand("6H 5H");
        TexasHand th6 = new TexasHand("3S 6D");
        TexasTable tt3 = new TexasTable("AS 4D 9H AD TH",
                TableState.RIVER);
        TexasSuperHand tsh5 = new TexasSuperHand(th5, tt3);
        tsh5.displayBestHand();
        assertEquals("AD AS TH 9H 6H " + LINE_SEPARATOR, 
                outContent.toString());
        outContent.reset();
        assertEquals(tsh5.getHandType(), "One Pair");
        TexasSuperHand tsh6 = new TexasSuperHand(th6, tt3);
        tsh6.displayBestHand();
        assertEquals("AD AS TH 9H 6D " + LINE_SEPARATOR, 
                outContent.toString());
        outContent.reset();
        assertEquals(tsh6.getHandType(), "One Pair");
        assertTrue(tsh6.compareTo(tsh6) == 0);
        assertTrue(customComp.compare(tsh5, tsh6) < 0);
    }
}
