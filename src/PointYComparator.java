import java.util.Comparator;

/**
 * Class to overload comparator function
 * Functionality includes comparing Y coordinates in order to sort in descending order
 */
public class PointYComparator implements Comparator<Point> {

    /**
     * compare
     * pre: two points are passed into the function
     * post: returns 1 if Y coordinate of point1 is less than Y coordinate of point 2,
     *       otherwise returns -1 to sort Y coordinates into descending order
     */
    @Override
    public int compare(Point o1, Point o2) {
        if (o1.getY() > o2.getY()) {
            return -1;
        } else if (o1.getY() < o2.getY()) {
            return 1;
        }
        return 0;
    }
}
