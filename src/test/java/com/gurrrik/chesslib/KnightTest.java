package com.gurrrik.chesslib;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {
    private Piece knight;

    @Before
    public void setUp() throws Exception {
        knight = new Knight();
    }

    @Test
    public void testIsValidMove() throws Exception {
        assertTrue(knight.isValidMove(Board.B2, Board.C4));
        assertTrue(knight.isValidMove(Board.B1, Board.A3));
        assertFalse(knight.isValidMove(Board.A1, Board.H8));
        assertFalse(knight.isValidMove(Board.E2, Board.E4));
    }

    @Test
    public void testGetTransitionalSquaresForMove() throws Exception {
        assertEquals(0, knight.getTransitionalSquaresForMove(Board.B2, Board.C4).size());
        assertNull(knight.getTransitionalSquaresForMove(Board.A1, Board.A2));
    }
}