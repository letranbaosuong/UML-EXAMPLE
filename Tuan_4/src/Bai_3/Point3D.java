package Bai_3;

public class Point3D extends Point2D {
    private float z = 0.0f;

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float[] getXYZ() {
        return new float[]{super.getX(), super.getY(), z};
    }

    public void setXYZ(float x, float y, float z) {
        setX(x);
        setY(y);
        this.z = z;
    }

    public Point3D(float z) {
        super();
        this.z = z;
    }

    public Point3D(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }

    public Point3D(double v, double v1, double v2) {
    }

    @Override
    public String toString() {
        return "(" + super.getX() + ", " + super.getY() + ", " + this.z + ")";
    }

    public Point3D() {
        super();
        this.z = 0.0f;
    }
}
