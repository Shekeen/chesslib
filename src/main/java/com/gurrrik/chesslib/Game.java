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

    public Color getPlayerToMove() {
        return playerToMove;
    }

    public boolean isWhiteCanKingCastle() {
        return whiteCanKingCastle;
    }

    public boolean isWhiteCanQueenCastle() {
        return whiteCanQueenCastle;
    }

    public boolean isBlackCanKingCastle() {
        return blackCanKingCastle;
    }

    public boolean isBlackCanQueenCastle() {
        return blackCanQueenCastle;
    }

    public int getEnPassantSqi() {
        return enPassantSqi;
    }

    public int getHalfMoveClock() {
        return halfMoveClock;
    }

    public int getFullMoveClock() {
        return fullMoveClock;
    }

    public Color getOtherPlayer(Color player) {
        switch (player) {
            case WHITE:
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }

    public boolean tryMove(int sqiFrom, int sqiTo) {
        // check if a move is a castle, if it is, make the move
        if (tryCastleMove(sqiFrom, sqiTo))
            return true;
        // check if a move is en passant, if it is, make the move
        else if (tryEnPassantMove(sqiFrom, sqiTo))
            return true;

        Stone stoneFrom = board.getStone(sqiFrom);
        // you have to move a stone
        if (stoneFrom == null)
            return false;
        // you can't move another player's stone
        if (stoneFrom.getColor() != playerToMove)
            return false;

        Stone stoneTo = board.getStone(sqiTo);
        // you can't capture your own stone
        if (stoneTo != null && stoneTo.getColor() == playerToMove)
            return false;
        // the stone can't capture like this
        if (stoneTo != null && !stoneFrom.isValidCaptureMove(sqiFrom, sqiTo))
            return false;
        // the stone can't move like this
        if (stoneTo == null && !stoneFrom.isValidMove(sqiFrom, sqiTo))
            return false;

        return false;
    }

    private boolean tryCastleMove(int sqiFrom, int sqiTo) {
        return false;
    }

    private boolean tryEnPassantMove(int sqiFrom, int sqiTo) {
        Stone stoneFrom = board.getStone(sqiFrom);
        if (stoneFrom == null)
            return false;
        boolean isPawn = stoneFrom.getPiece() instanceof Pawn;
        if (sqiTo == enPassantSqi && isPawn
                && stoneFrom.isValidCaptureMove(sqiFrom, sqiTo))
        {
            board.moveStone(sqiFrom, sqiTo);
            enPassantSqi = -1;
            playerToMove = getOtherPlayer(playerToMove);
            halfMoveClock = 0;
            if (playerToMove == Color.WHITE)
                fullMoveClock++;
        }
        return false;
    }

    public String toFENString() {
        StringBuilder sb = new StringBuilder();
        sb.append(board.toFENString());
        sb.append(' ');

        if (playerToMove == Color.WHITE)
            sb.append("w ");
        else
            sb.append("b ");

        if (whiteCanKingCastle
                || whiteCanQueenCastle
                || blackCanKingCastle
                || blackCanQueenCastle) {
            if (whiteCanKingCastle)
                sb.append('K');
            if (whiteCanQueenCastle)
                sb.append('Q');
            if (blackCanKingCastle)
                sb.append('k');
            if (whiteCanQueenCastle)
                sb.append('q');
            sb.append(' ');
        } else {
            sb.append("- ");
        }

        if (enPassantSqi == -1) {
            sb.append("- ");
        } else {
            sb.append(Board.sqiToName(enPassantSqi));
            sb.append(' ');
        }

        sb.append(halfMoveClock);
        sb.append(' ');

        sb.append(fullMoveClock);

        return sb.toString();
    }

    @Override
    public String toString() {
        return toFENString();
    }
}
