package g60131.atlg3.ascii.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Drawing {
    private List<Shape> shapes;
    private int height;
    private int width;

    public Drawing() {
        shapes = new LinkedList<>();
    }

    public Drawing(int width, int height) {
        if(width < 0 ||height < 0) {
            throw new IllegalArgumentException("Width or height invalid");
        }
        shapes = new LinkedList<>();
        this.height = height;
        this.width = width;
    }

    /**
     * Add new shape to the List
     * @param shapes shape to add
     */
    void addShape(Shape shapes) {
        this.shapes.add(shapes);
    }

    /**
     * Method return a shape
     * @param p The point where the shape is
     * @return A shape
     */
    public Shape getShape(Point p) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.isInside(p)) {
                return shape;
            }
        }
        return null;
    }

    /**
     * Add a shape at one position in list of shape
     * @param pos The index of the list to add the shape
     * @param shape The shape to add
     */
    public void addShapeAt(int pos, Shape shape) {
        shapes.add(pos, shape);
    }

    /**
     * Move a shape
     * @param pos The position of the shape in list
     * @param dx axe x
     * @param dy axe y
     */
    void moveShape(int pos, double dx, double dy) {
        if (pos < 0 || pos > shapes.size() -1) {
            throw new IllegalArgumentException("Position invalide: "+pos);
        }
        shapes.get(pos).move(dx, dy);
    }

    /**
     * Return shape list
     */
    public List<Shape> getShapesList() {
        return new ArrayList<>(shapes);
    }

    /**
     * Get a shape in a position in shapes list
     * @param index the index of the shape to get
     * @return The shape
     */
    public Shape getShapeAt(int index) {return shapes.get(index);}

    void setShapesColor(int pos, char color) {
        if (pos < 0 || pos > shapes.size()-1) {
            throw new IllegalArgumentException("Position invalide");
        }
        shapes.get(pos).setColor(color);
    }

    /**
     * getter to get the height of drawing
     * @return the height of drawing
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter to get the width of drawing
     * @return the width of drawing
     */
    public int getWidth() {
        return width;
    }

    /**
     * Remove a shape in list
     * @param shapeIndex the index of the shape in list
     */
    protected void remove(int shapeIndex) {
        shapes.remove(shapeIndex);
    }

    /**
     * Remove a shape
     * @param shape the shape to remove in the list
     */
    protected void remove(Shape shape) {
        shapes.remove(shape);
    }
}
