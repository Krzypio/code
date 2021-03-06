/**
 * Class to describe transformation.
 * important: update()
 */
public class TransformationMatrix extends Matrix{
    Matrix position = new Matrix(3,1);
    OrientationMatrix orientation = new OrientationMatrix();

    /**
     * Create transformation matrix based on parameters
     * @param orientation 3x3 matrix
     * @param position 3x1 matrix
     */
    public TransformationMatrix(OrientationMatrix orientation, Matrix position) {
        super(4, 4);
        setDefault();
        this.position.set(position.get());
        this.orientation.set(orientation.get());
        if(!position.isValid() || !orientation.isValid()){
            isValid = false;
            return;
        }
        isValid = true;
        update();
    }

    public TransformationMatrix(TransformationMatrix transformation){
        this(transformation.get());
    }

    public TransformationMatrix(){
        super(4, 4);
        setDefault();
    }
    public TransformationMatrix(double[][] matrix4x4){
        super(4, 4);

        if (matrix4x4.length != 4 || matrix4x4[0].length != 4)
            throw new IllegalArgumentException("Delivered matrix in argument is not 4x4");

        orientation.get()[0][0] = matrix4x4[0][0];
        orientation.get()[0][1] = matrix4x4[0][1];
        orientation.get()[0][2] = matrix4x4[0][2];

        orientation.get()[1][0] = matrix4x4[1][0];
        orientation.get()[1][1] = matrix4x4[1][1];
        orientation.get()[1][2] = matrix4x4[1][2];

        orientation.get()[2][0] = matrix4x4[2][0];
        orientation.get()[2][1] = matrix4x4[2][1];
        orientation.get()[2][2] = matrix4x4[2][2];

        position.get()[0][0] = matrix4x4[0][3];
        position.get()[1][0] = matrix4x4[1][3];
        position.get()[2][0] = matrix4x4[2][3];

        get()[3][0] = 0;
        get()[3][1] = 0;
        get()[3][2] = 0;
        get()[3][3] = 1;

        update();
    }

    /**
     * Update values of matrix. NECESSARY to run after changes.
     */
    public void update(){
        //position and orientation
        for(int i=0; i<3; i++){
            //position
            matrix[i][3] = position.get()[i][0];

            for(int j=0; j<3; j++){
                //orientation
                matrix[i][j] = orientation.get()[i][j];
            }//for j
        }//for i

    }

    /**
     * Set default values for position, orientation and 4th (bottom) row (0,0,0,1)
     */
    @Override
    public void setDefault(){
        position = new Matrix(3,1);
        position.setDefault();

        orientation = new OrientationMatrix();
        orientation.setDefault();

        matrix[3][0] = 0;
        matrix[3][1] = 0;
        matrix[3][2] = 0;
        matrix[3][3] = 1;

        update();
    }

    public TransformationMatrix getInverse(){
        Matrix positionInv = new Matrix(3,1);
        OrientationMatrix orientationInv = new OrientationMatrix();

        //variables
        double a1, a2, a3, b1, b2, b3, c1, c2, c3, px, py, pz;
        a1 = get()[0][0];   b1 = get()[0][1];   c1 = get()[0][2];   px = get()[0][3];
        a2 = get()[1][0];   b2 = get()[1][1];   c2 = get()[1][2];   py = get()[1][3];
        a3 = get()[2][0];   b3 = get()[2][1];   c3 = get()[2][2];   pz = get()[2][3];

        //set positionInv
        positionInv.get()[0][0] = -(px*a1 + py*a2 + pz*a3);
        positionInv.get()[1][0] = -(px*b1 + py*b2 + pz*b3);
        positionInv.get()[2][0] = -(px*c1 + py*c2 + pz*c3);

        //set orientationInv
        orientationInv.get()[0][0] = a1;
        orientationInv.get()[0][1] = a2;
        orientationInv.get()[0][2] = a3;

        orientationInv.get()[1][0] = b1;
        orientationInv.get()[1][1] = b2;
        orientationInv.get()[1][2] = b3;

        orientationInv.get()[2][0] = c1;
        orientationInv.get()[2][1] = c2;
        orientationInv.get()[2][2] = c3;

        TransformationMatrix transformationInv = new TransformationMatrix(orientationInv, positionInv);
        return transformationInv;
    }

    public void eulerAnglesZXZ(){
        double alpha=0, beta=0, gamma=0;
        double r11, r12, r13, r21, r22, r23, r31, r32, r33;
        r11 = get()[0][0];  r12 = get()[0][1];  r13 = get()[0][2];
        r21 = get()[1][0];  r22 = get()[1][1];  r23 = get()[1][2];
        r31 = get()[2][0];  r32 = get()[2][1];  r33 = get()[2][2];

        /*if (r13==0)
            System.out.println("-----------------------r13=0 błąd");
        if (r23==0)
            System.out.println("-----------------------r23=0 błąd");

        alpha = Math.atan2(r23, r13);
        beta = Math.acos(r33);

        if (beta>0){
            alpha = alpha;
            beta = Math.atan2(Math.sqrt( Math.pow(r13,2) + Math.pow(r23,2) ), r33);
            gamma = Math.atan2(r32, -r31);
        } else if (beta<0) {
            alpha = Math.atan2(-r23, -r13);
            beta = Math.atan2(Math.sqrt(-Math.pow(r13,2) + Math.pow(r23,2) ), r33);
            gamma = Math.atan2(-r32, r31);
        } else
            System.out.println("-----brak rozwiązania dla sin(beta)=0");*/

        beta = Math.acos(r33);
        gamma = Math.atan(r31/r32);
        alpha = Math.atan(r13/-r23);

        alpha = Math.toDegrees(alpha);
        beta = Math.toDegrees(beta);
        gamma = Math.toDegrees(gamma);

        System.out.println("eulerAnglesZXZ: alpha(+-)=" + alpha + ", beta(+-)=" + beta + ", gamma=" + gamma);
    }
}
