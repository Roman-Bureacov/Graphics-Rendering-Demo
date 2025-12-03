package model.javaGL.matrix;

import java.util.Arrays;

/**
 * Representation of an integer matrix, for precision
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class IntMatrix implements Matrix<Integer> {
    private final int[][] iMatrix;


    /**
     * Constructs an integer matrix with all entries 0.
     * @param pRows the number of rows in this matrix
     * @param pColumns the number of columns in this matrix
     */
    public IntMatrix(final int pRows, final int pColumns) {
        super();
        this.iMatrix = new int[pRows][pColumns];
    }

    @Override
    public void set(final int pRow, final int pCol, final Integer pValue) {
        this.iMatrix[pRow][pCol] = pValue;
    }

    @Override
    public Integer get(final int pRow, final int pCol) {
        return this.iMatrix[pRow][pCol];
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
    public Matrix<Integer> clone() {
        final int lRows = this.rowCount();
        final int lCols = this.columnCount();
        final IntMatrix lDupe;

        try {

            lDupe = (IntMatrix) super.clone();
        } catch (final CloneNotSupportedException lExc) {
            // lazy exception
            throw new RuntimeException(lExc);
        }

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
    public static IntMatrix identity(final int pDimension) {
        final IntMatrix lIdentity = new IntMatrix(pDimension, pDimension);
        for (int i = 0; i < pDimension; i++) {
            lIdentity.set(i, i, 1);
        }

        return lIdentity;
    }
}
