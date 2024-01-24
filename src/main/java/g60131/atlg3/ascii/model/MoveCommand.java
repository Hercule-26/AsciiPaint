package g60131.atlg3.ascii.model;

import g60131.atlg3.ascii.util.Command;

public class MoveCommand implements Command {
    private  Shape shape;
    private double dx;
    private double dy;

    public MoveCommand(Shape shape, double dx, double dy) {
        this.shape = shape;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        shape.move(dx, dy);
    }

    @Override
    public void unexecute() {
        shape.move(-dx, -dx);
    }
}
