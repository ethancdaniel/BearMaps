package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private Point[] pointsSet;
    public NaivePointSet(List<Point> points) {
        pointsSet = new Point[points.size()];
        for (int i = 0; i < points.size(); i++) {
            pointsSet[i] = points.get(i);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point max = pointsSet[0];
        Point goal = new Point(x, y);
        for (Point p : pointsSet) {
            double closestDist = Point.distance(goal, max);
            double iterDist = Point.distance(goal, p);
            if (iterDist < closestDist) {
                max = p;
            }
        }
        return max;
    }
}
