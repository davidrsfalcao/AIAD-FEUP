package elements;

public class Destiny {
    private Point position;
    private double totalTime;
    private double time;
    double deltaX, deltaY;


    public Destiny(Point position, Point positionActual, double time) {
        this.position = position;
        this.time = time;
        this.totalTime = time;
        deltaX = positionActual.getX() - position.getX();
        deltaY = positionActual.getY() - position.getY();
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
}
