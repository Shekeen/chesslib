package com.gurrrik.chesslib;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoneTest {
    private Stone whitePawn;
    private Stone blackPawn;

    @Before
    public void setUp() throws Exception {
        whitePawn = new Stone(Color.WHITE, new Pawn());
        blackPawn = new Stone(Color.BLACK, new Pawn());
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(Color.WHITE, whitePawn.getColor());
        assertEquals(Color.BLACK, blackPawn.getColor());
    }

    @Test
    public void testIsValidMove() throws Exception {
        assertTrue(whitePawn.isValidMove(Board.E2, Board.E4));
        assertTrue(whitePawn.isValidMove(Board.E3, Board.E4));
        assertTrue(blackPawn.isValidMove(Board.E7, Board.E5));
        assertTrue(blackPawn.isValidMove(Board.E6, Board.E5));
        assertFalse(whitePawn.isValidMove(Board.E3, Board.E5));
        assertFalse(whitePawn.isValidMove(Board.E7, Board.E6));
        assertFalse(blackPawn.isValidMove(Board.E6, Board.E4));
        assertFalse(blackPawn.isValidMove(Board.E2, Board.E3));
    }

    @Test
    public void testIsValidCaptureMove() throws Exception {
        assertTrue(whitePawn.isValidCaptureMove(Board.E4, Board.D5));
        assertTrue(blackPawn.isValidCaptureMove(Board.E5, Board.F4));
        assertFalse(whitePawn.isValidCaptureMove(Board.E5, Board.D4));
        assertFalse(blackPawn.isValidCaptureMove(Board.E4, Board.F5));
    }
}