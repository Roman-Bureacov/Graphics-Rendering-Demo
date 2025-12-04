package app.model.javaGL.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;
import model.javaGL.matrix.Vertex;
import org.junit.jupiter.api.Test;

/**
 * Tests the matrix math of the javaGL library
 *
 * @author Roman Bureacov
 * @version 2025-12
 */
public class MatrixMathTest {

    /**
     * Tests if the 1x1 matrices multiply correctly
     */
    @Test
    public void testSmallMatrixMultiply() {
        final Matrix<Double> lMatrixA = new DoubleMatrix(1, 1);
        final Matrix<Double> lMatrixB = new DoubleMatrix(1, 1);

        lMatrixA.set(0, 0, 2d);
        lMatrixB.set(0, 0, 5d);

        final Matrix<Double> lMatrixExp = new DoubleMatrix(1, 1);
        lMatrixExp.set(0, 0, 10d);
        assertSameMatrix(lMatrixExp, MatrixMath.matrixMultiply(lMatrixA, lMatrixB));
    }

    /**
     * Tests if the 4x4 matrices multiply into the correct 4x4 matrix
     */
    @SuppressWarnings({"OverlyLongMethod", "ExtractMethodRecommender"})
    @Test
    public void testLargerSquareMatrixMultiply() {
        final Matrix<Double> lMatrixA = new DoubleMatrix(4, 4);
        final Matrix<Double> lMatrixB = new DoubleMatrix(4, 4);

        // configure matrix A
        lMatrixA.set(0, 0, 1d);
        lMatrixA.set(0, 1, 2d);
        lMatrixA.set(0, 2, 3d);
        lMatrixA.set(0, 3, 4d);

        lMatrixA.set(1, 0, 5d);
        lMatrixA.set(1, 1, 6d);
        lMatrixA.set(1, 2, 7d);
        lMatrixA.set(1, 3, 8d);

        lMatrixA.set(2, 0, 9d);
        lMatrixA.set(2, 1, 0d);
        lMatrixA.set(2, 2, 1d);
        lMatrixA.set(2, 3, 2d);

        lMatrixA.set(3, 0, 3d);
        lMatrixA.set(3, 1, 4d);
        lMatrixA.set(3, 2, 5d);
        lMatrixA.set(3, 3, 6d);

        // configure matrix B
        lMatrixB.set(0, 0, 7d);
        lMatrixB.set(0, 1, 8d);
        lMatrixB.set(0, 2, 9d);
        lMatrixB.set(0, 3, 0d);

        lMatrixB.set(1, 0, 1d);
        lMatrixB.set(1, 1, 2d);
        lMatrixB.set(1, 2, 3d);
        lMatrixB.set(1, 3, 4d);

        lMatrixB.set(2, 0, 5d);
        lMatrixB.set(2, 1, 6d);
        lMatrixB.set(2, 2, 7d);
        lMatrixB.set(2, 3, 8d);

        lMatrixB.set(3, 0, 9d);
        lMatrixB.set(3, 1, 0d);
        lMatrixB.set(3, 2, 1d);
        lMatrixB.set(3, 3, 2d);

        // configure expected matrix
        final Matrix<Double> lMatrixExp = new DoubleMatrix(4, 4);
        lMatrixExp.set(0, 0, 60d);
        lMatrixExp.set(0, 1, 30d);
        lMatrixExp.set(0, 2, 40d);
        lMatrixExp.set(0, 3, 40d);

        lMatrixExp.set(1, 0, 148d);
        lMatrixExp.set(1, 1, 94d);
        lMatrixExp.set(1, 2, 120d);
        lMatrixExp.set(1, 3, 96d);

        lMatrixExp.set(2, 0, 86d);
        lMatrixExp.set(2, 1, 78d);
        lMatrixExp.set(2, 2, 90d);
        lMatrixExp.set(2, 3, 12d);

        lMatrixExp.set(3, 0, 104d);
        lMatrixExp.set(3, 1, 62d);
        lMatrixExp.set(3, 2, 80d);
        lMatrixExp.set(3, 3, 68d);


        assertSameMatrix(lMatrixExp, MatrixMath.matrixMultiply(lMatrixA, lMatrixB));
    }

    /**
     * Tests if the 4x4 and 4x1 matrices multiply into the correct 4x1 matrix
     */
    @Test
    public void testLargerNonSquareMatrixMultiply() {
        final Matrix<Double> lMatrixA = new DoubleMatrix(4, 4);
        final Matrix<Double> lMatrixB = new DoubleMatrix(4, 1);

        // configure matrix A
        lMatrixA.set(0, 0, 1d);
        lMatrixA.set(0, 1, 2d);
        lMatrixA.set(0, 2, 3d);
        lMatrixA.set(0, 3, 4d);

        lMatrixA.set(1, 0, 5d);
        lMatrixA.set(1, 1, 6d);
        lMatrixA.set(1, 2, 7d);
        lMatrixA.set(1, 3, 8d);

        lMatrixA.set(2, 0, 9d);
        lMatrixA.set(2, 1, 0d);
        lMatrixA.set(2, 2, 1d);
        lMatrixA.set(2, 3, 2d);

        lMatrixA.set(3, 0, 3d);
        lMatrixA.set(3, 1, 4d);
        lMatrixA.set(3, 2, 5d);
        lMatrixA.set(3, 3, 6d);

        // configure matrix B
        lMatrixB.set(0, 0, 7d);
        lMatrixB.set(1, 0, 1d);
        lMatrixB.set(2, 0, 5d);
        lMatrixB.set(3, 0, 9d);

        // configure expected matrix
        final Matrix<Double> lMatrixExp = new DoubleMatrix(4, 1);
        lMatrixExp.set(0, 0, 60d);
        lMatrixExp.set(1, 0, 148d);
        lMatrixExp.set(2, 0, 86d);
        lMatrixExp.set(3, 0, 104d);

        assertSameMatrix(lMatrixExp, MatrixMath.matrixMultiply(lMatrixA, lMatrixB));
    }

    /**
     * Tests vertex multiplication commonly used for transformations
     */
    @Test
    public void testVertexMultiply() {
        final Matrix<Double> lMatrixA = new DoubleMatrix(4, 4);
        final Vertex lVertex = new Vertex();

        // configure matrix A
        lMatrixA.set(0, 0, 1d);
        lMatrixA.set(0, 1, 2d);
        lMatrixA.set(0, 2, 3d);
        lMatrixA.set(0, 3, 4d);

        lMatrixA.set(1, 0, 5d);
        lMatrixA.set(1, 1, 6d);
        lMatrixA.set(1, 2, 7d);
        lMatrixA.set(1, 3, 8d);

        lMatrixA.set(2, 0, 9d);
        lMatrixA.set(2, 1, 0d);
        lMatrixA.set(2, 2, 1d);
        lMatrixA.set(2, 3, 2d);

        lMatrixA.set(3, 0, 3d);
        lMatrixA.set(3, 1, 4d);
        lMatrixA.set(3, 2, 5d);
        lMatrixA.set(3, 3, 6d);

        // configure matrix B
        lVertex.set(0, 0, 7d);
        lVertex.set(1, 0, 1d);
        lVertex.set(2, 0, 5d);
        lVertex.set(3, 0, 9d);

        // configure expected matrix
        final Vertex lMatrixExp = new Vertex(60d, 148d, 86d);
        lMatrixExp.set(3, 104d);

        assertSameMatrix(lMatrixExp, MatrixMath.vertexMultiply(lMatrixA, lVertex));
    }

    /**
     * Tests if the 4x4 matrix is correctly inverted
     */
    @Test
    public void testInversion() {
        // initial matrix
        final Matrix<Double> lMatrixInitial = new DoubleMatrix(4, 4);
        lMatrixInitial.set(0, 0, 1d);
        lMatrixInitial.set(0, 1, 1d);
        lMatrixInitial.set(0, 2, 2d);
        lMatrixInitial.set(0, 3, 3d);

        lMatrixInitial.set(1, 0, 4d);
        lMatrixInitial.set(1, 1, 2d);
        lMatrixInitial.set(1, 2, 5d);
        lMatrixInitial.set(1, 3, 6d);

        lMatrixInitial.set(2, 0, 7d);
        lMatrixInitial.set(2, 1, 8d);
        lMatrixInitial.set(2, 2, 3d);
        lMatrixInitial.set(2, 3, 9d);

        lMatrixInitial.set(3, 0, 0d);
        lMatrixInitial.set(3, 1, 1d);
        lMatrixInitial.set(3, 2, 2d);
        lMatrixInitial.set(3, 3, 4d);

        // expected inverse matrix
        final Matrix<Double> lMatrixInverse = new DoubleMatrix(4, 4);
        lMatrixInverse.set(0, 0, -4.9d);
        lMatrixInverse.set(0, 1, 1.3d);
        lMatrixInverse.set(0, 2, 0.1d);
        lMatrixInverse.set(0, 3, 1.5d);

        lMatrixInverse.set(1, 0, 8d);
        lMatrixInverse.set(1, 1, -2d);
        lMatrixInverse.set(1, 2, 0d);
        lMatrixInverse.set(1, 3, -3d);

        lMatrixInverse.set(2, 0, 7.8d);
        lMatrixInverse.set(2, 1, -1.6d);
        lMatrixInverse.set(2, 2, -0.2d);
        lMatrixInverse.set(2, 3, -3d);

        lMatrixInverse.set(3, 0, -5.9d);
        lMatrixInverse.set(3, 1, 1.3d);
        lMatrixInverse.set(3, 2, 0.1d);
        lMatrixInverse.set(3, 3, 2.5d);

        final Matrix<Double> lMatrixActual = MatrixMath.inverse(lMatrixInitial);
        assertNotNull(lMatrixActual, "Matrix was found to be singular instead of nonsingular");
        assertSameMatrix(lMatrixInverse, lMatrixActual);
    }

    /**
     * Asserts if the two matrices are equivalent
     * @param pMatrixExpected the first matrix to test
     * @param pMatrixActual the second matrix to test
     * @param pTolerance the tolerance on double values to be considered the same
     */
    public static void assertSameMatrix(
            final Matrix<Double> pMatrixExpected,
            final Matrix<Double> pMatrixActual,
            final double pTolerance) {

        final int lRows = pMatrixExpected.rowCount();
        final int lCols = pMatrixExpected.columnCount();

        assertEquals(lRows, pMatrixActual.rowCount(), "The matrices differ in rows");
        assertEquals(lCols, pMatrixActual.columnCount(), "The matrices differ in columns");

        for (int row = 0; row < lRows; row++) {
            for (int col = 0; col < lCols; col++) {
                final double lValExp = pMatrixExpected.get(row, col);
                final double lValAct = pMatrixActual.get(row, col);
                assertTrue(Math.abs(lValExp - lValAct) < pTolerance,
                        "Matrix expected (%f) did not reasonably match matrix actual (%f) on row %d col %d".formatted(
                                lValExp, lValAct, row, col
                        ));
            }
        }
    }


    /**
     * Asserts if the two matrices are equivalent with a tolerance of 0.00001
     * @param pMatrixExcpected the first matrix to test
     * @param pMatrixActual the second matrix to test
     */
    public static void assertSameMatrix(
            final Matrix<Double> pMatrixExcpected,
            final Matrix<Double> pMatrixActual
    ) {
        assertSameMatrix(pMatrixExcpected, pMatrixActual, 0.00001);
    }
}
