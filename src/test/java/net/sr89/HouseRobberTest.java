package net.sr89;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseRobberTest {

    final HouseRobber robber = new HouseRobber();

    @Test
    void test1() {
        assertEquals(12, robber.rob(new int[]{2, 7, 9, 3, 1}));
    }

    @Test
    void test2() {
        assertEquals(4, robber.rob(new int[]{1, 2, 3, 1}));
    }
}