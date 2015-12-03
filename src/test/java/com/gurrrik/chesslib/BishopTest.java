package com.gurrrik.chesslib;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BishopTest {
    private Piece bishop;

    @Before
    public void setUp() throws Exception {
        bishop = new Bishop();
    }

    @Test
    public void testIsValidMove() throws Exception {
        assertTrue(bishop.isValidMove(Board.F1, Board.B5));
        assertTrue(bishop.isValidMove(Board.H8, Board.G7));
        assertFalse(bishop.isValidMove(Board.A1, Board.C1));
        assertFalse(bishop.isValidMove(Board.C3, Board.H7));
    }

    @Test
    public void testGetTransitionalSquaresForMove() throws Exception {
        List<Integer> squares = bishop.getTransitionalSquaresForMove(Board.C1, Board.F4);
        assertEquals(2, squares.size());
        assertEquals(Board.D2, (long)squares.get(0));
        assertEquals(Board.E3, (long)squares.get(1));

        assertNull(bishop.getTransitionalSquaresForMove(Board.A1, Board.D2));
    }
}