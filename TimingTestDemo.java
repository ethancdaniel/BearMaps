package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug. Demonstrates how you can use either
 * System.currentTimeMillis or the Princeton Stopwatch
 * class to time code.
 */
public class TimingTestDemo {
    public static void main(String[] args) {
        speedTest();
    }

    public static void speedTest() {
        System.out.println("Starting add() test...");
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naiveMinPQ = new NaiveMinPQ<>();
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 1000000; i += 1) {
            naiveMinPQ.add("hi" + i, i);
        }
        System.out.println("Naive time elapsed: " + sw.elapsedTime() +  " seconds.");
        sw = new Stopwatch();
        for (int i = 0; i < 100000; i += 1) {
            arrayHeapMinPQ.add("hi" + i, i);
        }
        System.out.println("Beast time elapsed: " + sw.elapsedTime() +  " seconds.");
        System.out.println();
        System.out.println("Starting contains() test...");
        sw = new Stopwatch();
        for (int i = 0; i < 10000; i += 1) {
            naiveMinPQ.contains("hi" + i);
        }
        System.out.println("Naive time elapsed: " + sw.elapsedTime() +  " seconds.");
        sw = new Stopwatch();
        for (int i = 0; i < 10000; i += 1) {
            arrayHeapMinPQ.contains("hi" + i);
        }
        System.out.println("Beast time elapsed: " + sw.elapsedTime() +  " seconds.");
        System.out.println();
        System.out.println("Starting changePriority() test...");
        sw = new Stopwatch();
        for (int i = 0; i < 10000; i += 1) {
            naiveMinPQ.changePriority("hi" + i, 1000000000);
        }
        System.out.println("Naive time elapsed: " + sw.elapsedTime() +  " seconds.");
        sw = new Stopwatch();
        for (int i = 0; i < 10000; i += 1) {
            arrayHeapMinPQ.changePriority("hi" + i, 1000000000);
        }
        System.out.println("Beast time elapsed: " + sw.elapsedTime() +  " seconds.");
        System.out.println();
        System.out.println("Starting getSmallest() test...");
        sw = new Stopwatch();
        for (int i = 0; i < 1000; i += 1) {
            naiveMinPQ.getSmallest();
        }
        System.out.println("Naive time elapsed: " + sw.elapsedTime() +  " seconds.");
        sw = new Stopwatch();
        for (int i = 0; i < 1000; i += 1) {
            arrayHeapMinPQ.getSmallest();
        }
        System.out.println("Beast time elapsed: " + sw.elapsedTime() +  " seconds.");
        System.out.println();
        System.out.println("Starting removeSmallest() test...");
        sw = new Stopwatch();
        for (int i = 0; i < 1000; i += 1) {
            naiveMinPQ.removeSmallest();
        }
        System.out.println("Naive time elapsed: " + sw.elapsedTime() +  " seconds.");
        sw = new Stopwatch();
        for (int i = 0; i < 1000; i += 1) {
            arrayHeapMinPQ.removeSmallest();
        }
        System.out.println("Beast time elapsed: " + sw.elapsedTime() +  " seconds.");
        System.out.println();
        System.out.println("Starting size() test...");
        sw = new Stopwatch();
        for (int i = 0; i < 1000000; i += 1) {
            naiveMinPQ.size();
        }
        System.out.println("Naive time elapsed: " + sw.elapsedTime() +  " seconds.");
        sw = new Stopwatch();
        for (int i = 0; i < 1000000; i += 1) {
            arrayHeapMinPQ.size();
        }
        System.out.println("Beast time elapsed: " + sw.elapsedTime() +  " seconds.");
    }
}
