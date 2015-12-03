package com.gurrrik.chesslib;

import com.gurrrik.chesslib.util.IntegerRange;

import java.util.ArrayList;
import java.util.List;

class BishopMoveChecker {
    public static boolean isValidMove(int sqiFrom, int sqiTo) {
        return sqiFrom != sqiTo &&
                Board.sqiRowDistance(sqiFrom, sqiTo) == Board.sqiColDistance(sqiFrom, sqiTo);
    }

    public static boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return isValidMove(sqiFrom, sqiTo);
    }

    public static List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        if (isValidMove(sqiFrom, sqiTo)) {
            List<Integer> result = new ArrayList<>();
            int rowSign = Board.sqiToCol(sqiTo) - Board.sqiToCol(sqiFrom) > 0 ? 1 : -1;
            int colSign = Board.sqiToRow(sqiTo) - Board.sqiToRow(sqiFrom) > 0 ? 1 : -1;
            switch (rowSign * colSign) {
                case 1:
                    for (int x: IntegerRange.interval(sqiFrom, sqiTo, 9))
                        result.add(x);
                    break;
                case -1:
                    for (int x: IntegerRange.interval(sqiFrom, sqiTo, 7))
                        result.add(x);
                    break;
                default:
                    return null;
            }
            return result;
        } else {
            return null;
        }
    }
}

public class Bishop implements Piece {
    @Override
    public boolean isValidMove(int sqiFrom, int sqiTo) {
        return BishopMoveChecker.isValidMove(sqiFrom, sqiTo);
    }

    @Override
    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return BishopMoveChecker.isValidCaptureMove(sqiFrom, sqiTo);
    }

    @Override
    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        return BishopMoveChecker.getTransitionalSquaresForMove(sqiFrom, sqiTo);
    }
}
