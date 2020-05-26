package bearmaps;

import java.util.Comparator;
import java.util.List;

public class KDTree {
    private Node root;

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = addNode(p, root, false);
        }
    }

    // @source BSTMap put()
    private Node addNode(Point point, Node node, boolean horizontal) {
        if (node == null) {
            Node temp = new Node(point);
            temp.horizontal = horizontal;
            return temp;
        }
        int cmp = node.compare(node, new Node(point));
        if (cmp > 0) {
            node.leftChild = addNode(point, node.leftChild, !node.horizontal);
        } else {
            node.rightChild = addNode(point, node.rightChild, !node.horizontal);
        }
        return node;
    }

    public Point nearest(double x, double y) {
        return nearestHelper(root, new Node(new Point(x, y)), root).point;
    }

    private Node nearestHelper(Node n, Node goal, Node best) {
        if (n == null) {
            return best;
        } else if (Point.distance(n.point, goal.point) < Point.distance(best.point, goal.point)) {
            best = n;
        }
        Node goodSide, badSide;
        if (n.compare(n, goal) > 0) {
            goodSide = n.leftChild;
            badSide = n.rightChild;
        } else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        best = nearestHelper(goodSide, goal, best);
        if (Point.distance(best.point, goal.point)
            > Point.distance(bestPointInSide(n, goal), goal.point)) {
            best = nearestHelper(badSide, goal, best);
        }
        return best;
    }

    private Point bestPointInSide(Node side, Node goal) {
        if (side.horizontal) {
            double dist = goal.point.getY() - side.point.getY();
            return new Point(goal.point.getX(), goal.point.getY() - dist);
        } else {
            double dist = goal.point.getX() - side.point.getX();
            return new Point(goal.point.getX() - dist, goal.point.getY());
        }
    }

    private class Node implements Comparator<Node> {
        private Point point;
        private boolean horizontal;
        private Node leftChild, rightChild;

        Node(Point point) {
            this.point = point;
        }

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.horizontal) {
                return Double.compare(o1.point.getY(), o2.point.getY());
            } else {
                return Double.compare(o1.point.getX(), o2.point.getX());
            }
        }
    }
}
