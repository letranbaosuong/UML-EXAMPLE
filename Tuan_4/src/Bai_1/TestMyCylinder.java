package Bai_1;

public class TestMyCylinder {
    public static void main(String[] args) {
        // Declare and allocate a new instance of cylinder
        // with default color, radius, and height
        myCylinder c1 = new myCylinder();
        System.out.println("c1 is " + c1.toString()
                + "\nCylinder:"
                + " radius=" + c1.getRadius()
                + " height=" + c1.getHeight()
                + " base area=" + c1.getArea()
                + " volume=" + c1.getVolume()
                + " Surface Area=" + c1.getSurfaceArea()
        );
        // Declare and allocate a new instance of cylinder
        // specifying height, with default color and radius
        myCylinder c2 = new myCylinder(10.0);
        System.out.println("c2 is " + c2.toString()
                + "\nCylinder:"
                + " radius=" + c2.getRadius()
                + " height=" + c2.getHeight()
                + " base area=" + c2.getArea()
                + " volume=" + c2.getVolume()
                + " Surface Area=" + c2.getSurfaceArea()
        );
        // Declare and allocate a new instance of cylinder
        // specifying radius and height, with default color
        myCylinder c3 = new myCylinder(2.0, 10.0);
        System.out.println("c3 is " + c3.toString()
                + "\nCylinder:"
                + " radius=" + c3.getRadius()
                + " height=" + c3.getHeight()
                + " base area=" + c3.getArea()
                + " volume=" + c3.getVolume()
                + " Surface Area=" + c3.getSurfaceArea()
        );
    }
}
