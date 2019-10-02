package Bai_1;

public class myCylinder extends myCircle {
    private double height = 1.0;

    public myCylinder() {
        super();
        this.height = 1.0;
    }

//    public myCylinder(double height) {
//        super();
//        this.height = height;
//    }

    public myCylinder(double radius) {
        super(radius);
        this.height = 1.0;
    }

    public myCylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    public myCylinder(double radius, double height, String color) {
        super(radius, color);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSurfaceArea() {
        return 2 * Math.PI * super.getRadius() * getHeight() + 2 * super.getArea();
    }

    public double getVolume() {
        return Math.PI * super.getRadius() * super.getRadius() * this.height;
    }

    @Override
    public String toString() { // in Cylinder class
        return "Cylinder: subclass of " + super.toString() // use Circle's toString()
                + " height=" + height;
    }
}
