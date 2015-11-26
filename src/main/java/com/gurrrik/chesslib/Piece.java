package com.gurrrik.chesslib;

import java.util.List;

public interface Piece {
    boolean isValidMove(int sqiFrom, int sqiTo);
    boolean isValidCaptureMove(int sqiFrom, int sqiTo);
    List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo);
}
