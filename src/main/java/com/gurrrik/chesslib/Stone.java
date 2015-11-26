package com.gurrrik.chesslib;

import java.util.List;

public class Stone {
    private Color color;
    private Piece piece;

    public Stone(Color color, Piece piece) {
        this.color = color;
        this.piece = piece;
    }

    public Color getColor() {
        return color;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isValidMove(int sqiFrom, int sqiTo) {
        return piece.isValidMove(sqiFrom, sqiTo);
    }

    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return piece.isValidCaptureMove(sqiFrom, sqiTo);
    }

    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        return piece.getTransitionalSquaresForMove(sqiFrom, sqiTo);
    }
}
