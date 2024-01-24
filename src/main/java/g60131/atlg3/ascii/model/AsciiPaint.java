package g60131.atlg3.ascii.model;

import g60131.atlg3.ascii.util.Command;
import g60131.atlg3.ascii.util.CommandManager;

public class AsciiPaint {
    private Drawing drawing;
    private CommandManager commandManager = new CommandManager();

    public AsciiPaint() {}
    public AsciiPaint(int width, int height) {
        this.drawing = new Drawing(width, height);
    }

    /**
     * Create a new circle
     * @param x axe x of the point
     * @param y axe y of the point
     * @param radius radius of circle
     * @param color color of circle
     */
    public void newCircle(int x, int y, double radius, char color) {
        if (radius <= 0) {
            throw new IllegalArgumentException("ANGLAIS !! Rayon négatif: "+radius);
        }
        Circle circle = new Circle(color, new Point(x, y), radius);
        Command command = new AddCommand(circle, drawing);
        commandManager.newCommand(command);
    }

    /**
     * Create a new rectangle
     * @param x axe x of the point
     * @param y axe y of the point
     * @param width the width of rectangle
     * @param height the height of rectangle
     * @param color the color of rectangle
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Valeur négatif");
        }
        Rectangle rectangle = new Rectangle(color, new Point(x, y), width, height);
        Command command = new AddCommand(rectangle, drawing);
        commandManager.newCommand(command);
    }

    /**
     * Create a new square
     * @param x axe x of the point
     * @param y axe y of the point
     * @param side the width of the side
     * @param color the color of the square
     */
    public void newSquare(int x, int y, double side, char color) {
        if (side <= 0) {
            throw new IllegalArgumentException("Valeur négatif");
        }
        Square square = new Square(color, new Point(x, y), side);
        Command command = new AddCommand(square, drawing);
        commandManager.newCommand(command);
    }

    /**
     * Create a new line
     * @param x1 abcsis x of the firt point
     * @param y1 abcsis y of the first point
     * @param x2 abcsis x of the second point
     * @param y2 abcsis y of the second point
     * @param color the color of the line
     */
    public void newLine(int x1, int y1, int x2, int y2, char color) {
        Line line = new Line(color, x1, y1, x2, y2);
        Command command = new AddCommand(line, drawing);
        commandManager.newCommand(command);
    }

    /**
     * Move the shape
     * @param pos the position of the shape in the list
     * @param dx axe x
     * @param dy axe y
     */
    public void moveShape(int pos, double dx, double dy) {
        Command command = new MoveCommand(drawing.getShapeAt(pos), dx, dy);
        commandManager.newCommand(command);
    }

    /**
     * Delete a shape
     * @param pos the position ine the liste of shape
     */
    public void deleteShape(int pos) {
        Command command = new DeleteCommand(pos, drawing.getShapeAt(pos), drawing);
        commandManager.newCommand(command);
    }

    /**
     * reset the color of the shape
     * @param pos the position of the shape in the list
     * @param color the new color to set.
     */
    public void setShapeColor(int pos, char color) {
        Shape shape = drawing.getShapeAt(pos);
        Command command = new SetCommand(shape, color);
        commandManager.newCommand(command);
    }

    /**
     * return the drawing
     * @return the drawing
     */
    public Drawing getDrawing() {return drawing;}

    public void group(char color, int[] shapeIndex) {
        int shapesListSize = drawing.getShapesList().size();
        if (shapeIndex.length > drawing.getShapesList().size()) {
            throw new IllegalArgumentException("they are to much number for shapes in the paint");
        }
        for (int index: shapeIndex) {
            if (index < 0 || index > shapesListSize-1) {
                throw new IllegalArgumentException("Invalid index");
            }
        }
        sort(shapeIndex);
        Command command = new GroupCommand(shapeIndex, drawing, color);
        commandManager.newCommand(command);
    }

    /**
     * Delete the group et put the shapes in the group
     * to the drawing shapes list
     * @param index the index of the group shape in the shapes List
     */
    public void ungroup(int index) {
        if (index < 0 || index >= drawing.getShapesList().size()) {
            throw new IllegalArgumentException("Index invalid");
        }
        if (drawing.getShapeAt(index).getClass() != Group.class) {
            throw new IllegalArgumentException("The shape at index " + index + " is not a group");
        }
        Command command = new UngroupCommand(drawing, index);
        commandManager.newCommand(command);
    }

    /**
     * This method has used to sort an array
     * @param list the array to sort
     */
    private void sort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int tmp = list[i];
            int j = i-1;
            while (j >= 0 && list[j] < tmp) {
                list[j+1] = list[j];
                j--;
            }
            list[j+1] = tmp;
        }
    }

    /**
     * Undo a command
     */
    public void undo() {
        commandManager.undo();
    }

    /**
     * Redo a command
     */
    public void redo() {
        commandManager.redo();
    }


}
