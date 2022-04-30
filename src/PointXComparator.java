import java.util.Comparator;

/**
 * Class to overload comparator function
 * Functionality includes comparing X coordinates in order to sort in ascending order
 */
public class PointXComparator implements Comparator<Point> {

    /**
     * compare
     * pre: two points are passed into the function
     * post: uses compare function and returns -1 if X coordinate of point1 is
     *       less than X coordinate of point 2, otherwise returns 1 to sort X coordinates into descending order
     */
    @Override
    public int compare(Point o1, Point o2) {
        return Double.compare(o1.getX(), o2.getX());
    }
}
