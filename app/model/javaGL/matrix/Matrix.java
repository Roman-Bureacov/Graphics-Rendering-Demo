package model.javaGL.matrix;

/**
 * Representation of an n x n matrix.
 * <br>
 * The row and columns of the matrix are 0-indexed.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public final class Matrix {
    private final double[][] iMatrix;

    /**
     * Creates a matrix with all entries zero.
     * @param pRows the number of rows in the matrix
     * @param pColumns the number of columns in the matrix
     */
    public Matrix(final int pRows, final int pColumns) {
        super();
        this.iMatrix = new double[pRows][pColumns];
    }

    /**
     * Sets the value at the specified row and column.
     *
     * @param pRow the row to set at
     * @param pCol the columns to set at
     * @param pValue the value to set
     * @return the value that was replaced
     */
    public double set(final int pRow, final int pCol, final double pValue) {
        final double lOld = this.iMatrix[pRow][pCol];
        this.iMatrix[pRow][pCol] = pValue;
        return lOld;
    }

    /**
     * Gets the value at the specified row and column.
     * @param pRow the row to get from
     * @param pCol the column to get form
     * @return the value at the row and column
     */
    public double get(final int pRow, final int pCol) {
        return this.iMatrix[pRow][pCol];
    }

    /**
     * Gets the number of rows in this matrix.
     * @return the number of rows in this matrix
     */
    public int rowCount() {
        return this.iMatrix.length;
    }

    /**
     * Gets the number of columns in this matrix.
     * The number of columns is guaranteed to be the same
     * for every row.
     * @return The number of columns in this matrix.
     */
    public int columnCount() {
        return this.iMatrix[0].length;
    }
}
