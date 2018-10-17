package elements;

public class Point {

    double x, y;

    /**
     * @param x - position in xx
     * @param y - position in yy
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return - position in xx
     */
    
    public double getX() {
        return x;
    }
    
    /**
     * 
     * @return - position in yy
     */

    public double getY() {
        return y;
    }

    /**
     * 
     * @param other - other point coordinates
     * @return - distance between the two points
     */
    
    public double getDistance(Point other){
        return Math.sqrt(Math.pow(other.getX()-this.x, 2) + Math.pow(other.getY()-this.y, 2));
    }


}
