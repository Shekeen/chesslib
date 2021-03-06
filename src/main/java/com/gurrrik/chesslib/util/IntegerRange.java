package com.gurrrik.chesslib.util;

import java.util.Iterator;

class IntegerRangeIterator implements Iterator<Integer> {
    private int start;
    private int stop;
    private int step;

    public IntegerRangeIterator(int start, int stop, int step) {
        this.start = start;
        this.stop = stop;
        this.step = step;
    }

    @Override
    public boolean hasNext() {
        if (step > 0)
            return start <= stop;
        else
            return step < 0 && start >= stop;
    }

    @Override
    public Integer next() {
        int result = start;
        start += step;
        return result;
    }
}

public class IntegerRange implements Iterable<Integer> {
    private int start;
    private int stop;
    private int step;

    private IntegerRange(int start, int stop, int step) {
        this.start = start;
        this.stop = stop;
        this.step = step;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntegerRangeIterator(start, stop, step);
    }

    public static IntegerRange interval(int start, int stop) {
        return interval(start, stop, 1);
    }

    public static IntegerRange interval(int start, int stop, int step) {
        int absStep = Math.abs(step);
        if (start <= stop)
            return new IntegerRange(start + absStep, stop - 1, absStep);
        else
            return new IntegerRange(start - absStep, stop + 1, -absStep);
    }

    public static IntegerRange halfInterval(int start, int stop) {
        return halfInterval(start, stop, 1);
    }

    public static IntegerRange halfInterval(int start, int stop, int step) {
        int absStep = Math.abs(step);
        if (start <= stop)
            return new IntegerRange(start, stop - 1, absStep);
        else
            return new IntegerRange(start, stop + 1, -absStep);
    }

    public static IntegerRange range(int start, int stop) {
        return range(start, stop, 1);
    }

    public static IntegerRange range(int start, int stop, int step) {
        int absStep = Math.abs(step);
        if (start <= stop)
            return new IntegerRange(start, stop, absStep);
        else
            return new IntegerRange(start, stop, -absStep);
    }
}
