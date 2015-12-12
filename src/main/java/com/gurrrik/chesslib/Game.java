package com.gurrrik.chesslib;

import java.util.ArrayList;
import java.util.List;

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

    public Game(Board board, Color playerToMove,
                boolean whiteCanKingCastle, boolean whiteCanQueenCastle,
                boolean blackCanKingCastle, boolean blackCanQueenCastle,
                int enPassantSqi, int halfMoveClock, int fullMoveClock) {
        this.board = board;
        this.playerToMove = playerToMove;
        this.whiteCanKingCastle = whiteCanKingCastle;
        this.whiteCanQueenCastle = whiteCanQueenCastle;
        this.blackCanKingCastle = blackCanKingCastle;
        this.blackCanQueenCastle = blackCanQueenCastle;
        this.enPassantSqi = enPassantSqi;
        this.halfMoveClock = halfMoveClock;
        this.fullMoveClock = fullMoveClock;
    }

    public Color getPlayerToMove() {
        return playerToMove;
    }

    public Board getBoard() {
        return board;
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
        // first try to make a special move (castle, en passant)
        if (tryCastleMove(sqiFrom, sqiTo))
            return true;
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

        for (int sqi: stoneFrom.getTransitionalSquaresForMove(sqiFrom, sqiTo))
            if (board.getStone(sqi) != null)
                return false;

        board.moveStone(sqiFrom, sqiTo);
        //TODO: promote pawn if necessary

        if (stoneFrom.getPiece() instanceof Pawn &&
                Board.sqiRowDistance(sqiFrom, sqiTo) == 2) {
            enPassantSqi = (sqiFrom + sqiTo) / 2;
        } else {
            enPassantSqi = -1;
        }

        if (stoneFrom.getPiece() instanceof King) {
            switch (stoneFrom.getColor()) {
                case WHITE:
                    whiteCanKingCastle = false;
                    whiteCanQueenCastle = false;
                    break;
                case BLACK:
                    blackCanKingCastle = false;
                    blackCanQueenCastle = false;
                    break;
                default:
                    break;
            }
        }
        if (stoneFrom.getPiece() instanceof Rook) {
            if (stoneFrom.getColor() == Color.WHITE) {
                if (sqiFrom == Board.A1)
                    whiteCanQueenCastle = false;
                else if (sqiFrom == Board.H1)
                    whiteCanKingCastle = false;
            } else {
                if (sqiFrom == Board.A8)
                    blackCanQueenCastle = false;
                else if (sqiFrom == Board.H8)
                    blackCanKingCastle = false;
            }
        }

        playerToMove = getOtherPlayer(playerToMove);

        if (stoneFrom.getPiece() instanceof Pawn ||
                stoneTo != null)
            halfMoveClock = 0;
        else
            halfMoveClock++;

        if (playerToMove == Color.WHITE)
            fullMoveClock++;

        //TODO: check half move clock for 50 move rule
        return true;
    }

    private boolean tryCastleMove(int sqiFrom, int sqiTo) {
        Stone stoneFrom = board.getStone(sqiFrom);
        if (stoneFrom == null)
            return false;
        boolean isKing = stoneFrom.getPiece() instanceof King;
        if (isKing) {
            switch (stoneFrom.getColor()) {
                case WHITE:
                    if (sqiFrom == Board.E1 && sqiTo == Board.G1)
                        return tryWhiteKingSideCastleMove();
                    else if (sqiFrom == Board.E1 && sqiTo == Board.C1)
                        return tryWhiteQueenSideCastleMove();
                    break;
                case BLACK:
                    if (sqiFrom == Board.E8 && sqiTo == Board.G8)
                        return tryBlackKingSideCastleMove();
                    else if (sqiFrom == Board.E8 && sqiTo == Board.C8)
                        return tryBlackQueenSideCastleMove();
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    private boolean tryWhiteKingSideCastleMove() {
        if (whiteCanKingCastle) {
            List<Integer> intermediateSqis = new ArrayList<>();
            intermediateSqis.add(Board.F1);
            intermediateSqis.add(Board.G1);
            return tryCastleMoveHelper(Board.E1, Board.G1, Board.H1, Board.F1,
                    Color.WHITE, intermediateSqis);
        }
        return false;
    }

    private boolean tryWhiteQueenSideCastleMove() {
        if (whiteCanQueenCastle) {
            List<Integer> intermediateSqis = new ArrayList<>();
            intermediateSqis.add(Board.B1);
            intermediateSqis.add(Board.C1);
            intermediateSqis.add(Board.D1);
            return tryCastleMoveHelper(Board.E1, Board.C1, Board.A1, Board.D1,
                    Color.WHITE, intermediateSqis);
        }
        return false;
    }

    private boolean tryBlackKingSideCastleMove() {
        if (blackCanKingCastle) {
            List<Integer> intermediateSqis = new ArrayList<>();
            intermediateSqis.add(Board.F8);
            intermediateSqis.add(Board.G8);
            return tryCastleMoveHelper(Board.E8, Board.G8, Board.H8, Board.F8,
                    Color.BLACK, intermediateSqis);
        }
        return false;
    }

    private boolean tryBlackQueenSideCastleMove() {
        if (blackCanQueenCastle) {
            List<Integer> intermediateSqis = new ArrayList<>();
            intermediateSqis.add(Board.B8);
            intermediateSqis.add(Board.C8);
            intermediateSqis.add(Board.D8);
            return tryCastleMoveHelper(Board.E8, Board.C8, Board.A8, Board.D8,
                    Color.BLACK, intermediateSqis);
        }
        return false;
    }

    private boolean tryCastleMoveHelper(int sqiKingFrom, int sqiKingTo,
                                        int sqiRookFrom, int sqiRookTo,
                                        Color color, List<Integer> intermediateSqis) {
        Stone king = board.getStone(sqiKingFrom);
        Stone rook = board.getStone(sqiRookFrom);
        assert king.getPiece() instanceof King && king.getColor() == color;
        if (rook == null || !(rook.getPiece() instanceof Rook) || rook.getColor() != color)
            return false;
        for (int sqi: intermediateSqis)
            if (board.getStone(sqi) != null)
                return false;
        // TODO: also check if squares are under attack
        board.moveStone(sqiKingFrom, sqiKingTo);
        board.moveStone(sqiRookFrom, sqiRookTo);
        enPassantSqi = -1;
        playerToMove = getOtherPlayer(playerToMove);
        halfMoveClock++;
        if (playerToMove == Color.WHITE)
            fullMoveClock++;
        switch (color) {
            case WHITE:
                whiteCanKingCastle = false;
                whiteCanQueenCastle = false;
                break;
            case BLACK:
                blackCanKingCastle = false;
                blackCanQueenCastle = false;
                break;
            default:
                break;
        }
        return true;
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
            return true;
        }
        return false;
    }

    public static String STARTING_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -1 0 0";

    public static Game fromFENString(String fen) {
        String[] fields = fen.split("\\s+");

        assert fields.length == 6;

        assert fields[1].length() == 1;
        char playerToMoveChar = fields[1].charAt(0);
        assert playerToMoveChar == 'w' || playerToMoveChar == 'b';

        boolean whiteCanKingCastle = false;
        boolean whiteCanQueenCastle = false;
        boolean blackCanKingCastle = false;
        boolean blackCanQueenCastle = false;
        assert fields[2].length() < 5;
        if (fields[2].charAt(0) != '-') {
            whiteCanKingCastle = fields[2].contains("K");
            whiteCanQueenCastle = fields[2].contains("Q");
            blackCanKingCastle = fields[2].contains("k");
            blackCanQueenCastle = fields[2].contains("q");
        }

        int enPassantSqi = -1;
        if (fields[3].charAt(0) != '-') {
            enPassantSqi = Board.nameToSqi(fields[3]);
        }

        int halfMoveClock = Integer.parseInt(fields[4]);
        int fullMoveClock = Integer.parseInt(fields[5]);

        return new Game(Board.fromFENString(fields[0]),
                playerToMoveChar == 'w' ? Color.WHITE : Color.BLACK,
                whiteCanKingCastle, whiteCanQueenCastle,
                blackCanKingCastle, blackCanQueenCastle,
                enPassantSqi, halfMoveClock, fullMoveClock);
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
