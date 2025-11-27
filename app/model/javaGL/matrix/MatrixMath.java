package model.javaGL.matrix;

/**
 * A utility class that performs matrix algebra.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public final class MatrixMath {

    private MatrixMath() {
        super();
    }

    /**
     * Performs matrix multiplication.
     * @param pLeftRealMatrix the matrix that would appear on the left of the multiplication
     * @param pRightRealMatrix the matrix that would appear on the right of the multiplication
     * @return the resulting matrix product
     * @throws IllegalArgumentException if the matrix sizes are invalid
     */
    public static Matrix<Double> matrixMultiply(
            final Matrix<Double> pLeftRealMatrix,
            final Matrix<Double> pRightRealMatrix
            )
            throws IllegalArgumentException {

        final int lLeftRows = pLeftRealMatrix.rowCount();
        final int lLeftColumns = pLeftRealMatrix.columnCount();
        final int lRightColumns = pRightRealMatrix.columnCount();
        final int lRightRows = pRightRealMatrix.rowCount();

        if (lLeftColumns != lRightRows)
            throw new IllegalArgumentException(
                    "Matrices are of incompatible dimensions for multiplication: %d columns against %d rows"
                            .formatted(lLeftColumns, lRightRows));

        final DoubleMatrix lResult = new DoubleMatrix(lLeftRows, lRightColumns);

        for (int leftRow = 0; leftRow < lLeftRows; leftRow++) {
            for (int rightCol = 0; rightCol < lRightColumns; rightCol++) {
                double lSum = 0d;
                for (int i = 0; i < lLeftColumns; i++) {
                    lSum += pLeftRealMatrix.get(leftRow, i) * pRightRealMatrix.get(i, rightCol);
                }
                lResult.set(leftRow, rightCol, lSum);
            }
        }

        return lResult;
    }

    /**
     * Performs scalar matrix multiplication, multiplying each entry of the matrix by a scalar.
     * @param pScalar the scalar to multiply with
     * @param pDoubleMatrix the matrix to multiply into
     * @return the resulting matrix
     */
    public static DoubleMatrix scalarMultiply(final double pScalar, final DoubleMatrix pDoubleMatrix) {
        final DoubleMatrix lResult = new DoubleMatrix(pDoubleMatrix.rowCount(), pDoubleMatrix.columnCount());

        final int lColumnCount = pDoubleMatrix.columnCount();
        final int lRowCount = pDoubleMatrix.rowCount();
        for (int row = 0; row < lRowCount; row++) {
            for (int col = 0; col < lColumnCount; col++) {
                lResult.set(row, col, pScalar * pDoubleMatrix.get(row, col));
            }
        }

        return lResult;
    }

    /**
     * Performs a matrix multiplication with a vertex. Ignores the fourth row of the vertex matrix.
     * @param pTransformation the transformation matrix
     * @param pVertex the vertex to transform
     * @return the resulting vertex
     */
    public static Vertex vertexMultiply(final Matrix<Double> pTransformation, final Vertex pVertex) {
        if (pTransformation.rowCount() != 4 && pTransformation.columnCount() != 4)
            throw new IllegalArgumentException("transformation matrix must be 4x4");

        final Vertex lResult = new Vertex();
        for (int row = 0; row < 4; row++) {
            double lSum = 0;
            for (int col = 0; col < 4; col++) {
                lSum += pTransformation.get(row, col) * pVertex.get(col);
            }
            lResult.set(row, lSum);
        }

        return lResult;
    }

    public static Matrix<Double> addMatrix(final Matrix<Double> pLeft, final Matrix<Double> pRight) {
        final int lRows = pLeft.rowCount();
        final int lCols = pLeft.columnCount();

        if (lRows != pRight.rowCount())
            throw new IllegalArgumentException("Rows of matrices must match for addition");
        if (lCols != pRight.columnCount())
            throw new IllegalArgumentException("Columns of matrices must match for addition");

        final Matrix<Double> lResult = new DoubleMatrix(lRows, lCols);

        for (int row = 0; row < lRows; row++) {
            for (int col = 0; col < lCols; col++) {
                lResult.set(row, col, pLeft.get(row, col) + pRight.get(row, col));
            }
        }

        return lResult;
    }
}
