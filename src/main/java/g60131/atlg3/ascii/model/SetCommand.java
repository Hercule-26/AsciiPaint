package g60131.atlg3.ascii.model;

import g60131.atlg3.ascii.util.Command;

public class SetCommand implements Command {
    private Shape shape;
    private char color;
    private char oldColor;

    public SetCommand(Shape shape, char color) {
        this.shape = shape;
        this.color = color;
        oldColor = shape.getColor();
    }

    @Override
    public void execute() {
        shape.setColor(color);
    }

    @Override
    public void unexecute() {
        shape.setColor(oldColor);
    }
}
