import java.util.Comparator;
/**
 * Class to represent a point
 * A point is represented by two doubles, x and y
 * Functionality includes two getter functions to return X and Y coordinates
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor
     * pre: none
     * post: instance variables are initialized
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * accessor for x coordinate of point
     * pre: x variable is initialized
     * post: return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * accessor for y coordinate of point
     * pre: y variable is initialized
     * post: return y coordinate
     */
    public double getY() {
        return y;
    }
}

