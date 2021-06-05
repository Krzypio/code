import java.util.Objects;

public class PositionMatrix {
    private double x = Double.POSITIVE_INFINITY;
    private double y = Double.POSITIVE_INFINITY;
    private double z = Double.POSITIVE_INFINITY;

    private PositionMatrix() {
    }

    public PositionMatrix(double x, double y, double z) {
        setXYZ(x, y, z);
    }

    public void setXYZ(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
