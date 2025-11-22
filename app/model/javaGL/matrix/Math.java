package model.javaGL.matrix;

/**
 * A utility class that performs matrix algebra.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public final class Math {

    private Math() {
        super();
    }

    /**
     * Performs matrix multiplication.
     * @param pLeftMatrix the matrix that would appear on the left of the multiplication
     * @param pRightMatrix the matrix that would appear on the right of the multiplication
     * @return the resulting matrix product
     * @throws IllegalArgumentException if the matrix sizes are invalid
     */
    public static Matrix matrixMultiply(final Matrix pLeftMatrix, final Matrix pRightMatrix)
            throws IllegalArgumentException {
        final int lLeftRows = pLeftMatrix.rowCount();
        final int lLeftColumns = pLeftMatrix.columnCount();
        final int lRightColumns = pRightMatrix.columnCount();

        if (pLeftMatrix.columnCount() != pRightMatrix.rowCount())
            throw new IllegalArgumentException("Matrices are of incompatible dimensions for multiplication");

        final Matrix lResult = new Matrix(lLeftRows, lRightColumns);

        for (int leftRow = 0; leftRow < lLeftRows; leftRow++) {
            for (int rightCol = 0; rightCol < lRightColumns; rightCol++) {
                double lSum = 0d;
                for (int i = 0; i < lLeftColumns; i++) {
                    lSum += pLeftMatrix.get(leftRow, i) * pRightMatrix.get(i, rightCol);
                }
                lResult.set(leftRow, rightCol, lSum);
            }
        }

        return lResult;
    }

    /**
     * Performs scalar matrix multiplication, multiplying each entry of the matrix by a scalar.
     * @param pScalar the scalar to multiply with
     * @param pMatrix the matrix to multiply into
     * @return the resulting matrix
     */
    public static Matrix scalarMultiply(final double pScalar, final Matrix pMatrix) {
        final Matrix lResult = new Matrix(pMatrix.rowCount(), pMatrix.columnCount());

        final int lColumnCount = pMatrix.columnCount();
        final int lRowCount = pMatrix.rowCount();
        for (int row = 0; row < lRowCount; row++) {
            for (int col = 0; col < lColumnCount; col++) {
                lResult.set(row, col, pScalar * pMatrix.get(row, col));
            }
        }

        return lResult;
    }
}
