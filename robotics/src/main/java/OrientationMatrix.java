/**
 * Class to describe orientation
 */
public class OrientationMatrix extends Matrix {
    private final Matrix rX = new Matrix(3,3);
    private final Matrix rY = new Matrix(3,3);
    private final Matrix rZ = new Matrix(3,3);

    /**
     * Create 3x3 orientation matrix with default values I
     */
    public OrientationMatrix() {
        super(3, 3);
        setDefault();
    }

    private void fillRX(double alphaDegrees) {
        double radians = Math.toRadians(alphaDegrees);
        double[][] bufor = new double[3][3];
        bufor[0][0] = 1;                   bufor[0][1] = 0;                  bufor[0][2] = 0;
        bufor[1][0] = 0;                   bufor[1][1] = Math.cos(radians);  bufor[1][2] = -Math.sin(radians);
        bufor[2][0] = 0;                   bufor[2][1] = Math.sin(radians);  bufor[2][2] = Math.cos(radians);
        rX.set(bufor);
    }
    private void fillRY(double betaDegrees) {
        double radians = Math.toRadians(betaDegrees);
        double[][] bufor = new double[3][3];
        bufor[0][0] = Math.cos(radians);   bufor[0][1] = 0;                   bufor[0][2] = Math.sin(radians);
        bufor[1][0] = 0;                   bufor[1][1] = 1;                   bufor[1][2] = 0;
        bufor[2][0] = -Math.sin(radians);  bufor[2][1] = 0;                   bufor[2][2] = Math.cos(radians);
        rY.set(bufor);
    }
    private void fillRZ(double gammaDegrees) {
        double radians = Math.toRadians(gammaDegrees);
        double[][] bufor = new double[3][3];
        bufor[0][0] = Math.cos(radians);   bufor[0][1] = -Math.sin(radians);  bufor[0][2] = 0;
        bufor[1][0] = Math.sin(radians);   bufor[1][1] = Math.cos(radians);   bufor[1][2] = 0;
        bufor[2][0] = 0;                   bufor[2][1] = 0;                   bufor[2][2] = 1;
        rZ.set(bufor);
    }

    /**
     * Rotate by some degrees by X axis. Counter clock wise is used.
     * @param alphaDegrees in degrees
     */
    public void rotateX(double alphaDegrees) {
        fillRX(alphaDegrees);
        multiply(rX);
    }

    /**
     * Rotate by some degrees by Y axis. Counter clock wise is used.
     * @param betaDegrees in degrees
     */
    public void rotateY(double betaDegrees) {
        fillRY(betaDegrees);
        multiply(rY);
    }

    /**
     * Rotate by some degrees by Z axis. Counter clock wise is used.
     * @param gammaDegrees in degrees
     */
    public void rotateZ(double gammaDegrees) {
        fillRZ(gammaDegrees);
        multiply(rZ);
    }

    /**
     * Set matrix as I matrix with 1 on diagonal and 0 everywhere else
     */
    @Override
    public void setDefault(){
        for (int i=0; i<3; i++){
            for(int j=0; j<3; j++)
                if (i==j) matrix[i][j] = 1;
                else matrix[i][j] = 0;
        }//for i
    }
}
