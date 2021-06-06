import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MatrixTest {

    @Test
    public void constructorTest1(){
        final Matrix matrix = new Matrix(1, 1);
        assertEquals(true, matrix.isValid());
    }

    @Test()
    public void constructorTest2(){

        try {
            final Matrix matrix = new Matrix(0, 1);
            fail("My method didn't throw when i expected it to");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    public void constructorTest3(){
        try {
            final Matrix matrix = new Matrix(-1, 0);
            fail("My method didn't throw when i expected it to");
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    public void setMatrixIsValidTest1(){
        final Matrix matrix = new Matrix(3,3);
        matrix.set(new double[][]{{1,1,1},{2,2,2},{3,3,3}});
        assertEquals(true, matrix.isValid());
    }

    @Test
    public void setMatrixIsValidTest2(){
        final Matrix matrix = new Matrix(4,3);
        matrix.set(new double[][]{{1,1,1},{2,2,2},{3,3,3}});
        assertEquals(false, matrix.isValid());
    }

    @Test
    public void setMatrixIsValidTest3(){
        final Matrix matrix = new Matrix(3,2);
        matrix.set(new double[][]{{1,1,1},{2,2,2},{3,3,3}});
        assertEquals(false, matrix.isValid());
    }

    @Test
    public void setGetMatrixTest4(){
        final Matrix matrix = new Matrix(3,3);
        double[][] input = {{1,1,1},{2,2,2},{3,3,3}};
        matrix.set(input);
        assertEquals(true, Arrays.deepEquals(input, matrix.get()));
    }

    @Test
    public void setGetMatrixTest5(){
        final Matrix matrix = new Matrix(3,3);
        double[][] input = {{1,1,1},{2,2,2},{3,3,3}};
        matrix.set(input);
        input[0][0] = 9;
        assertEquals(false, Arrays.deepEquals(input, matrix.get()));
    }

    @Test
    public void multiplyTest1(){
        final Matrix a = new Matrix(3,3);
        a.set(new double[][]{{-1,-2,3}, {0,2,-1}, {-1,3,0}});

        final Matrix b = new Matrix(3,3);
        b.set(new double[][]{{1,5,1}, {2,1,2}, {3,2,3}});

        final Matrix expResult = new Matrix(3,3);
        expResult.set(new double[][]{{4, -1, 4}, {1, 0, 1}, {5, -2, 5}});

        a.multiply(b);
        assertEquals(true, Arrays.deepEquals(a.get(), expResult.get()));
    }

    @Test
    public void multiplyTest2(){
        final Matrix a = new Matrix(3,3);
        a.set(new double[][]{{-2,-1,0}, {1,2,3}, {4,5,6}});
        final Matrix expResult = new Matrix(3,3);
        expResult.set(new double[][]{{-20,-10,0}, {10,20,30}, {40,50,60}});

        a.multiply(10);
        assertEquals(true, Arrays.deepEquals(a.get(), expResult.get()));
    }

    @Test
    public void addTest(){
        final Matrix a = new Matrix(3,3);
        a.set(new double[][]{{-2,-1,0}, {1,2,3}, {4,5,6}});

        final Matrix b = new Matrix(3,3);
        b.set(new double[][]{{1,1,1}, {1,1,1}, {1,1,1}});

        final Matrix expResult = new Matrix(3,3);
        expResult.set(new double[][]{{-1,0,1}, {2,3,4}, {5,6,7}});

        a.add(b);
        assertEquals(true, Arrays.deepEquals(a.get(), expResult.get()));
    }
}
