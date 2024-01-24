package g60131.atlg3.ascii.model;

public class Line extends ColoredShape{
    private Point p1;
    private Point p2;
    public Line(char color, int x1, int y1, int x2, int y2) {
        super(color);
        if (x2 <= x1) {
            p1 = new Point(x2, y2);
            p2 = new Point(x1, y1);
        } else {
            p1 = new Point(x1, y1);
            p2 = new Point(x2, y2);
        }
    }

    @Override
    public boolean isInside(Point p) {
        boolean horizontal = (p1.getX() <= p.getX() && p.getX() <= p2.getX()) && (p.getY() == p1.getY() && p.getY() == p2.getY());
        boolean vertical = ((p1.getY() <= p.getY() && p.getY() <= p2.getY()
                            || p2.getY() <= p.getY() && p.getY() <= p1.getY())
                            && (p.getX() == p1.getX() && p.getX() == p2.getX()));
        boolean diagonal = (p1.getX() <= p.getX() && p.getX() <= p2.getX()) && (p1.getY() <= p.getY() && p.getY() <= p2.getY());
        if (vertical) {
            return true;
        }
        else if (horizontal || diagonal) {
            double m =  (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()); // m = DeltaY / DeltaX = ...
            double distance = Math.abs(m * p.getX() - p.getY() - m * p1.getX() + p1.getY()) / Math.sqrt(m * m + 1);
            // NaN pour la ligne vertical car DeltaX = 0 -> division par 0 et donc m = infinie et distance = NaN.
            return Double.isNaN(distance) || distance <= 0.5;
        } else {
            return false;
        }
    }

    @Override
    public void move(double dx, double dy) {
        p1.move(dx, dy);
        p2.move(dx, dy);
    }
}