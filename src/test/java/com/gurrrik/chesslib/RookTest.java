package com.gurrrik.chesslib;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RookTest {
    private Piece rook;

    @Before
    public void setUp() throws Exception {
        rook = new Rook();
    }

    @Test
    public void testIsValidMove() throws Exception {
        assertTrue(rook.isValidMove(Board.H3, Board.E3));
        assertTrue(rook.isValidMove(Board.H8, Board.H1));
        assertFalse(rook.isValidMove(Board.A1, Board.B2));
        assertFalse(rook.isValidMove(Board.C3, Board.H7));
    }

    @Test
    public void testGetTransitionalSquaresForMove() throws Exception {
        List<Integer> squares = rook.getTransitionalSquaresForMove(Board.H8, Board.E8);
        assertEquals(2, squares.size());
        assertEquals(Board.G8, (long)squares.get(0));
        assertEquals(Board.F8, (long)squares.get(1));

        assertNull(rook.getTransitionalSquaresForMove(Board.C3, Board.A8));
    }
}