package g60131.atlg3.ascii.model;

public class Circle extends ColoredShape{
    private Point center;
    private double radius;

    public Circle(char color, Point center, double radius) {
        super(color);
        if (radius < 0) {
            throw new IllegalArgumentException("The raduis is négative");
        }
        this.center = center;
        this.radius = radius;
    }

    /**
     * Verify if a point is inside of the shape
     * @param p the point to verify
     * @return return true if the point is inside the shape
     */
    @Override
    public boolean isInside(Point p) {
        return center.distanceTo(p) <= radius;
    }

    /**
     * Move the circle
     * @param dx axe x
     * @param dy axe y
     */
    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }


}
