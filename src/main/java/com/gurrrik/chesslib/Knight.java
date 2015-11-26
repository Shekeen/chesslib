package com.gurrrik.chesslib;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece {
    @Override
    public boolean isValidMove(int sqiFrom, int sqiTo) {
        return Board.sqiRowDistance(sqiFrom, sqiTo) * Board.sqiColDistance(sqiFrom, sqiTo) == 2;
    }

    @Override
    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return isValidMove(sqiFrom, sqiTo);
    }

    @Override
    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        if (isValidMove(sqiFrom, sqiTo)) {
            return new ArrayList<>();
        } else {
            return null;
        }
    }
}
