/**
 * Class to describe position
 */
public class PositionMatrix extends Matrix {
    /**
     * create 3x1 position matrix with x=0, y=0, z=0
     */
    public PositionMatrix() {
        super(3, 1);
    }

    /**
     * create 3x1 position matrix with x=x, y=y, z=z
     */
    public PositionMatrix(double x, double y, double z) {
        super(3, 1);
        matrix[0][0] = x;
        matrix[1][0] = y;
        matrix[2][0] = z;
    }

    /**
     * Create 3x1 position matrix with the same parameters as source
     * @param position is matrix to copy
     */
    public PositionMatrix(PositionMatrix position) {
        super(3, 1);
        set(position.get());
    }

    /**
     * Create 3x1 position matrix of point relative to A
     * @param aTb is transformation matrix of B relative to A
     * @param bP is position matrix of point relative to B
     */
    public PositionMatrix(TransformationMatrix aTb, PositionMatrix bP) {
        this();
        Matrix bPextended = new Matrix(4, 1);
        for(int i=0; i<3; i++)
            bPextended.get()[i][0] = bP.get()[i][0];
        bPextended.get()[3][0] = 1;

        Matrix aP = new Matrix(aTb);
        aP.multiply(bPextended);
        for (int i=0; i<3; i++)
            matrix[i][0] = aP.get()[i][0];
    }

    /**
     * Multiply OrientationMatrix * PositionMatrix
     * @param orientation
     * @param positionMatrix
     */
    public PositionMatrix(OrientationMatrix orientation, PositionMatrix positionMatrix){
        this();
        for(int i=0; i<3; i++){
            double value = 0;
            for (int j=0; j<3; j++)
                value += positionMatrix.get()[j][0] * orientation.get()[i][j];
            get()[i][0] = value;
        }//for i
    }
}
