package bearmaps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void exceptionsTest() {
        exception.expect(NoSuchElementException.class);
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.changePriority("stringNotInHeap", 3);
        heap.removeSmallest();
        heap.getSmallest();

        exception.expect(IllegalArgumentException.class);
        heap.add("str1", 3);
        heap.add("str1", 2);
    }
    @Test
    public void basicTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("str1", 1);
        heap.add("str2", 2);
        assertEquals(2, heap.size());
        assertTrue(heap.contains("str1"));
        String removedItem = heap.removeSmallest();
        assertEquals("str1", removedItem);
        assertEquals(1, heap.size());
        assertFalse(heap.contains(removedItem));
    }

    @Test
    public void resizeTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("str1", 1);
        heap.add("str2", 2);
        heap.add("str3", 3);
        heap.add("str4", 4);
        heap.add("str5", 5);
        heap.add("str6", 6);
        assertEquals("str1", heap.removeSmallest());
        heap.changePriority("str2", 8);
        assertEquals("str3", heap.removeSmallest());
    }

    @Test
    public void advancedChangePriorityTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10; i++) {
            heap.add("hi" + i, i);
        }
        while (heap.size() > 0) {
            heap.removeSmallest();
        }
        heap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10000; i++) {
            heap.add("hi" + i, i);
        }
        for (int i = 0; i < 5000; i++) {
            heap.changePriority("hi" + i, 100000 + i);
        }
        for (int i = 0; i < 5000; i++) {
            assertNotEquals("hi" + 0, heap.removeSmallest());
        }
        assertEquals("hi0", heap.removeSmallest());
        assertEquals(10000 - 5001, heap.size());
    }

    @Test
    public void advancedGetAndRemoveAndSizeTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100; i++) {
            heap.add("hi" + i, i);
        }
        assertEquals(100, heap.size());
        for (int i = 0; i < 50; i++) {
            String x = heap.getSmallest();
            String y = heap.removeSmallest();
            assertEquals(x, y);
            assertEquals("hi" + i, y);
        }
        assertEquals(50, heap.size());
        for (int i = 0; i < 50; i++) {
            heap.add("hi" + i, i);
        }
        assertEquals(100, heap.size());
        for (int i = 0; i < 100; i++) {
            assertEquals("hi" + i, heap.removeSmallest());
        }
        assertEquals(0, heap.size());
    }

    @Test
    public void advancedContainsTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100; i++) {
            heap.add("hi" + i, i);
        }
        for (int i = 0; i < 100; i++) {
            assertTrue(heap.contains("hi" + i));
        }
        for (int i = 0; i < 50; i++) {
            String x = heap.getSmallest();
            assertTrue(heap.contains(x));
            String y = heap.removeSmallest();
            assertFalse(heap.contains(x));
            assertEquals(x, y);
            assertEquals("hi" + i, y);
        }
    }
}
