package g60131.atlg3.ascii.model;

import g60131.atlg3.ascii.util.Command;

public class DeleteCommand implements Command {
    private int index;
    private Shape shape;
    private Drawing drawing;

    public DeleteCommand(int index, Shape shape, Drawing drawing) {
        this.index = index;
        this.shape = shape;
        this.drawing = drawing;
    }

    @Override
    public void execute() {
        drawing.remove(shape);
    }

    @Override
    public void unexecute() {
        drawing.addShapeAt(index, shape);
    }
}
