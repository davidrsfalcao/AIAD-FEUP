package elements;

public class Point {

    int x, y;

    /**
     * @param x - position in xx
     * @param y - position in yy
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return - position in xx
     */
    
    public int getX() {
        return x;
    }
    
    /**
     * 
     * @return - position in yy
     */

    public int getY() {
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
