package com.gurrrik.chesslib;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class QueenTest {
    private Piece queen;

    @Before
    public void setUp() throws Exception {
        queen = new Queen();
    }

    @Test
    public void testIsValidMove() throws Exception {
        assertTrue(queen.isValidMove(Board.E2, Board.E3));
        assertTrue(queen.isValidMove(Board.H8, Board.A1));
        assertFalse(queen.isValidMove(Board.A1, Board.D2));
        assertFalse(queen.isValidMove(Board.C3, Board.H7));
    }

    @Test
    public void testGetTransitionalSquaresForMove() throws Exception {
        List<Integer> squares = queen.getTransitionalSquaresForMove(Board.D3, Board.D6);
        assertEquals(2, squares.size());
        assertEquals(Board.D4, (long)squares.get(0));
        assertEquals(Board.D5, (long)squares.get(1));

        assertNull(queen.getTransitionalSquaresForMove(Board.D3, Board.H8));
    }
}