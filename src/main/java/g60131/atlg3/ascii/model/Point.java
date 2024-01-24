package g60131.atlg3.ascii.model;

/**
 * JAVADOC
 *
 * @author Alper
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this(p.x, p.y);
    }

    /**
     * Move a point
     * @param dx axe x
     * @param dy axe y
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * calculate the distance between two points
     * @param other the second point
     * @return distance between 2 points
     */
    public double distanceTo(Point other) {
        double distance;
        distance = ((other.getX() - this.x) * (other.getX() - this.x))
                + ((other.getY() - this.y) * (other.getY() - this.y));
        distance = Math.sqrt(distance);
        return distance;
    }

    /**
     * Return axe x of the point
     * @return axe x
     */
    public double getX() {return x;}

    /**
     * Return axe y of the point
     * @return axe y
     */
    public double getY() {
        return y;
    }
}
