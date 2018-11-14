package elements;

public class Destiny {
    private Point position;
    private Point initialPosition;
    private double totalTime;
    private double time;
    double deltaX, deltaY;


    public Destiny(Point position, Point positionActual, double time) {
        this.position = position;
        this.time = time;
        this.totalTime = time;
        deltaX = position.getX() - positionActual.getX();
        deltaY = position.getY() - positionActual.getY();
        initialPosition = new Point(positionActual.getX(), positionActual.getY()) ;
    }

    public void update(double delta){
        time -= delta;
    }

    public Point getPosition() {
        return position;
    }

    public double getTime() {
        return time;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public Point getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Point initialPosition) {
        this.initialPosition = initialPosition;
    }
}
