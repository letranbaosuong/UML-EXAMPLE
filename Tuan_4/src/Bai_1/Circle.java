package Bai_1;

/*
 * The Circle class models a circle with a radius and color.
 */
public class Circle {  // Save as "Circle.java"
    // private instance variable, not accessible from outside this class
    private double radius;
    private String color;

    // The default constructor with no argument.
    // It sets the radius and color to their default value.
    public Circle() {
        radius = 1.0;
        color = "red";
    }

    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    // 2nd constructor with given radius, but color default
    public Circle(double r) {
        radius = r;
        color = "red";
    }

    // A public method for retrieving the radius
    public double getRadius() {
        return radius;
    }

    // A public method for computing the area of circle
    public double getArea() {
        return radius * radius * Math.PI;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", color='" + color + '\'' +
                '}';
    }
}
