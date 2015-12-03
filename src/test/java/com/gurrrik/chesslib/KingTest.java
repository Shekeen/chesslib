package com.gurrrik.chesslib;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {
    private Piece king;

    @Before
    public void setUp() throws Exception {
        king = new King();
    }

    @Test
    public void testIsValidMove() throws Exception {
        assertTrue(king.isValidMove(Board.E2, Board.E3));
        assertTrue(king.isValidMove(Board.H8, Board.G7));
        assertFalse(king.isValidMove(Board.A1, Board.C1));
        assertFalse(king.isValidMove(Board.C3, Board.H7));
    }

    @Test
    public void testGetTransitionalSquaresForMove() throws Exception {
        assertEquals(0, king.getTransitionalSquaresForMove(Board.E3, Board.F4).size());
        assertNull(king.getTransitionalSquaresForMove(Board.C3, Board.C5));
    }
}