package g60131.atlg3.ascii.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsciiPaintTest {

    @Test
    void newCircle() {
        AsciiPaint asciiPaint = new AsciiPaint(40, 40);
        Point p = new Point(20, 20);
        asciiPaint.newCircle(20, 20, 5, 'C');
        List<Shape> expShape = asciiPaint.getDrawing().getShapesList();
        assertEquals(expShape.get(0), asciiPaint.getDrawing().getShape(p));
    }

    @Test
    void newCircleError() {
        AsciiPaint asciiPaint = new AsciiPaint(40, 40);
        assertThrows(Exception.class, () -> {
            asciiPaint.newCircle(20, 20, -5, 'C');
        });
    }

    @Test
    void newRectangle() {
        AsciiPaint asciiPaint = new AsciiPaint(40, 40);
        Point p = new Point(20, 20);
        asciiPaint.newRectangle(20, 20, 10, 3, 'R');
        List<Shape> expShape = asciiPaint.getDrawing().getShapesList();
        assertEquals(expShape.get(0), asciiPaint.getDrawing().getShape(p));
    }

    @Test
    void newRectangleError() {
        AsciiPaint asciiPaint = new AsciiPaint(40, 40);
        assertThrows(Exception.class, () -> {
            asciiPaint.newRectangle(20, 20, -10, 3, 'R');
        });
        assertThrows(Exception.class, () -> {
            asciiPaint.newRectangle(20, 20, 10, -3, 'R');
        });
    }

    @Test
    void newSquare() {
        AsciiPaint asciiPaint = new AsciiPaint(40, 40);
        Point p = new Point(20, 20);
        asciiPaint.newSquare(20, 20, 5, 'S');
        List<Shape> expShape = asciiPaint.getDrawing().getShapesList();
        assertEquals(expShape.get(0), asciiPaint.getDrawing().getShape(p));
    }

    @Test
    void newSquareError() {
        AsciiPaint asciiPaint = new AsciiPaint(40, 40);
        assertThrows(Exception.class, () -> {
            asciiPaint.newSquare(20, 20, -5, 'C');
        });
    }

    @Test
    void moveShape() {
        AsciiPaint asciiPaint = new AsciiPaint(100, 100);
        asciiPaint.newCircle(15, 20, 5, 'C');
        asciiPaint.newRectangle(60, 70, 10, 3, 'R');
        asciiPaint.newSquare(10, 15, 5, 'S');

        asciiPaint.moveShape(0, 2, 2);
        Point p1 = new Point(17, 22); // Point of circle after he moved
        asciiPaint.moveShape(1, 2, 2);
        Point p2 = new Point(62, 72); // Point of rectangle after he moved
        asciiPaint.moveShape(2, 2, 2);
        Point p3 = new Point(12, 17); // Point of square after he moved

        List<Shape> expShape = asciiPaint.getDrawing().getShapesList();
        assertEquals(expShape.get(0), asciiPaint.getDrawing().getShape(p1)); // Equals of Circle
        assertEquals(expShape.get(1), asciiPaint.getDrawing().getShape(p2)); // Equals of Rectangle
        assertEquals(expShape.get(2), asciiPaint.getDrawing().getShape(p3)); // Equals of Square
    }
}