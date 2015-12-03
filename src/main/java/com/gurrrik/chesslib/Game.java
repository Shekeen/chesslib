package com.gurrrik.chesslib;

public class Game {
    private Board board;

    private Color playerToMove;

    private boolean whiteCanKingCastle;
    private boolean whiteCanQueenCastle;
    private boolean blackCanKingCastle;
    private boolean blackCanQueenCastle;

    private int enPassantSqi;

    private int halfMoveClock;
    private int fullMoveClock;

    public Game() {
        board = new Board();
        playerToMove = Color.WHITE;
        whiteCanKingCastle = true;
        whiteCanQueenCastle = true;
        blackCanKingCastle = true;
        blackCanQueenCastle = true;
        enPassantSqi = -1;
        halfMoveClock = 0;
        fullMoveClock = 1;
    }
}
