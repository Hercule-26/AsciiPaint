package g60131.atlg3.ascii.view;

import g60131.atlg3.ascii.model.Drawing;
import g60131.atlg3.ascii.model.Point;
import g60131.atlg3.ascii.model.Shape;
import java.util.List;

public class View {

    public View() {
    }

    /**
     * show all shape
     */
    public void display(Drawing drawing) {
        for (int i = 0; i < drawing.getHeight(); i++) {
            for (int j = 0; j < drawing.getWidth(); j++) {
                Point p = new Point(j, i);
                if (drawing.getShape(p) != null) { // [j][i] parce que (x, y)
                    System.out.print(drawing.getShape(p).getColor() + " "); // [j][i] parce que (x, y)
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Show all command
     */
    public void showCommand() {
        System.out.println("===============================================");
        System.out.println("|                   Command                   |");
        System.out.println("===============================================");
        System.out.println(" - add circle x | y | radius | color");
        System.out.println(" - add square x | y | side | color");
        System.out.println(" - add rectangle x | y | widht | heigth | color");
        System.out.println(" - add line x1 | y1 | x2 | y2 | color");
        System.out.println(" - show (show all shape)");
        System.out.println(" - move 'pos' | x | y");
        System.out.println(" - set pos | color (letter)");
        System.out.println(" - group color | index(es) ...");
        System.out.println(" - ungroup pos");
        System.out.println(" - undo : undo a command");
        System.out.println(" - redo : redo a command");
        System.out.println(" - delete pos");
        System.out.println(" - exit (quit the program)");
        System.out.println("===============================================");
    }

    /**
     * Show list of shapes
     * @param shapes list of shape
     */
    public void showShapeList(List<Shape> shapes) {
        for (int i = 0; i < shapes.size(); i++) {
            System.out.print(i + " : " + shapes.get(i).getColor() + " | ");
        }
        System.out.println();
    }
}
