package model.javaGL.matrix;

import java.util.Arrays;

/**
 * Representation of an n x m matrix.
 * <br>
 * The row and columns of the matrix are 0-indexed.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class DoubleMatrix implements Matrix<Double> {
    private final double[][] iMatrix;

    /**
     * Creates a matrix with all entries zero.
     * @param pRows the number of rows in the matrix
     * @param pColumns the number of columns in the matrix
     */
    public DoubleMatrix(final int pRows, final int pColumns) {
        super();
        this.iMatrix = new double[pRows][pColumns];
    }


    @Override
    public void set(final int pRow, final int pCol, final Double pValue) {
        this.iMatrix[pRow][pCol] = pValue;
    }

    @Override
    public Double get(final int pRow, final int pCol) {
        return this.iMatrix[pRow][pCol];
    }

    /**
     * Gets the number of rows in this matrix.
     * @return the number of rows in this matrix
     */
    @Override
    public int rowCount() {
        return this.iMatrix.length;
    }


    @Override
    public int columnCount() {
        return this.iMatrix[0].length;
    }

    @Override
    public DoubleMatrix copy() {
        final int lRows = this.rowCount();
        final int lCols = this.columnCount();
        final DoubleMatrix lDupe = new DoubleMatrix(lRows, lCols);
        for (int i = 0; i < lRows; i++) {
            lDupe.iMatrix[i] = Arrays.copyOf(this.iMatrix[i], lCols);
        }

        return lDupe;
    }
    
    /**
     * Creates an n x n identity matrix
     * @param pDimension the dimension of the square identity matrix
     * @return the identity matrix
     */
    public static DoubleMatrix identity(final int pDimension) {
        final DoubleMatrix lIdentity = new DoubleMatrix(pDimension, pDimension);
        for (int i = 0; i < pDimension; i++) {
            lIdentity.set(i, i, 1d);
        }

        return lIdentity;
    }
}
