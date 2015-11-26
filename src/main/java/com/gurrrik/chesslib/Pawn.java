package com.gurrrik.chesslib;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    @Override
    public boolean isValidMove(int sqiFrom, int sqiTo) {
        return sqiFrom != sqiTo &&
                Board.sqiToCol(sqiFrom) == Board.sqiToCol(sqiTo) &&
                Board.sqiRowDistance(sqiFrom, sqiTo) < 3;
    }

    @Override
    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return Board.sqiColDistance(sqiFrom, sqiTo) == 1 &&
                Board.sqiRowDistance(sqiFrom, sqiTo) == 1;
    }

    @Override
    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        if (isValidMove(sqiFrom, sqiTo)) {
            if (Board.sqiRowDistance(sqiFrom, sqiTo) == 2) {
                List<Integer> result = new ArrayList<>();
                result.add((sqiFrom + sqiTo) / 2);
                return result;
            } else {
                return new ArrayList<>();
            }
        } else if (isValidCaptureMove(sqiFrom, sqiTo)) {
            return new ArrayList<>();
        } else {
            return null;
        }
    }
}
