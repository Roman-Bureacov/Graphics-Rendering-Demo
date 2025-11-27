package model.javaGL.mesh.primitives;

import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.Vertex;

/**
 * Representation of basic primitives.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public abstract class Primitive {

    private int iColor = 0x000000;

    /**
     * Returns the vertices representing this primitive.
     * @return the vertices. No sorting is guaranteed.
     */
    public abstract Vertex[] points();

    /**
     * Applies a matrix transformation to the set of points
     * defining this primitive.
     * @param pTransform the matrix representing the linear transformation
     */
    public abstract void transform(final Matrix<Double> pTransform);

    /**
     * Performs but does not apply a matrix transformation to the set of
     * points defining this matrix
     * @param pTransform the matrix representing the linear transformation
     * @return the resulting transformation
     */
    public abstract Vertex[] transformCopy(final Matrix<Double> pTransform);

    /**
     * Gets the color value from this primitive
     * @return the RGB value
     */
    public int color() {
        return this.iColor;
    }

    /**
     * Sets the color for this primitive.
     * @param pColor the (ideally hex) RGB value
     */
    public void setColor(final int pColor) {
        this.iColor = pColor;
    }

    /**
     * Constructs a copy of this primitive.
     * @return the duplicate of this primitive
     */
    public abstract Primitive copy();
}
