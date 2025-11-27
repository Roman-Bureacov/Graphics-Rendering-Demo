package model.javaGL.matrix;

/**
 * Represents a vertex as a 4x1 column vector of doubles.
 * 
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Vertex implements Matrix<Double> {

    // transpose of a column vector
    private final double[] iVertex = new double[4];

    /**
     * Constructs a vertex with all entries 0 and the final row as 1.
     */
    public Vertex() {
        super();
        this.iVertex[3] = 1d;
    }

    /**
     * Constructs a vertex with the specified parameters and the final row as 1.
     * @param pX the x value of this vertex
     * @param pY the y value of this vertex
     * @param pZ the z value of this vertex
     */
    public Vertex(final double pX, final double pY, final double pZ) {
        this();
        this.iVertex[0] = pX;
        this.iVertex[1] = pY;
        this.iVertex[2] = pZ;
    }

    /**
     * Convenience method for setting the vertex value
     * @param pRow the row to set
     * @param pValue the value to set
     */
    public void set(final int pRow, final double pValue) {
        this.iVertex[pRow] = pValue;
    }

    /**
     * Convenience method for getting the vertex values
     * @param pRow the row to get
     * @return the value at the row
     */
    public double get(final int pRow) {
        return this.iVertex[pRow];
    }

    @Override
    public void set(final int pRow, final int pCol, final Double pValue) {
        if (pCol > 0) throw new IndexOutOfBoundsException("column argument out of bounds for vertex");
        this.iVertex[pRow] = pValue;
    }

    @Override
    public Double get(final int pRow, final int pCol) {
        if (pCol > 0) throw new IndexOutOfBoundsException("column argument out of bounds for vertex");
        return this.iVertex[pRow];
    }

    @Override
    public int rowCount() {
        return 4;
    }

    @Override
    public int columnCount() {
        return 1;
    }

    @Override
    public Vertex copy() {
        return new Vertex(
                this.iVertex[0],
                this.iVertex[1],
                this.iVertex[2]
        );
    }
}
