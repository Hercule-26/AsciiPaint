package g60131.atlg3.ascii.model;

import java.util.ArrayList;
import java.util.List;

public class Group extends ColoredShape{
    private List<Shape> shapes;
    public Group(char color) {
        super(color);
        this.shapes = new ArrayList<>();
    }
    @Override
    public boolean isInside(Point p) {
        for (Shape shape: shapes) {
            if (shape.isInside(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Move all shape in the group
     * @param dx move in axe x
     * @param dy move in axe y
     */
    @Override
    public void move(double dx, double dy) {
        for (Shape shape: shapes) {
            shape.move(dx, dy);
        }
    }

    /**
     * Add a shape to the group
     * @param shape the shape to add to the list
     */
    protected void addShape(Shape shape) {
        shapes.add(shape);
    }

    /**
     * Retourne the group shapes list
     * @return A list of shapes
     */
    public List<Shape> getshapeList() {return new ArrayList<>(shapes);}
}
