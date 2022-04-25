import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClosestPairOfPoints {
    private PointXComparator sortX;
    private PointYComparator sortY;
    private String fileName;
    private Point[] points;

    public ClosestPairOfPoints(String fileName) {
        sortX = new PointXComparator();
        sortY = new PointYComparator();
        this.fileName = fileName;
    }

    public void findClosestPair() {
        initializeData();
//        for (int i = 0; i < points.length; i++) {
//            System.out.println(points[i].getX() + ", " + points[i].getY());
//        }
        closestPair(0, points.length - 1);
    }

    public double closestPair(int start, int end) {
        double closest = Integer.MAX_VALUE;
        double distance = 0;
        if (end - start < 3) {
            for (int i = start; i <= end - 1; i++) {
                for (int j = i + 1; j <= end; j++) {
                    distance = Math.sqrt(Math.pow((points[i].getX() - points[j].getX()), 2) + Math.pow((points[i].getY() - points[j].getY()), 2));
                    if (distance < closest) {
                        closest = distance;
                    }
                }
            }
        } else {
            int mid = (start + end) / 2;
            double closestLeft = closestPair(start, mid);
            double closestRight = closestPair(mid + 1, end);
            double l = points[mid].getX();
            double delta = Math.min(closestLeft, closestRight);
            ArrayList<Point> deltaRange = new ArrayList<Point>();
            for (int i = start; i <= end; i++) {
                if (points[i].getX() <= l + delta && points[i].getX() >= l - delta) {
                    deltaRange.add(points[i]);
                }
            }

            Collections.sort(deltaRange, sortY);
//            for (Point p : deltaRange) {
//                System.out.println(p.getX() + ", " + p.getY());
//            }
            Point from;
            Point to;
            for (int i = 0; i < deltaRange.size() - 1; i++) {
                from = deltaRange.get(i);
                //System.out.println(from.getX() + ", " + from.getY());
                for (int j = i + 1; j < Math.min(7, deltaRange.size()); j++) {
                    to = deltaRange.get(j);
                    distance = Math.sqrt(Math.pow((from.getX() - to.getX()), 2) + Math.pow((from.getY() - to.getY()), 2));
                    if (distance < closest) {
                        closest = distance;
                    }
                }
            }
        }
        System.out.println("D[" + start + "," + end + "]: " + closest);
        return closest;
    }

    public void initializeData() {
        File file = new File(fileName);
        try {
            Scanner in = new Scanner(file);
            int n = in.nextInt();
            points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(in.nextDouble(), in.nextDouble());
            }
            Arrays.sort(points, sortX);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
}
