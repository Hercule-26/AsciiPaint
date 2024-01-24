package g60131.atlg3.ascii.model;

/**
 * JAVADOC DES INTERFACES
 *
 * @author Alper
 */
public interface Shape {
    boolean isInside(Point p);
    void move(double dx, double dy);
    char getColor();
    void setColor(char color);
}
