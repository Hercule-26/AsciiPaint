package g60131.atlg3.ascii.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @Test
    void rectangleIsInside() {
        Rectangle rectangle = new Rectangle('R', new Point(22, 22), 10, 3);
        Point p1 = new Point(28, 23); // True
        boolean expResult = true;
        assertEquals(expResult, rectangle.isInside(p1));
    }

    @Test
    void rectangleIsNotInside() {
        Rectangle rectangle = new Rectangle('R', new Point(22, 22), 10, 3);
        Point p2 = new Point(40, 40); // False
        boolean expResult2 = false;
        assertEquals(expResult2, rectangle.isInside(p2));
    }

    @Test
    void CircleIsInside() {
        Circle circle = new Circle('C', new Point(20, 20), 5);
        Point p1 = new Point(23, 23); // True
        Point p2 = new Point(10, 10); // False
        boolean expResult = true;
        boolean expResult2 = false;
        assertEquals(expResult, circle.isInside(p1));
        assertEquals(expResult2, circle.isInside(p2));
    }

    @Test
    void CircleIsNotInside() {
        Circle circle = new Circle('C', new Point(20, 20), 5);
        Point p2 = new Point(10, 10); // False
        boolean expResult2 = false;
        assertEquals(expResult2, circle.isInside(p2));
    }

    /*
        Pas besoin de faire des test pour SquareIsInside car le square prend tout de la super classe
    */

}