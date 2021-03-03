package com.pardxa.tqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testExample1() throws InterruptedException {
        Tqueue<Integer> queue = new Tqueue<>(7, 2);
        queue.enqueue(Integer.valueOf(8), 8);
        queue.enqueue(Integer.valueOf(2), 2);
        queue.enqueue(Integer.valueOf(6), 6);
        queue.enqueue(Integer.valueOf(5), 5);
        queue.enqueue(Integer.valueOf(2), 2);
        queue.enqueue(Integer.valueOf(5), 5);
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        queue.enqueue(Integer.valueOf(2), 2);
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());
    }

    @Test
    void testExample2() throws InterruptedException {
        Tqueue<Integer> queue = new Tqueue<>(7, 2);
        queue.enqueue(Integer.valueOf(8), 8);
        queue.enqueue(Integer.valueOf(2), 2);
        queue.enqueue(Integer.valueOf(6), 6);
        queue.enqueue(Integer.valueOf(5), 5);
        queue.enqueue(Integer.valueOf(5), 5);
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        queue.enqueue(Integer.valueOf(2), 2);
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(Integer.valueOf(8), queue.dequeue());
    }

    @Test
    void testExample3() throws InterruptedException {
        Tqueue<Integer> queue = new Tqueue<>(35, 2);
        queue.enqueue(Integer.valueOf(5), 5);
        queue.enqueue(Integer.valueOf(1), 1);
        queue.enqueue(Integer.valueOf(4), 4);
        queue.enqueue(Integer.valueOf(3), 3);

        queue.enqueue(Integer.valueOf(1), 1);
        queue.enqueue(Integer.valueOf(5), 5);
        queue.enqueue(Integer.valueOf(3), 3);
        queue.enqueue(Integer.valueOf(4), 4);

        queue.enqueue(Integer.valueOf(3), 3);
        queue.enqueue(Integer.valueOf(5), 5);
        queue.enqueue(Integer.valueOf(1), 1);
        queue.enqueue(Integer.valueOf(4), 4);

        queue.enqueue(Integer.valueOf(4), 4);
        queue.enqueue(Integer.valueOf(7), 7);
        queue.enqueue(Integer.valueOf(3), 3);
        queue.enqueue(Integer.valueOf(1), 1);

        queue.enqueue(Integer.valueOf(4), 4);
        queue.enqueue(Integer.valueOf(9), 9);
        queue.enqueue(Integer.valueOf(1), 1);
        queue.enqueue(Integer.valueOf(3), 3);

        queue.enqueue(Integer.valueOf(5), 5);
        queue.enqueue(Integer.valueOf(3), 3);
        queue.enqueue(Integer.valueOf(5), 5);
        queue.enqueue(Integer.valueOf(1), 1);

        queue.enqueue(Integer.valueOf(4), 4);
        queue.enqueue(Integer.valueOf(3), 3);
        queue.enqueue(Integer.valueOf(1), 1);
        queue.enqueue(Integer.valueOf(7), 7);

        queue.enqueue(Integer.valueOf(3), 3);
        queue.enqueue(Integer.valueOf(1), 1);
        queue.enqueue(Integer.valueOf(1), 1);
        queue.enqueue(Integer.valueOf(3), 3);

        queue.enqueue(Integer.valueOf(4), 4);
        queue.enqueue(Integer.valueOf(1), 1);
        queue.enqueue(Integer.valueOf(1), 1);
        //
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());

        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());

        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());

        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());

        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());

        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());

        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(7), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());

        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());

        assertEquals(Integer.valueOf(7), queue.dequeue());
        assertEquals(Integer.valueOf(9), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
    }
    @Test
    void testExample4() throws InterruptedException {
        Tqueue<Integer> queue = new Tqueue<>(8, 2);
        queue.enqueue(Integer.valueOf(82), 8);
        queue.enqueue(Integer.valueOf(81), 8);
        queue.enqueue(Integer.valueOf(83), 8);
        queue.enqueue(Integer.valueOf(84), 8);
        queue.enqueue(Integer.valueOf(85), 8);
        queue.enqueue(Integer.valueOf(6), 6);
        queue.enqueue(Integer.valueOf(5), 5);
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(Integer.valueOf(82), queue.dequeue());
        assertEquals(Integer.valueOf(81), queue.dequeue());
        assertEquals(Integer.valueOf(83), queue.dequeue());
        assertEquals(Integer.valueOf(84), queue.dequeue());
        assertEquals(Integer.valueOf(85), queue.dequeue());
    }
}
