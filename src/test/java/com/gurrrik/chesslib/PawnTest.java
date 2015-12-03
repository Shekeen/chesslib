package com.gurrrik.chesslib;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PawnTest {
    private Piece pawn;

    @Before
    public void setUp() throws Exception {
        pawn = new Pawn();
    }

    @Test
    public void testIsValidMove() throws Exception {
        assertTrue(pawn.isValidMove(Board.A2, Board.A4));
        assertTrue(pawn.isValidMove(Board.A5, Board.A7));
        assertFalse(pawn.isValidMove(Board.E2, Board.D2));
        assertFalse(pawn.isValidMove(Board.H8, Board.G7));
    }

    @Test
    public void testIsValidCaptureMove() throws Exception {
        assertTrue(pawn.isValidCaptureMove(Board.E2, Board.D3));
        assertTrue(pawn.isValidCaptureMove(Board.C7, Board.D6));
        assertFalse(pawn.isValidCaptureMove(Board.E2, Board.E3));
        assertFalse(pawn.isValidCaptureMove(Board.A1, Board.C3));
    }

    @Test
    public void testGetTransitionalSquaresForMove() throws Exception {
        assertEquals(0, pawn.getTransitionalSquaresForMove(Board.E2, Board.E3).size());

        List<Integer> squares = pawn.getTransitionalSquaresForMove(Board.C7, Board.C5);
        assertEquals(1, squares.size());
        assertEquals(Board.C6, (long)squares.get(0));

        assertNull(pawn.getTransitionalSquaresForMove(Board.E2, Board.D2));
    }
}