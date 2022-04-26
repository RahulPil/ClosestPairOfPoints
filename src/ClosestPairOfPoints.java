import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClosestPairOfPoints {
    private final PointXComparator sortX;
    private final PointYComparator sortY;
    private final String fileName;
    private Point[] points;

    public ClosestPairOfPoints(String fileName) {
        sortX = new PointXComparator();
        sortY = new PointYComparator();
        this.fileName = fileName;
    }

    public void findClosestPair() {
        initializeData();
        closestPair(0, points.length - 1);
    }

    public double closestPair(int start, int end) {
        double delta;
        double distance;
        if (end - start < 3) {
            delta = Integer.MAX_VALUE;
            for (int i = start; i <= end - 1; i++) {
                for (int j = i + 1; j <= end; j++) {
                    distance = Math.sqrt(Math.pow((points[i].getX() - points[j].getX()), 2) + Math.pow((points[i].getY() - points[j].getY()), 2));
                    if (distance < delta) {
                        delta = distance;
                    }
                }
            }
        } else {
            int mid = (start + end) / 2;
            double closestLeft = closestPair(start, mid);
            double closestRight = closestPair(mid + 1, end);
            double l = points[mid].getX();
            delta = Math.min(closestLeft, closestRight);
            ArrayList<Point> deltaRange = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                if (points[i].getX() <= l + delta && points[i].getX() >= l - delta) {
                    deltaRange.add(points[i]);
                }
            }
            deltaRange.sort(sortY);
            Point from;
            Point to;
            for (int i = 0; i < deltaRange.size() - 1; i++) {
                from = deltaRange.get(i);
                //System.out.println(from.getX() + ", " + from.getY());
                for (int j = i + 1; j < Math.min(7, deltaRange.size()); j++) {
                    to = deltaRange.get(j);
                    distance = Math.sqrt(Math.pow((from.getX() - to.getX()), 2) + Math.pow((from.getY() - to.getY()), 2));
                    //System.out.println("(" + from.getX() + ", " + from.getY() + ") " + distance + " (" + to.getX() + ", " + to.getY() + ")");
                    if (distance < delta) {
                        //System.out.println("New closest!!");
                        delta = distance;
                    }
                }
            }
        }
        System.out.printf("D[" + start + "," + end + "]: " + "%.4f %n", delta);
        return delta;
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
