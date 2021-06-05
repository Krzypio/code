import java.util.Arrays;

public class OrientationMatrix {
    private double alphaDegreesX = 0;
    private double betaDegreesY = 0;
    private double gammaDegreesZ = 0;

    private double[][] rX = new double[3][3];
    private double[][] rY = new double[3][3];
    private double[][] rZ = new double[3][3];

    double[][] r = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

    private void fillRX() {
        double radians = Math.toRadians(this.alphaDegreesX);
        rX[0][0] = 1;
        rX[0][1] = 0;
        rX[0][2] = 0;

        rX[1][0] = 0;
        rX[1][1] = Math.cos(radians);
        rX[1][2] = -Math.sin(radians);

        rX[2][0] = 0;
        rX[2][1] = Math.sin(radians);
        rX[2][2] = Math.cos(radians);
    }

    private void fillRY() {
        double radians = Math.toRadians(this.betaDegreesY);
        rY[0][0] = Math.cos(radians);
        rY[0][1] = 0;
        rY[0][2] = Math.sin(radians);

        rY[1][0] = 0;
        rY[1][1] = 1;
        rY[1][2] = 0;

        rY[2][0] = -Math.sin(radians);
        rY[2][1] = 0;
        rY[2][2] = Math.cos(radians);
    }

    private void fillRZ() {
        double radians = Math.toRadians(this.gammaDegreesZ);
        rZ[0][0] = Math.cos(radians);
        rZ[0][1] = -Math.sin(radians);
        rZ[0][2] = 0;

        rZ[1][0] = Math.sin(radians);
        rZ[1][1] = Math.cos(radians);
        rZ[1][2] = 0;

        rZ[2][0] = 0;
        rZ[2][1] = 0;
        rZ[2][2] = 1;
    }

    public OrientationMatrix() {
        fillRX();
        fillRY();
        fillRZ();
    }

    private double[][] multiplyMatrices(double[][] a, double[][] b) {
        double result[][] = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = 0;   //clear
                for (int k = 0; k < 3; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }//for k
            }// for j
        }// for i
        return result;
    }//multiplyMatrices()

    private double[][] copyOfR(){
        double[][] copyR = new double[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                copyR[i][j] = r[i][j];
            }//for j
        }//for i
        return copyR;
    }

    public void setAlphaDegreesX(double alphaDegreesX) {
        this.alphaDegreesX = alphaDegreesX;
        fillRX();
        r = multiplyMatrices(copyOfR(), rX);
    }

    public void setBetaDegreesY(double betaDegreesY) {
        this.betaDegreesY = betaDegreesY;
        fillRY();
        r = multiplyMatrices(copyOfR(), rY);
    }

    public void setGammaDegreesZ(double gammaDegreesZ) {
        this.gammaDegreesZ = gammaDegreesZ;
        fillRZ();
        r = multiplyMatrices(copyOfR(), rZ);
    }

    public double[][] getR() {
        return r;
    }

    private String matrixToString(double[][] matrix){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(Double.toString(matrix[i][j]) + "\t");
            }//for j
            sb.append("\n");
        }//for i
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String strDouble = String.format("%5.2f", r[i][j]);
                sb.append(strDouble + '\t');
            }//for j
            sb.append("\n");
        }//for i
        sb.setLength(sb.length()-1);
        return sb.toString();
    }//toString()
}