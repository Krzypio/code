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
}
