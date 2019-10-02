package Bai_5;

public class test_bai_5 {
    public static void main(String[] args) {
        Shape shape1 = new Shape("blue");
        System.out.println("shape1 is " + shape1.toString());

        Shape shape2 = new Shape("green", false);
        System.out.print("shape2 is ");
        if (shape2.isFilled())
            System.out.println("filled");
        else
            System.out.println("not filled");
        System.out.println("The color of shape2 is " + shape2.getColor());

        Circle circle1 = new Circle(5.5, "yellow", false);
        System.out.println("circle1 is " + circle1.toString());

        Circle circle2 = new Circle(2.6);
        System.out.println("circle2 is " + circle2.toString());

        Circle circle3 = new Circle(1.0);
        System.out.println("During initialization, circle3 is " + circle3.toString());

        circle3.setRadius(1.9);
        System.out.println("Set radius to " + circle3.getRadius());
        System.out.println("Now the radius is " + circle3.getRadius());

        circle3.setColor("brown");
        System.out.println("Set color to " + circle3.getColor());

        circle3.setFilled(false);
        System.out.println("Set filled to " + circle3.isFilled());

        System.out.println("Now circle3 is " + circle3.toString());
        System.out.println("The area is of " + circle3.getArea());
        System.out.println("The perimeter is of " + circle3.getPerimeter());

        Rectangle rectangle1 = new Rectangle(5.0, 7.0);
        System.out.println("rectangle1 is " + rectangle1.toString());

        Rectangle rectangle2 = new Rectangle(4.1, 3.6, "yellow");
        System.out.println("rectangle2 is " + rectangle2.toString());
        System.out.println("The area is of " + rectangle2.getArea());
        System.out.println("The perimeter is of " + rectangle2.getPerimeter());

        System.out.println("Set width to 20");
        rectangle2.setWidth(20);
        System.out.println("The new width is " + rectangle2.getWidth());

        System.out.println("Set length to 50");
        rectangle2.setLength(50);
        System.out.println("The new length is " + rectangle2.getLength());

        System.out.println("Set color to green");
        rectangle2.setColor("green");
        System.out.println("The color is " + rectangle2.getColor());

        Rectangle rectangle3 = new Rectangle();
        System.out.print("rectangle3 is ");
        if (rectangle3.isFilled())
            System.out.println("filled");
        else
            System.out.println("Not filled");

        Square square1 = new Square();
        System.out.println("square1 is " + square1.toString());

        System.out.println("Set color to blue");
        square1.setColor("blue");
        System.out.println("Set filled to false");
        square1.setFilled(false);
        System.out.println("Set length to 10");
        square1.setLength(10);
        System.out.println("The side is " + square1.getSide());
        System.out.print("square1 is ");
        if (square1.getLength() == square1.getWidth())
            System.out.println("a square");
        else
            System.out.println("Not a square");
        System.out.println("Now square1 is " + square1.toString());

        Square square2 = new Square(3);
        System.out.println("square2 is " + square2.toString());
        System.out.println("The area is of " + square2.getArea());
        System.out.println("The perimeter is of " + square2.getPerimeter());
    }
}
