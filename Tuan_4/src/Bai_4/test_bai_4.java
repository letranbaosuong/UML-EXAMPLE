package Bai_4;

public class test_bai_4 {
    public static void main(String[] args) {
        MovablePoint mp1 = new MovablePoint(100, 200, 10, 15);
        System.out.println("mp1 is " + mp1.toString());

        mp1.move();
        System.out.println("After 1 tick, it moves to " + mp1.toString());

        MovablePoint mp2 = new MovablePoint(-100, 30);
        System.out.println("During initialization, mp2 is " + mp2.toString());

        Point p1 = new Point(3, 7);
        System.out.println("Set the new start point to " + p1.toString());

        mp2.setXY(p1.getX(), p1.getY());
        mp2.move();
        System.out.println("After 1 tick, it moves to " + mp2.toString());
        System.out.println("After 1 tick, it moves to (-100.0, 30.0),speed=(-100.0,30.0)");

        MovablePoint mp3 = new MovablePoint();
        System.out.println("During initialization, mp3 is " + mp3.toString());

        mp3.move();
        System.out.println("After 1 tick, it does not move. " + mp3.toString());

        mp3.setXY(150, 20);
        System.out.print("Take it to new position (");
        for (int i = 0; i < mp3.getXY().length; i++) {
            System.out.print(mp3.getXY()[i]);
            if (i < mp3.getXY().length - 1)
                System.out.print(", ");
        }
        System.out.println(")");
        mp3.setSpeed(18, 30);
        System.out.print("Change its speed to (");
        for (int i = 0; i < mp3.getSpeed().length; i++) {
            System.out.print(mp3.getSpeed()[i]);
            if (i < mp3.getSpeed().length - 1)
                System.out.print(", ");
        }
        System.out.println(")");
        mp3.move();
        System.out.println("After 1 tick, it moves to " + mp3.toString());
    }
}
