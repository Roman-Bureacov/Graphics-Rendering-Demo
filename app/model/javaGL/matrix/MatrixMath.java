package model.javaGL.matrix;

import org.jetbrains.annotations.Nullable;

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

    /**
     * Performs matrix addition if and only if the left and right matrices are the same dimenions.
     * @param pLeft the left matrix
     * @param pRight the right matrix
     * @return the resulting matrix from the addition
     */
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

    /**
     * Inverses the matrix if the matrix is invertible.
     * @param pMatrix the matrix to inverse
     * @return the resulting inverted matrix, or null if the matrix is non-invertible
     */
    @Nullable
    public static Matrix<Double> inverse(final Matrix<Double> pMatrix) {
        /*
        Linear Algebra with Applications
        Steven J. Leon and Lisette de Pillis

        Chapter 2.3, page 116

        A^-1 = 1/det(A) * adj(A) when det(A) != 0

        where the adjoint of a matrix is, the transpose of the matrix of, the cofactor matrix of each term
         */

        final int lRows = pMatrix.rowCount();
        final int lCols = pMatrix.columnCount();

        if (lRows != lCols) return null;

        final Matrix<Double> lInverse = new DoubleMatrix(lRows, lCols);

        // find adjoint matrix
        for (int row = 0; row < lRows; row++) {
            for (int col = 0; col < lCols; col++) {
                // transpose in advance
                lInverse.set(col, row, cofactor(pMatrix, col, row));
            }
        }

        // find determinant
        // we can make use of the fact that the inverse matrix at this moment
        // if made up of cofactors
        double lDeterminant = 0;
        for (int col = 0; col < lCols; col++) {
            lDeterminant += pMatrix.get(0, col) * lInverse.get(0, col);
        }

        if (lDeterminant == 0) return null;

        for (int row = 0; row < lRows; row++) {
            for (int col = 0; col < lCols; col++) {
                lInverse.set(row, col, 1d / lDeterminant * lInverse.get(row, col));
            }
        }

        return lInverse;
    }

    /**
     * Finds the cofactor for the matrix at the row and column
     * @param pMatrix the matrix to look in
     * @param pRow the row
     * @param pCol the column
     * @return the cofactor of the row and column
     */
    public static double cofactor(final Matrix<Double> pMatrix, final int pRow, final int pCol) {
        if (pMatrix.rowCount() != pMatrix.columnCount()) 
            throw new IllegalArgumentException("Matrix must be square to find cofactor");
        
        // get minor
        final int lRows = pMatrix.rowCount();
        final int lCols = pMatrix.columnCount();
        final Matrix<Double> lMinor = new DoubleMatrix(lRows - 1, lCols - 1);
        for (int row = 0; row < lRows; row++) {
            if (row == pRow) {
                // skip row
                row++;
            } else {
                for (int col = 0; col < lCols; col++) {
                    if (col == pCol) {
                        // skip column
                        col++;
                    } else {
                        lMinor.set(row, col, pMatrix.get(row, col));
                    }
                }
            }
        }

        return Math.pow(-1, pRow + pCol) * determinant(lMinor);
    }

    /**
     * Finds the determinants for the matrix at the row and column
     * @param pMatrix the matrix to look in
     * @return the determinant of the row and column
     */
    public static double determinant(final Matrix<Double> pMatrix) {
        final int lRows = pMatrix.rowCount();
        final int lCols = pMatrix.columnCount();

        if (lRows != lCols)
            throw new IllegalArgumentException("Matrix must be square to find determinant");
        
        double lDet = 0;

        if (lRows == 1) {
            return pMatrix.get(0, 0);
        } else if (lRows == 2) {
            return pMatrix.get(0, 0) * pMatrix.get(1, 1)
                    - pMatrix.get(0, 1) * pMatrix.get(1, 0);
        } else {
            // find the determinant using the first row
            for (int col = 0; col < lCols; col++) {
                lDet = pMatrix.get(0, col) * cofactor(pMatrix, 0, col);
            }
        }

        return lDet;
    }
}
