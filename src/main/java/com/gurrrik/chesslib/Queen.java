package com.gurrrik.chesslib;

import java.util.List;

public class Queen implements Piece {
    @Override
    public boolean isValidMove(int sqiFrom, int sqiTo) {
        return RookMoveChecker.isValidMove(sqiFrom, sqiTo)
                || BishopMoveChecker.isValidMove(sqiFrom, sqiTo);
    }

    @Override
    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return isValidMove(sqiFrom, sqiTo);
    }

    @Override
    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        if (RookMoveChecker.isValidMove(sqiFrom, sqiTo))
            return RookMoveChecker.getTransitionalSquaresForMove(sqiFrom, sqiTo);
        else if (BishopMoveChecker.isValidMove(sqiFrom, sqiTo))
            return BishopMoveChecker.getTransitionalSquaresForMove(sqiFrom, sqiTo);
        else
            return null;
    }

    @Override
    public char getSANSymbol() {
        return 'q';
    }
}
