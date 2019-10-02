package Bai_1;

public class myCircle {
    private double radius = 1.0;
    private String color = "red";

    public myCircle() {
        this.radius = 1.0;
        this.color = "red";
    }

    public myCircle(double radius) {
        this.radius = radius;
    }

    public myCircle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getArea() {
        return Math.PI * getRadius() * getRadius();
    }

    @Override
    public String toString() {
        return "Circle{radius=" + getRadius() + ",color='" + getColor() + "'}";
    }
}
