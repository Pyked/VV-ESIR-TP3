package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    @Test
    void testBinaryHeapConstructor() {
        // Block: Valid comparator
        BinaryHeap<Integer> heap1 = new BinaryHeap<>(Comparator.naturalOrder());
        assertNotNull(heap1);

        // Block: Null comparator
        assertThrows(IllegalArgumentException.class, () -> new BinaryHeap<>(null));
    }

    @Test
    void testPop() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());

        // Block: Empty heap
        assertThrows(NoSuchElementException.class, heap::pop);

        // Block: Heap with one element
        heap.push(5);
        assertEquals(5, heap.pop());

        // Block: Heap with multiple elements
        heap.push(10);
        heap.push(5);
        heap.push(15);
        assertEquals(5, heap.pop());
    }

    @Test
    void testPeek() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());

        // Block: Empty heap
        assertThrows(NoSuchElementException.class, heap::peek);

        // Block: Heap with one element
        heap.push(5);
        assertEquals(5, heap.peek());

        // Block: Heap with multiple elements
        heap.push(10);
        heap.push(5);
        heap.push(15);
        assertEquals(5, heap.peek());
    }

    @Test
    void testPush() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());

        // Block: Null element
        assertThrows(NullPointerException.class, () -> heap.push(null));

        // Block: First element in the heap
        heap.push(5);
        assertEquals(5, heap.peek());

        // Block: Not the first element in the heap
        heap.push(3);
        assertEquals(3, heap.peek());
        heap.push(7);
        assertEquals(3, heap.peek());
    }

    @Test
    void testCount() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());

        // Block: Empty heap
        assertEquals(0, heap.count());

        // Block: Heap with one element
        heap.push(5);
        assertEquals(1, heap.count());

        // Block: Heap with multiple elements
        heap.push(10);
        heap.push(5);
        heap.push(15);
        assertEquals(4, heap.count());
    }
}
