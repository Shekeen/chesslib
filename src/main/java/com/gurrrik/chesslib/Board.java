package com.gurrrik.chesslib;

import com.gurrrik.chesslib.util.IntegerRange;

import java.util.ArrayList;

public class Board {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final int SQUARES = WIDTH * HEIGHT;

    public static final int A1= 0,B1= 1,C1= 2,D1= 3,E1= 4,F1= 5,G1= 6,H1= 7,
                            A2= 8,B2= 9,C2=10,D2=11,E2=12,F2=13,G2=14,H2=15,
                            A3=16,B3=17,C3=18,D3=19,E3=20,F3=21,G3=22,H3=23,
                            A4=24,B4=25,C4=26,D4=27,E4=28,F4=29,G4=30,H4=31,
                            A5=32,B5=33,C5=34,D5=35,E5=36,F5=37,G5=38,H5=39,
                            A6=40,B6=41,C6=42,D6=43,E6=44,F6=45,G6=46,H6=47,
                            A7=48,B7=49,C7=50,D7=51,E7=52,F7=53,G7=54,H7=55,
                            A8=56,B8=57,C8=58,D8=59,E8=60,F8=61,G8=62,H8=63;

    public static final String[] SQUARE_NAMES =
            {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1",
             "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
             "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
             "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
             "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
             "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
             "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
             "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"};

    private ArrayList<Stone> board = new ArrayList<>(SQUARES);

    public Board() {
        for (int x: IntegerRange.halfInterval(0, SQUARES))
            board.add(null);
    }

    public static boolean isValidSqi(int sqi) {
        return sqi >= 0 && sqi < SQUARES;
    }

    public static int sqiToCol(int sqi) {
        if (sqi < 0 || sqi >= SQUARES)
            return -1;
        return sqi % WIDTH;
    }

    public static int sqiToRow(int sqi) {
        if (sqi < 0 || sqi >= SQUARES)
            return -1;
        return sqi / WIDTH;
    }

    public static int sqiRowDistance(int sqi1, int sqi2) {
        int sqi1Row = sqiToRow(sqi1);
        int sqi2Row = sqiToRow(sqi2);
        if (sqi1Row == -1 || sqi2Row == -1)
            return -1;
        return Math.abs(sqi1Row - sqi2Row);
    }

    public static int sqiColDistance(int sqi1, int sqi2) {
        int sqi1Col = sqiToCol(sqi1);
        int sqi2Col = sqiToCol(sqi2);
        if (sqi1Col == -1 || sqi2Col == -1)
            return -1;
        return Math.abs(sqi1Col - sqi2Col);
    }

    public static int coordsToSqi(int col, int row) {
        if (col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT)
            return -1;
        return row * WIDTH + col;
    }

    public static String sqiToName(int sqi) {
        if (!isValidSqi(sqi))
            return null;
        return SQUARE_NAMES[sqi];
    }

    public static int nameToSqi(String name) {
        if (name.length() != 2)
            return -1;
        char col = name.charAt(0);
        char row = name.charAt(1);
        if (col < 'a' || col > 'h' || row < '1' || row > '8')
            return -1;
        return coordsToSqi(col - 'a', row - '1');
    }

    public boolean isSquareEmpty(int sqi) {
        return getStone(sqi) == null;
    }

    public boolean isSquareEmpty(int col, int row) {
        return getStone(col, row) == null;
    }

    public Stone getStone(int sqi) {
        return board.get(sqi);
    }

    public Stone getStone(int col, int row) {
        return board.get(coordsToSqi(col, row));
    }

    public void setStone(int sqi, Stone stone) {
        board.set(sqi, stone);
    }

    public void setStone(int col, int row, Stone stone) {
        board.set(coordsToSqi(col, row), stone);
    }

    public void removeStone(int sqi) {
        board.set(sqi, null);
    }

    public void removeStone(int col, int row) {
        board.set(coordsToSqi(col, row), null);
    }

    public boolean moveStone(int sqiFrom, int sqiTo) {
        if (isSquareEmpty(sqiFrom) || !isSquareEmpty(sqiTo))
            return false;
        board.set(sqiTo, board.get(sqiFrom));
        board.set(sqiFrom, null);
        return true;
    }

    public boolean moveStone(int colFrom, int rowFrom, int colTo, int rowTo) {
        return moveStone(coordsToSqi(colFrom, rowFrom), coordsToSqi(colTo, rowTo));
    }

    public String toFENString() {
        StringBuilder sb = new StringBuilder();
        for (int row = HEIGHT - 1; row >= 0; row--) {
            int acc = 0;
            for (int col = 0; col < WIDTH; col++) {
                if (isSquareEmpty(col, row)) {
                    acc++;
                } else {
                    if (acc > 0) {
                        sb.append(acc);
                        acc = 0;
                    }
                    sb.append(getStone(col, row).getSANSymbol());
                }
            }
            if (acc > 0)
                sb.append(acc);
            if (row != 0)
                sb.append('/');
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return toFENString();
    }
}
