package g60131.atlg3.ascii.model;

public class Rectangle extends ColoredShape {
    private Point upperLeft;
    private double width;
    private double height;

    public Rectangle(char color, Point upperLeft, double width, double height) {
        super(color);
        if (width  <= 0 || height <= 0) {
            throw new IllegalArgumentException("width or height under 0 ");
        }
        this.upperLeft = upperLeft;
        this.width = width-1; // -1 car le point initial en plus sinon
        this.height = height-1; // -1 car le point initial en plus sinon
    }

    /**
     * Verify if a point is inside of shape
     * @param p the point to verify
     * @return return tru if is inside the shape
     */
    @Override
    public boolean isInside(Point p) {
        return  upperLeft.getX() <= p.getX()
                && p.getX() <= (upperLeft.getX() + width)
                && upperLeft.getY() <= p.getY()
                && p.getY() <= (upperLeft.getY() + height);
    }

    @Override
    public void move(double dx, double dy) {upperLeft.move(dx, dy);}

}
