import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class to represent Closest Pair Of Points algorithm
 * The ClosestPairOfPoints object is represented by a PointXComparator,
 * PointYComparator, string name of a file, and stores an array of points
 * Functionality includes allocating memory for points through file input and
 * finds the closest pair of points given a partition of points
 */
public class ClosestPairOfPoints {
    private final PointXComparator sortX;
    private final PointYComparator sortY;
    private final String fileName;
    private Point[] points;

    /**
     * constructor
     * pre: none
     * post: instance variables are initialized
     */
    public ClosestPairOfPoints(String fileName) {
        sortX = new PointXComparator();
        sortY = new PointYComparator();
        this.fileName = fileName;
    }

    /**
     * findClosestPair
     * pre: none
     * post: data in points array is initialized and closest pairs of points are found
     */
    public void findClosestPair() {
        initializeData();
        closestPair(0, points.length - 1);
    }

    /**
     * closestPair
     * pre: points has been allocated memory and
     *      all points have been added to the points array
     * post: returns the distance of the closest pair of points
     *       for the partition of points sent as parameters into the function
     */
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

    /**
     * initializeData
     * pre: file holding points data is available in directory, array holding points is declared
     * post: points array is initialized
     */
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
