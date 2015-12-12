package com.gurrrik.chesslib;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testGetOtherPlayer() throws Exception {

    }

    @Test
    public void testTryMove() throws Exception {

    }

    @Test
    public void testFromFENString() throws Exception {
        Game game = Game.fromFENString(Game.STARTING_FEN);
        assertEquals(Color.WHITE, game.getPlayerToMove());
        assertTrue(game.isWhiteCanKingCastle());
        assertTrue(game.isWhiteCanQueenCastle());
        assertTrue(game.isBlackCanKingCastle());
        assertTrue(game.isBlackCanQueenCastle());
        assertEquals(-1, game.getEnPassantSqi());
        assertEquals(0, game.getHalfMoveClock());
        assertEquals(0, game.getFullMoveClock());

        Board board = game.getBoard();
        assertNull(board.getStone(Board.C3));
        assertNull(board.getStone(Board.H5));
        assertEquals(Color.WHITE, board.getStone(Board.E2).getColor());
        assertTrue(board.getStone(Board.E2).getPiece() instanceof Pawn);
        assertEquals(Color.BLACK, board.getStone(Board.B8).getColor());
        assertTrue(board.getStone(Board.B8).getPiece() instanceof Knight);
    }

    @Test
    public void testToFENString() throws Exception {

    }
}