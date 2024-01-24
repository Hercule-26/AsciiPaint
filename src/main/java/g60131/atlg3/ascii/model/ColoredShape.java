package g60131.atlg3.ascii.model;

public abstract class ColoredShape implements Shape {
    private char color;

    /**
     * Return the color of the shape
     * @param color the color of the shape
     */
    public ColoredShape(char color) {
        this.color = color;
    }

    /**
     * Return the color of the shape
     * @return the color of the shape
     */
    public char getColor() {
        return color;
    }

    /**
     * reset the color of the shape
     * @param color the new color to set
     */
    public void setColor(char color) {
        this.color = color;
    }

}
