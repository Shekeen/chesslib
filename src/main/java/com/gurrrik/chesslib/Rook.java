package com.gurrrik.chesslib;

import com.gurrrik.chesslib.util.IntegerRange;

import java.util.ArrayList;
import java.util.List;

class RookMoveChecker {
    public static boolean isValidMove(int sqiFrom, int sqiTo) {
        return sqiFrom != sqiTo &&
                (Board.sqiToRow(sqiFrom) == Board.sqiToRow(sqiTo) ||
                        Board.sqiToCol(sqiFrom) == Board.sqiToCol(sqiTo));
    }

    public static boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return isValidMove(sqiFrom, sqiTo);
    }

    public static List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        if (isValidMove(sqiFrom, sqiTo)) {
            List<Integer> result = new ArrayList<>();
            if (Board.sqiToRow(sqiFrom) == Board.sqiToRow(sqiTo)) {
                for (int x: IntegerRange.interval(sqiFrom, sqiTo, 1))
                    result.add(x);
            } else {
                for (int x: IntegerRange.interval(sqiFrom, sqiTo, 8))
                    result.add(x);
            }
            return result;
        } else {
            return null;
        }
    }
}

public class Rook implements Piece {
    @Override
    public boolean isValidMove(int sqiFrom, int sqiTo) {
        return RookMoveChecker.isValidMove(sqiFrom, sqiTo);
    }

    @Override
    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return RookMoveChecker.isValidCaptureMove(sqiFrom, sqiTo);
    }

    @Override
    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        return RookMoveChecker.getTransitionalSquaresForMove(sqiFrom, sqiTo);
    }
}
