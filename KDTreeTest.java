package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    private static Random randomGenerator = new Random(500);

    private static Point randomPoint() {
        double x = randomGenerator.nextDouble();
        double y = randomGenerator.nextDouble();
        return new Point(x, y);
    }

    private static ArrayList<Point> randomPoints(int numPoints) {
        ArrayList<Point> arrayList = new ArrayList<>();
        for (int i = 0; i < numPoints; i++) {
            arrayList.add(randomPoint());
        }
        return arrayList;
    }

    @Test
    public void naiveTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2

        assertEquals(3.3, ret.getX(), 0);
        assertEquals(4.4, ret.getY(), 0);
    }

    @Test
    public void kdTreeBasicTest() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(-2.9, 6.0);
        Point p3 = new Point(1.0, 4.4);
        Point p4 = new Point(1.2, 4.0);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4));
    }

    // @source https://www.youtube.com/watch?v=lp80raQvE5c
    // watched before writing test
    @Test
    public void randomNearest() {
        ArrayList<Point> points = randomPoints(10000);
        ArrayList<Point> queries = randomPoints(3000);

        NaivePointSet naivePointSet = new NaivePointSet(points);
        KDTree kdTree = new KDTree(points);

        for (Point p : queries) {
            Point expected = naivePointSet.nearest(p.getX(), p.getY());
            Point actual = kdTree.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }

    @Test
    public void speedNearestKDTree() {
        System.out.println("Starting nearest() speed test...");
        ArrayList<Point> points = randomPoints(100000);
        ArrayList<Point> queries = randomPoints(50000);
        KDTree kdTree = new KDTree(points);
        NaivePointSet naivePointSet = new NaivePointSet(points);

        Stopwatch sw = new Stopwatch();
        for (Point p : queries) {
            kdTree.nearest(p.getX(), p.getY());
        }
        System.out.println("KDTree: " + sw.elapsedTime());
        sw = new Stopwatch();
        for (Point p : queries) {
            naivePointSet.nearest(p.getX(), p.getY());
        }
        System.out.println("NaivePointSet: " + sw.elapsedTime());
    }
}
