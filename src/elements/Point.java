package elements;

/**
 * Class that represents a point in 2D
 */
public class Point {

    double x, y;

    /**
     * @param x - position in xx (kms)
     * @param y - position in yy (kms)
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return - position in xx (kms)
     */
    
    public double getX() {
        return x;
    }
    
    /**
     * 
     * @return - position in yy (kms)
     */

    public double getY() {
        return y;
    }

    /**
     * Returns the distance between 2 points (kms)
     *
     * @param other - other point coordinates
     * @return - distance between the two points
     */
    public double getDistance(Point other){
        return Math.sqrt(Math.pow(other.getX() - x, 2) + Math.pow(other.getY() - y, 2));
    }

    @Override
    public String toString() {
        return "x:" + x + "  y:" + y;
    }
}
