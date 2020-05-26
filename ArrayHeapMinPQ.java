package bearmaps;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private static final int INITIAL_ARRAY_SIZE = 5;
    private Item[] minHeapArray;
    private HashMap<T, Item> hashMap;
    private int size;

    public ArrayHeapMinPQ() {
        minHeapArray = (Item[]) new Object[INITIAL_ARRAY_SIZE];
        hashMap = new HashMap<>();
        size = 0;
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        if (size + 1 == minHeapArray.length) {
            resize(minHeapArray.length * 2);
        }
        size++;
        Item added = new Item(item, priority, size);
        minHeapArray[size] = added;
        hashMap.put(item, added);
        swim(size);
    }

    @Override
    public boolean contains(T item) {
        if (item == null) {
            return false;
        }
        return hashMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (minHeapArray[1] == null) {
            throw new NoSuchElementException();
        }
        return minHeapArray[1].item;
    }

    @Override
    public T removeSmallest() {
        if (minHeapArray[1] == null) {
            throw new NoSuchElementException();
        }
        if ((double) size / minHeapArray.length <= 0.25) {
            resize(minHeapArray.length / 2);
        }
        T removedItem = minHeapArray[1].item;
        minHeapArray[1] = minHeapArray[size];
        minHeapArray[1].indexInHeap = 1;
        minHeapArray[size] = null;

        size--;
        sink(1);
        hashMap.remove(removedItem);
        return removedItem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int indexOfItem = findIndexOfItem(item);
        if (minHeapArray[indexOfItem].priority < priority) {
            minHeapArray[indexOfItem].priority = priority;
            sink(indexOfItem);
        } else {
            minHeapArray[indexOfItem].priority = priority;
            swim(indexOfItem);
        }
    }

    private int findIndexOfItem(T item) {
        return hashMap.get(item).indexInHeap;
    }

    private void sink(int i) {
        if (i * 2 + 1 > size) {
            if (i * 2 == size && minHeapArray[i * 2].priority < minHeapArray[i].priority) {
                swap(i, i * 2);
            }
            return;
        }
        double topPriority = minHeapArray[i].priority;
        double leftChildPriority = minHeapArray[i * 2].priority;
        double rightChildPriority = minHeapArray[i * 2 + 1].priority;
        double minPriority = Math.min(rightChildPriority,
                Math.min(topPriority, leftChildPriority));
        if (minPriority == rightChildPriority) {
            swap(i, i * 2 + 1);
            sink(i * 2 + 1);
        } else if (minPriority == leftChildPriority) {
            swap(i, i * 2);
            sink(i * 2);
        }
    }

    private void swim(int i) {
        if (i != 1 && minHeapArray[i].priority < minHeapArray[i / 2].priority) {
            swap(i, i / 2);
            swim(i / 2);
        }
    }

    private void swap(int i, int k) {
        Item item = minHeapArray[i];
        minHeapArray[i].indexInHeap = k;
        minHeapArray[k].indexInHeap = i;
        minHeapArray[i] = minHeapArray[k];
        minHeapArray[k] = item;

    }

    private void resize(int finalSize) {
        Item[] temp = (Item[]) new Object[finalSize];
        for (int i = 1; i <= size; i++) {
            temp[i] = minHeapArray[i];
        }
        minHeapArray = temp;
    }

    private class Item {
        private double priority;
        private T item;
        private int indexInHeap;

        Item(T item, double priority, int indexInHeap) {
            this.item = item;
            this.priority = priority;
            this.indexInHeap = indexInHeap;
        }
    }
}
