package Bai_5;

public class Shape {
    private String color = "red";
    private boolean filled = true;

    public Shape() {
        this.color = "red";
        this.filled = true;
    }

    public Shape(String color) {
        this.color = color;
        this.filled = true;
    }

    public Shape(boolean filled) {
        this.color = "red";
        this.filled = filled;
    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        String str = "";
        if (isFilled())
            str = "filled";
        else
            str = "Not filled";
        return "A Shape with color of " + getColor() + " and " + str;
    }
}
