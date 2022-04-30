import java.io.File;

/**
 * Class to run algorithm
 * Functionality includes creating a new ClosestPairOfPoints object
 * and running algorithm on given data file
 */
public class Main {
    public static void main(String[] args) {
        ClosestPairOfPoints cpop = new ClosestPairOfPoints("program2data.txt");
        cpop.findClosestPair();
    }
}
