package com.gurrrik.chesslib.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IntegerRangeTest {
    @Test
    public void testInterval() throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int x: IntegerRange.interval(0, 11, 3))
            list.add(x);
        assertEquals(3, list.size());
        assertEquals(3, (long)list.get(0));
        assertEquals(6, (long)list.get(1));
        assertEquals(9, (long)list.get(2));
    }

    @Test
    public void testHalfInterval() throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int x: IntegerRange.halfInterval(10, -10, 5))
            list.add(x);
        assertEquals(4, list.size());
        assertEquals(10, (long)list.get(0));
        assertEquals(5, (long)list.get(1));
        assertEquals(0, (long)list.get(2));
        assertEquals(-5, (long)list.get(3));
    }

    @Test
    public void testRange() throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int x: IntegerRange.range(-1, 1))
            list.add(x);
        assertEquals(3, list.size());
        assertEquals(-1, (long)list.get(0));
        assertEquals(0, (long)list.get(1));
        assertEquals(1, (long)list.get(2));
    }
}