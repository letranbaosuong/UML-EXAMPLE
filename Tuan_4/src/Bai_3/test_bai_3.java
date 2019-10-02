package Bai_3;

public class test_bai_3 {
    public static void main(String[] args) {
        Point3D p3d1 = new Point3D(3, 4, 5);
        System.out.println("p3d1 is " + p3d1.toString());

        Point2D p2d2 = new Point2D(-1, 5);
        System.out.println("p2d2 is " + p2d2.toString());

        Point3D p3d2 = new Point3D(-1, 5, 8);
        System.out.println("p3d2 is " + p3d2.toString());

        Point3D p3d3 = new Point3D(-2, 1, 9);
        System.out.println("p3d3 is " + p3d3.toString());

        Point3D p3d4 = new Point3D(0, 0, 0);
        System.out.println("p3d4 is " + p3d4.toString());

        p3d4.setXY(-4, 7);
        System.out.print("setXY to (");
        for (int i = 0; i < p3d4.getXY().length; i++) {
            System.out.print(p3d4.getXY()[i]);
            if (i < p3d4.getXY().length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(")");
        p3d4.setZ(11);
        System.out.println("setZ to " + p3d4.getZ());
        System.out.println("Now p3d4 is " + p3d4.toString());
    }
}
