package com.gurrrik.chesslib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Queen implements Piece {
    private Method rookIsValidMove;
    private Method rookGetTransitionalSquaresForMove;
    private Method bishopIsValidMove;
    private Method bishopGetTransitionalSquaresForMove;

    @Override
    public boolean isValidMove(int sqiFrom, int sqiTo) {
        try {
            boolean resultRook = (boolean)rookIsValidMove.invoke(this, sqiFrom, sqiTo);
            boolean resultBishop = (boolean)bishopIsValidMove.invoke(this, sqiFrom, sqiTo);
            return resultRook || resultBishop;
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        }
    }

    @Override
    public boolean isValidCaptureMove(int sqiFrom, int sqiTo) {
        return isValidMove(sqiFrom, sqiTo);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> getTransitionalSquaresForMove(int sqiFrom, int sqiTo) {
        try {
            boolean resultRook = (boolean)rookIsValidMove.invoke(this, sqiFrom, sqiTo);
            boolean resultBishop = (boolean)bishopIsValidMove.invoke(this, sqiFrom, sqiTo);
            if (resultRook)
                return (List<Integer>)rookGetTransitionalSquaresForMove.invoke(this, sqiFrom, sqiTo);
            else if (resultBishop)
                return (List<Integer>)bishopGetTransitionalSquaresForMove.invoke(this, sqiFrom, sqiTo);
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }
        return null;
    }

    public Queen() throws NoSuchMethodException {
        rookIsValidMove =
                Rook.class.getMethod("isValidMove", int.class, int.class);
        rookGetTransitionalSquaresForMove =
                Rook.class.getMethod("getTransitionalSquaresForMove", int.class, int.class);
        bishopIsValidMove =
                Bishop.class.getMethod("isValidMove", int.class, int.class);
        bishopGetTransitionalSquaresForMove =
                Bishop.class.getMethod("getTransitionalSquaresForMove", int.class, int.class);
    }
}
