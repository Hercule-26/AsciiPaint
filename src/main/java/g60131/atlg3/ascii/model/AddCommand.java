package g60131.atlg3.ascii.model;

import g60131.atlg3.ascii.util.Command;

public class AddCommand implements Command {
    private Shape shape;
    private Drawing drawing;

    public AddCommand(Shape shape, Drawing drawing) {
        this.shape = shape;
        this.drawing = drawing;
    }

    @Override
    public void execute() {
        drawing.addShape(shape);
    }

    @Override
    public void unexecute() {
        drawing.remove(shape);
    }
}
