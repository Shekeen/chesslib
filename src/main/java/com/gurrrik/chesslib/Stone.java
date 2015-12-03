package com.gurrrik.chesslib;

import java.util.List;

public class Stone {
    private Color color;
    private Piece piece;
    private boolean isPawn;

    public Stone(Color color, Piece piece) {
        this.color = color;
        this.piece = piece;
        this.isPawn = piece instanceof Pawn;
    }

    public Color getColor() {
        return color;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isValidMove(int sqiFrom, int sqiTo) {
        if (!Board.isValidSqi(sqiFrom) || !Board.isValidSqi(sqiTo))
            return false;

        boolean validMove = piece.isValidMove(sqiFrom, sqiTo);
        if (isPawn) {
            boolean correctStartSqi = true;
            switch (color) {
                case WHITE:
                    if (Board.sqiRowDistance(sqiFrom, sqiTo) == 2)
                        correctStartSqi = Board.sqiToRow(sqiFrom) == 1;
                    return sqiTo > sqiFrom && validMove && correctStartSqi;
                case BLACK:
                    if (Board.sqiRowDistance(sqiFrom, sqiTo) == 2)
                        correctStartSqi = Board.sqiToRow(sqiFrom) == 6;
                    return sqiTo < sqiFrom && validMove && correctStartSqi;
                default:
                    return false;
            }
        } else {
            return validMove;
        }
    }

    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        if (!Board.isValidSqi(sqiFrom) || !Board.isValidSqi(sqiTo))
            return false;

        boolean validCaptureMove = piece.isValidCaptureMove(sqiFrom, sqiTo);
        if (isPawn) {
            switch (color) {
                case WHITE:
                    return sqiTo > sqiFrom && validCaptureMove;
                case BLACK:
                    return sqiTo < sqiFrom && validCaptureMove;
                default:
                    return false;
            }
        } else {
            return validCaptureMove;
        }
    }

    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        if (!Board.isValidSqi(sqiFrom) || !Board.isValidSqi(sqiTo))
            return null;

        return piece.getTransitionalSquaresForMove(sqiFrom, sqiTo);
    }

    public char getSANSymbol() {
        char c = piece.getSANSymbol();
        if (color == Color.WHITE)
            return Character.toUpperCase(c);
        else
            return Character.toLowerCase(c);
    }
}
