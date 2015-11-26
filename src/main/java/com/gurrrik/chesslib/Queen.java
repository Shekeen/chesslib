package com.gurrrik.chesslib;

import java.util.List;

public class Queen implements Piece {
    @Override
    public boolean isValidMove(int sqiFrom, int sqiTo) {
        return false;
    }

    @Override
    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return false;
    }

    @Override
    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        return null;
    }
}
