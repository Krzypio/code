/**
 * Class contains basic values and operations for matrix
 */
public class Matrix {
    int rows = 0;
    int columns = 0;
    double[][] matrix;
    boolean isValid;

    /**
     * Create matrix nxm, where n=rows and m=columns
     * @param rows
     * @param columns
     */
    public Matrix(int rows, int columns) {
        isValid = false;
        if(rows<1 || columns<1)
            throw new IllegalArgumentException("rows and columns must be not less than 1.");
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
        setDefault();
        this.isValid = true;
    }

    /**
     * Coreate matrix and copy values from source
     * @param matrix source
     */
    public Matrix(Matrix matrix){
        this(matrix.getRows(), matrix.getColumns());
        set(matrix.get());
    }

    /**
     * @return true if all operations on matrix were correct. False otherwise.
     */
    public boolean isValid(){
        return this.isValid;
    }

    /**
     * @return matrix double[][]
     */
    public double[][] get() {
        return matrix;
    }

    /**
     * @return rows count
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return columns count
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Set matrix basing on source one
     * @param bMatrix source
     */
    public void set(double[][] bMatrix){
        if(bMatrix == null){
            this.isValid = false;
            return;
        }
        int newRows = bMatrix.length;
        int newColumns = bMatrix[0].length;
        if (newRows != this.rows || newColumns != this.columns){
            this.isValid = false;
            return;
        }

        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.columns; j++){
                this.matrix[i][j] = bMatrix[i][j];
            }//for j
        }//for i
        this.isValid = true;
    }

    /**
     * Multiply actual matrix by multiplier matrix. Results saved in actual matrix.
     * @param multiplier multiplier
     */
    public void multiply(Matrix multiplier) {
        if(!this.isValid || !multiplier.isValid()){
            this.isValid = false;
            return;
        }
        if(this.getColumns() != multiplier.getRows()){
            this.isValid = false;
            return;
        }

        double result[][] = new double[rows][multiplier.columns];

        for (int rA = 0; rA < rows; rA++) {
            for (int cB = 0; cB < multiplier.getColumns(); cB++) {
                result[rA][cB] = 0;   //clear
                for (int cArB = 0; cArB < columns; cArB++) {
                    result[rA][cB] += this.matrix[rA][cArB] * multiplier.get()[cArB][cB];
                }//for k
            }// for j
        }// for i
        this.columns = multiplier.getColumns();
        this.matrix = new double[rows][columns];
        this.setDefault();
        this.set(result);
    }//multiplyMatrices()

    /**
     * Multiply by value. Result saved in actual matrix.
     * @param value
     */
    public void multiply(double value){
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                matrix[i][j] *= value;
            }//for j
        }//for i
    }

    /**
     * Addition of actual and source matrix. Result saved in source matrix.
     * @param matrix source
     */
    public void add (Matrix matrix){
        if(!this.isValid || !matrix.isValid()){
            this.isValid = false;
            return;
        }

        int matCol = matrix.getColumns();
        int matRow = matrix.getRows();

        if(rows != matRow || columns != matCol){
            this.isValid = false;
            return;
        }

        for (int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                this.matrix[i][j] += matrix.get()[i][j];
            }//for j
        }//for i
    }

    /**
     * fill matrix with 0
     */
    public void setDefault(){
        for (int i=0; i<rows; i++){
            for(int j=0; j<columns; j++)
                matrix[i][j] = 0;
        }//for i
    }

    /**
     * @return string describing matrix with values rounted to two points after comma.
     */
    @Override
    public String toString() {
        if (!isValid()) return "Not valid";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String strDouble = String.format("%5.2f", this.matrix[i][j]);
                sb.append(strDouble + '\t');
            }//for j
            sb.append("\n");
        }//for i
        sb.setLength(sb.length()-1);
        return sb.toString();
    }//toString()
}
