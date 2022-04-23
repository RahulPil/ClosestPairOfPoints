import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClosestPairOfPoints {
    private String fileName;
    private Point[] points;

    public ClosestPairOfPoints(String fileName) {
        this.fileName = fileName;
    }

    public void findClosestPair() {
        closestPair(0, points.length);
    }

    public int closestPair(int start, int end) {
        int mid = (start + end) / 2;
        double closestLeft = closestPair(start, mid);
        double closestRight = closestPair(mid + 1, end);
        double l = (points[mid].getX() + points[mid + 1].getX()) / 2;
        double delta = Math.min(closestLeft, closestRight);
        ArrayList<Point> deltaRange = new ArrayList<Point>();
        for (int i = start; i < end; i++) {
            if (points[i].getX() <= l + delta && points[i].getX() >= l - delta) {
                deltaRange.add(points[i]);
            }
        }
        Collections.sort(deltaRange);
        for (int i = 0; i < deltaRange.size(); i++) {
            for (int j = 0; j < 7; j++) {

            }
        }
    }

    public void initializeData() {
        File file = new File("src\\" + fileName);
        try {
            Scanner in = new Scanner(file);
            int n = in.nextInt();
            points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(in.nextDouble(), in.nextDouble());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
}
