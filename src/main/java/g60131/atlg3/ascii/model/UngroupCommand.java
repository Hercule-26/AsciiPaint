package g60131.atlg3.ascii.model;

import g60131.atlg3.ascii.util.Command;

import java.util.List;

public class UngroupCommand implements Command {
    private Drawing drawing;
    private int index;
    private List<Shape> shapes;
    private Group group;

    public UngroupCommand(Drawing drawing, int index) {
        this.drawing = drawing;
        this.index = index;
    }

    @Override
    public void execute() {
        group = (Group) drawing.getShapeAt(index); // Force la conversion
        shapes = group.getshapeList();
        for (int i = shapes.size()-1; i >= 0 ; i--) { // Ajouter de manière décroissante
            drawing.addShape(shapes.get(i));
        }
        drawing.remove(group);
    }

    @Override
    public void unexecute() {
        for (Shape shape : shapes) {
            drawing.remove(shape);
        }
        drawing.addShapeAt(index, group);
    }
}
