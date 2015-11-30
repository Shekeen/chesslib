package com.gurrrik.chesslib;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testIsValidSqi() throws Exception {
        assertTrue(Board.isValidSqi(Board.A3));
        assertTrue(Board.isValidSqi(Board.B6));
        assertFalse(Board.isValidSqi(-1));
        assertFalse(Board.isValidSqi(Board.SQUARES));
    }

    @Test
    public void testSqiToCol() throws Exception {
        assertEquals(0, Board.sqiToCol(Board.A3));
        assertEquals(1, Board.sqiToCol(Board.B5));
        assertEquals(7, Board.sqiToCol(Board.H8));
    }

    @Test
    public void testSqiToRow() throws Exception {
        assertEquals(2, Board.sqiToRow(Board.A3));
        assertEquals(4, Board.sqiToRow(Board.B5));
        assertEquals(7, Board.sqiToRow(Board.H8));
    }

    @Test
    public void testSqiRowDistance() throws Exception {
        assertEquals(2, Board.sqiRowDistance(Board.A3, Board.B5));
        assertEquals(3, Board.sqiRowDistance(Board.B5, Board.H8));
    }

    @Test
    public void testSqiColDistance() throws Exception {
        assertEquals(1, Board.sqiColDistance(Board.A3, Board.B5));
        assertEquals(6, Board.sqiColDistance(Board.B5, Board.H8));
    }

    @Test
    public void testCoordsToSqi() throws Exception {
        assertEquals(Board.A3, Board.coordsToSqi(0, 2));
        assertEquals(Board.B5, Board.coordsToSqi(1, 4));
        assertEquals(Board.H8, Board.coordsToSqi(7, 7));
    }

    @Test
    public void testIsSquareEmpty() throws Exception {
        assertTrue(board.isSquareEmpty(Board.A4));
        assertTrue(board.isSquareEmpty(Board.E6));

        board.setStone(Board.E4, new Stone(Color.WHITE, new Rook()));
        assertFalse(board.isSquareEmpty(Board.E4));

        board.removeStone(Board.E4);
        assertTrue(board.isSquareEmpty(Board.E4));
    }

    @Test
    public void testIsSquareEmpty1() throws Exception {
        assertTrue(board.isSquareEmpty(0, 3));
        assertTrue(board.isSquareEmpty(4, 5));

        board.setStone(4, 3, new Stone(Color.WHITE, new Rook()));
        assertFalse(board.isSquareEmpty(4, 3));

        board.removeStone(4, 3);
        assertTrue(board.isSquareEmpty(4, 3));
    }

    @Test
    public void testGetStone() throws Exception {
        assertNull(board.getStone(Board.E3));
        assertNull(board.getStone(Board.C8));

        board.setStone(Board.B2, new Stone(Color.WHITE, new Rook()));
        assertNotNull(board.getStone(Board.B2));

        board.removeStone(Board.B2);
        assertNull(board.getStone(Board.B2));
    }

    @Test
    public void testGetStone1() throws Exception {
        assertNull(board.getStone(4, 2));
        assertNull(board.getStone(2, 7));

        board.setStone(1, 1, new Stone(Color.WHITE, new Rook()));
        assertNotNull(board.getStone(1, 1));

        board.removeStone(1, 1);
        assertNull(board.getStone(1, 1));
    }

    @Test
    public void testSetAndRemoveStone() throws Exception {
        Stone stone = new Stone(Color.WHITE, new Rook());
        board.setStone(Board.E3, stone);
        assertEquals(stone, board.getStone(Board.E3));

        board.removeStone(Board.E3);
        assertNull(board.getStone(Board.E3));
    }

    @Test
    public void testSetAndRemoveStone1() throws Exception {
        Stone stone = new Stone(Color.WHITE, new Rook());
        board.setStone(4, 2, stone);
        assertEquals(stone, board.getStone(4, 2));

        board.removeStone(4, 2);
        assertNull(board.getStone(4, 2));
    }

    @Test
    public void testMoveStone() throws Exception {
        Stone stone = new Stone(Color.BLACK, new Bishop());
        board.setStone(Board.C3, stone);
        assertEquals(stone, board.getStone(Board.C3));

        board.moveStone(Board.C3, Board.H5);
        assertNull(board.getStone(Board.C3));
        assertEquals(stone, board.getStone(Board.H5));

        board.removeStone(Board.H5);
        assertNull(board.getStone(Board.H5));
    }

    @Test
    public void testMoveStone1() throws Exception {
        Stone stone = new Stone(Color.BLACK, new Bishop());
        board.setStone(2, 2, stone);
        assertEquals(stone, board.getStone(2, 2));

        board.moveStone(2, 2, 7, 4);
        assertNull(board.getStone(2, 2));
        assertEquals(stone, board.getStone(7, 4));

        board.removeStone(7, 4);
        assertNull(board.getStone(7, 4));
    }
}