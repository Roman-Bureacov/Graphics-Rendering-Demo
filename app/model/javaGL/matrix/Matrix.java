package model.javaGL.matrix;

/**
 * Interface of a basic matrix.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public interface Matrix<T> {

    /**
     * Sets the value at the specified row and column.
     *
     * @param pRow the row to set at
     * @param pCol the columns to set at
     * @param pValue the value to set
     */
    void set(final int pRow, final int pCol, final T pValue);

    /**
     * Gets the value at the specified row and column.
     * @param pRow the row to get from
     * @param pCol the column to get form
     * @return the value at the row and column
     */
    T get(final int pRow, final int pCol);

    /**
     * Gets the number of rows in this matrix.
     * @return the number of rows in this matrix
     */
    int rowCount();

    /**
     * Gets the number of columns in this matrix.
     * The number of columns is guaranteed to be the same
     * for every row.
     * @return The number of columns in this matrix.
     */
    int columnCount();

    /**
     * Constructs a copy of this matrix.
     * @return the duplicate of this matrix
     */
    Matrix<T> copy();
}
