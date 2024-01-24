package g60131.atlg3.ascii.model;

import g60131.atlg3.ascii.util.Command;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements Command {
    private int[] index;
    private List<Shape> shapes;
    private Drawing drawing;
    private Group group;

    public GroupCommand(int[] index, Drawing drawing, char color) {
        // trier la liste.
        this.index = index;
        this.shapes = new ArrayList<>();
        this.drawing = drawing;
        group = new Group(color);
    }

    /**
     * Execute the commande
     */
    @Override
    public void execute() {
        if (shapes.size() == 0) { // Si première exécution
            for (int index : index) { // dans le constructeur.
                Shape shape = drawing.getShapeAt(index);
                shapes.add(shape);
                group.addShape(shape);
                drawing.remove(shape);
            }
            drawing.addShape(group);
        } else {
            for (Shape shape : shapes) {
                drawing.remove(shape);
            }
            drawing.addShape(group);
        }
    }

    /**
     * Unexecute the last command
     */
    @Override
    public void unexecute() {
        for (int i = shapes.size()-1; i >= 0 ; i--) {
            drawing.addShapeAt(index[i], shapes.get(i));
        }
        drawing.remove(group);
    }
}
