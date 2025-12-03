package model.javaGL.matrix;

import java.util.Arrays;

/**
 * Representation of a matrix that may be composed
 * of integers, floating-point numbers, or even null
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class NumberMatrix implements Matrix<Number> {
    private final Number[][] iMatrix;

    /**
     * Creates a number matrix with all entries null.
     * @param pRows the number of rows in this matrix
     * @param pColumns the number of columns in this matrix
     */
    public NumberMatrix(final int pRows, final int pColumns) {
        super();
        this.iMatrix = new Number[pRows][pColumns];
    }

    @Override
    public Matrix<Number> clone() {
        final int lRows = this.rowCount();
        final int lCols = this.columnCount();
        final NumberMatrix lDupe;

        try {
            lDupe = (NumberMatrix) super.clone();
        } catch (final CloneNotSupportedException lExc) {
            // lazy exception
            throw new RuntimeException(lExc);
        }

        for (int i = 0; i < lRows; i++) {
            lDupe.iMatrix[i] = Arrays.copyOf(this.iMatrix[i], lCols);
        }

        return lDupe;
    }

    @Override
    public int columnCount() {
        return this.iMatrix[0].length;
    }

    @Override
    public int rowCount() {
        return this.iMatrix.length;
    }

    @Override
    public Number get(final int pRow, final int pCol) {
        return this.iMatrix[pRow][pCol];
    }

    @Override
    public void set(final int pRow, final int pCol, final Number pValue) {
        this.iMatrix[pRow][pCol] = pValue;
    }


    /**
     * Creates an n x n identity matrix
     * @param pDimension the dimension of the square identity matrix
     * @return the identity matrix
     */
    public static NumberMatrix identity(final int pDimension) {
        final NumberMatrix lIdentity = new NumberMatrix(pDimension, pDimension);
        for (int i = 0; i < pDimension; i++) {
            lIdentity.set(i, i, 1);
        }

        return lIdentity;
    }
}
